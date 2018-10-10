package com.hx.api.handler;

import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.core.redis.RedisCache;
import com.hx.core.utils.GsonUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @Auther: Zhoudu
 * @Date: 2018/6/27 11:28
 * @Description: 搜索关键字拦截
 */
public class SreachKeyInterceptHandler extends HandlerInterceptorAdapter {


    /**
     * 拦截请求前缀
     */
    public static final String PRE_INTERCEPT_URI = "/scenic";

    /**
     * 拦截参数名称
     */
    public static final String INTERCEPT_PARAMETER_NAME = "searchKey";

    /**
     * 用户信息
     */
    public static final String TOKE_KEY = "token";



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        if (uri.indexOf(PRE_INTERCEPT_URI) != -1){
            Enumeration<String> enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()){
                String requestParamName = enumeration.nextElement();
                if (requestParamName.equalsIgnoreCase(INTERCEPT_PARAMETER_NAME)){
                    //查询关键字
                    String searchValue = request.getParameter(requestParamName);
                    String toke =  request.getParameter(TOKE_KEY);
                    //用户信息
                    LoginVo user = RedisCache.get(toke) != null? GsonUtil.fromJson(RedisCache.get(toke), LoginVo.class): null;
                    //TODO 通过策略记录关键字  或者  记录用户搜索历史

                    break;
                }
            }
        }

        return true;
    }

}
