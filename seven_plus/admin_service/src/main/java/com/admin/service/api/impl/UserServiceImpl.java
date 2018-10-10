package com.admin.service.api.impl;

import com.admin.service.api.UserService;
import com.common.config.AliSmsConfig;
import com.common.config.WechatConfig;
import com.common.consts.Consts;
import com.common.consts.UserContext;
import com.common.excption.AuthExceptionConstants;
import com.common.excption.BaseException;
import com.common.jwt.provider.JwtTokenProvider;
import com.common.sms.AliSmsUtil;
import com.common.tencent.map.TencentMapUtil;
import com.common.wechat.authorize.MicroAuthorize;
import com.domain.admin.mapper.AdminSystemConfigMapper;
import com.domain.plus.entity.OrderRenew;
import com.domain.plus.entity.PlusCar;
import com.domain.plus.entity.PlusOrder;
import com.domain.plus.entity.PlusUser;
import com.domain.plus.mapper.OrderRenewMapper;
import com.domain.plus.mapper.PlusCarMapper;
import com.domain.plus.mapper.PlusOrderMapper;
import com.domain.plus.mapper.PlusUserMapper;
import com.domain.plus.param.SessionParam;
import com.domain.plus.vo.UserAuthVo;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 14:44
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WechatConfig config;

    @Autowired
    private AliSmsConfig aliSmsConfig;

    @Autowired
    private AdminSystemConfigMapper systemConfig;

    @Autowired
    private OrderRenewMapper renewMapper;

    @Autowired
    private PlusUserMapper userMapper;

    @Autowired
    private PlusCarMapper carMapper;

    @Autowired
    private PlusOrderMapper orderMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public Boolean existPhone(Long id) {
        PlusUser user = userMapper.queryById(id);
        if (StringUtils.isEmpty(user.getPhone())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public List<Map<String,Object>> findOrderByUserId(Long id, Integer type) {
        List<PlusOrder> orderList = orderMapper.queryListOrderByUserIdAndType(id,type);
        if (null == orderList || orderList.isEmpty()) {
            return null;
        }
        List<Map<String,Object>> list = new ArrayList<>();
        orderList.stream().forEach(l -> {
            OrderRenew renew = renewMapper.queryOrderRenewByOrder(l.getId());
            PlusCar car = carMapper.findById(l.getCarId());
            Map<String,Object> result = new HashMap<>();
            result.put("expireTime",l.getExpireTime());
            result.put("createTime",l.getCreateTime());
            result.put("remainDay",l.getExpireTime() - System.currentTimeMillis()< 0 ? 0 : l.getExpireTime() - System.currentTimeMillis());
            result.put("money",renew.getRenewMoney());
            result.put("useCount",l.getUseCount());
            result.put("totalCount",l.getTotalCount());
            result.put("carNo",car.getCarNo());
            result.put("id",l.getId());
            list.add(result);
        });
        return list;
    }

    @Override
    public UserAuthVo getUserAuth(Long id) {
        PlusUser user = userMapper.queryById(id);
        Integer wash = orderMapper.countOrderByUserIdAndType(id, Consts.PlusOrderType.TYPE_1.getCode());
        Integer nick = orderMapper.countOrderByUserIdAndType(id,Consts.PlusOrderType.TYPE_2.getCode());
        UserAuthVo authVo = new UserAuthVo();
        authVo.setVipType(user.getVipType());
        authVo.setIsWash(wash > 0);
        authVo.setIsNick(nick > 0);
        authVo.setPhone(user.getPhone());
        authVo.setBrokerage(user.getBrokerage());
        authVo.setParentId(user.getParentId());
        if (wash > 0 || nick > 0){
            Long washExpireTime = 0L;
            Long nickExpireTime = 0L;
            PlusOrder washOrder = orderMapper.queryOrderByUserIdAndType(id,Consts.PlusOrderType.TYPE_1.getCode());
            PlusOrder nickOrder = orderMapper.queryOrderByUserIdAndType(id,Consts.PlusOrderType.TYPE_2.getCode());
            if (null != washOrder){
                washExpireTime = washOrder.getExpireTime();
            }
            if (null != nickOrder){
                nickExpireTime = nickOrder.getExpireTime();
            }
            authVo.setRemainDay(washExpireTime > nickExpireTime ? (washExpireTime - System.currentTimeMillis() <= 0) ? 0 : washExpireTime - System.currentTimeMillis()
                    : (nickExpireTime - System.currentTimeMillis() <= 0) ? 0 : nickExpireTime - System.currentTimeMillis());
        }else {
            authVo.setRemainDay(0L);
        }
        return authVo;
    }

    @Override
    public Boolean changePhone(Long id, String phone,Long parentId) {
        PlusUser user = userMapper.queryById(id);
        PlusUser userByPhone = userMapper.queryUserByPhone(phone);
        if (phone.equals(user.getPhone())){
            throw BaseException.build(AuthExceptionConstants.PHONE_EQUAL);
        }
        if (StringUtils.isEmpty(user.getPhone())){
            //绑定手机号
            if (null != userByPhone){
                userByPhone.setCity(user.getCity());
                userByPhone.setBrokerage(user.getBrokerage());
                userByPhone.setOpenid(user.getOpenid());
                userByPhone.setUpdateTime(System.currentTimeMillis());
                if (null != parentId &&  parentId > 0 && parentId != user.getId()){
                    userByPhone.setParentId(parentId);
                }
                userMapper.updatePlusUser(userByPhone);
                userMapper.deleteById(user.getId());
            } else {
                user.setUpdateTime(System.currentTimeMillis());
                user.setPhone(phone);
                if (null != parentId &&  parentId > 0 && parentId != user.getId()){
                    user.setParentId(parentId);
                }
                userMapper.updatePlusUser(user);
            }
        } else {
            //换搬手机号
            user.setUpdateTime(System.currentTimeMillis());
            user.setPhone(phone);
            userMapper.updatePlusUser(user);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean sendSms(String phone,String code) {
        AliSmsUtil.sendSms(aliSmsConfig.getAccessKeyId(),aliSmsConfig.getAccessKeySecret()
                ,phone,aliSmsConfig.getSignName(),aliSmsConfig.getTemplateCode(),AliSmsUtil.TEMPLATE_PARAM.replace("{0}",code));
        return Boolean.TRUE;
    }

    @Override
    public String createToken(SessionParam code) {
        Map<String,Object> map = MicroAuthorize.authorize(config.getAppId(),config.getSecret(),code.getCode());
        if ( null == map ){
            throw BaseException.build(AuthExceptionConstants.MISSING_REDIRECT_URI_PARAMETER);
        }
        String openid = String.valueOf(map.get("openid"));
        PlusUser user = userMapper.queryUserByOpenId(openid);
        if (null == user){
            String key = systemConfig.getValue("tencentMapKey");
            Map<String,String> tencentMap = TencentMapUtil.getAddress(code.getLat(),code.getLng(),key);
            String city = null;
            if (null != tencentMap && !tencentMap.isEmpty() ){
                city = tencentMap.get("city");
            }
            user = new PlusUser();
            user.setOpenid(openid);
            user.setCreateTime(System.currentTimeMillis());
            user.setCity(city);
            userMapper.insertPlusUser(user);
        }
        UserContext context = new UserContext();
        context.setOpenId(openid);
        context.setId(user.getId());
        return jwtTokenProvider.createJwtToken(context);
    }
}
