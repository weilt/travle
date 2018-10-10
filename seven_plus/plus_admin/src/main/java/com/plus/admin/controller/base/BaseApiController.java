package com.plus.admin.controller.base;

import com.alibaba.fastjson.JSON;
import com.common.consts.Consts;
import com.common.consts.UserContext;
import com.common.jwt.provider.JwtTokenProvider;
import com.common.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 09:14
 * @Description: API 公共接口
 */
public abstract class BaseApiController {

    protected static  final Logger LOGGER = LoggerFactory.getLogger(BaseApiController.class);


    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected  JwtTokenProvider jwtTokenProvider;

    /**
     * 通过token获取用户信息
     * @return
     */
    protected UserContext getUserContext(){
        String token = request.getHeader(Consts.USER_CONTEXT_TOKEN);
        return jwtTokenProvider.parseJwtToken(token,UserContext.class);
    }

    /**
     *  获取客户端Ip
     * @return
     */
    protected String getClientIp() {
        return ObjectHelper.getIpAddress(request);
    }
}
