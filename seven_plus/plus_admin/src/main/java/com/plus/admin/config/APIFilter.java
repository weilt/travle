package com.plus.admin.config;

import com.admin.service.api.UserService;
import com.alibaba.fastjson.JSON;
import com.common.consts.Consts;
import com.common.consts.UserContext;
import com.common.jwt.provider.JwtTokenProvider;
import com.plus.admin.controller.api.result.ResultCode;
import com.plus.admin.controller.api.result.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * api监听
 * @author
 */
public class APIFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(APIFilter.class);

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String token = req.getHeader(Consts.USER_CONTEXT_TOKEN);
		UserContext context = jwtTokenProvider.parseJwtToken(token,UserContext.class);
		boolean existPhone = userService.existPhone(context.getId());
		if (!existPhone){
			res.setContentType("application/json; charset=utf-8");
			res.getWriter().write(JSON.toJSONString(ResultEntity.build(ResultCode.EXIST_NOT_PHONE)));
			return;
		}
		String url = req.getRequestURI();
		String Method = req.getMethod();
		System.out.println(url);
		System.out.println(Method);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}


}
