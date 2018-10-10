package com.admin.service.api;

import com.domain.plus.param.SessionParam;
import com.domain.plus.vo.UserAuthVo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 14:44
 * @Description:
 */
public interface UserService {

    /**
     * 通过js_code获取jwtToken授权
     * @param code
     * @return
     */
    String createToken(SessionParam code);

    /**
     * 发送短信
     * @param phone
     * @return
     */
    Boolean sendSms(String phone,String code);


    /**
     * 绑定电话号码或者换绑
     * @param id
     * @param phone
     * @return
     */
    Boolean changePhone(Long id, String phone,Long parentId);

    /**
     * 获取用户办理的业务和会员等级
     * @param id
     * @return
     */
    UserAuthVo getUserAuth(Long id);

    /**
     * 获取用户开通的服务
     * @param id
     * @param type
     * @return
     */
    List<Map<String,Object>> findOrderByUserId(Long id, Integer type);

    /**
     * 电话存在
     * @param id
     * @return
     */
    Boolean existPhone(Long id);
}
