package com.plus.admin.controller.api;

import com.admin.service.api.UserService;
import com.common.cache.Cache;
import com.common.consts.UserContext;
import com.common.sms.AliSmsUtil;
import com.common.util.UUIDHelper;
import com.domain.plus.param.SessionParam;
import com.domain.plus.param.SmsParam;
import com.plus.admin.controller.api.result.ResultCode;
import com.plus.admin.controller.api.result.ResultCollection;
import com.plus.admin.controller.api.result.ResultEntity;
import com.plus.admin.controller.base.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 14:32
 * @Description: 小程序获取token
 */
@Api(value = "API - SessionController", description = "小程序授权")
@RestController
public class SessionController extends BaseApiController {

    @Autowired
    private UserService userService;

    /**
     * 用户授权获取token
     * @return
     */
    @ApiOperation(value = "用户授权获取token" , notes = "用户授权获取token", response = ResultEntity.class)
    @PostMapping("/create_token")
    public ResultEntity<String> createToken(@RequestBody SessionParam code){
        String jwtToken = userService.createToken(code);
        return ResultEntity.build(jwtToken);
    }


    @ApiOperation(value = "发送短信" , notes = "发送短信", response = ResultEntity.class)
    @PostMapping("/sendSms")
    public ResultEntity sendSms(@RequestBody SmsParam param){
        UserContext context = getUserContext();
        //验证码
        String code = UUIDHelper.randomNumber();
        Cache.put(context.getId()+"",code);
        userService.sendSms(param.getPhone(),code);
        return ResultEntity.build(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "修改电话号码（绑定电话）" , notes = "修改电话号码（绑定电话)", response = ResultEntity.class)
    @PostMapping("/changePhone")
    public ResultEntity changePhone(@RequestBody SmsParam param){
        UserContext context = getUserContext();
        String code = Cache.get(context.getId()+"",String.class);
        if (null != param.getCode() && code.equals(param.getCode())){
            //修改电话号码
            userService.changePhone(context.getId(),param.getPhone(),param.getId());
            return ResultEntity.build(ResultCode.SUCCESS);
        } else {
            return ResultEntity.build(ResultCode.VERIFICATION_CODE_ERR);
        }

    }

}
