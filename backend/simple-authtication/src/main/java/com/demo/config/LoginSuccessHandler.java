package com.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 认证成功，失败还有登出成功的自定义策略
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler, 
AuthenticationFailureHandler, LogoutSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String sessionId = request.getSession().getId();
		response.setStatus(HttpStatus.OK.value());
		response.getWriter().println("{\"session\":\""+sessionId+"\"}");

		String header = response.getHeader("Set-Cookie");
		int index;
		if(header !=null && (index = header.indexOf("HttpOnly"))>0) {
			header = header.substring(0,index - 1);
			response.setHeader("Set-Cookie",header);
		}
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.FORBIDDEN.value());
		
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.setStatus(HttpStatus.OK.value());	
	}

}
