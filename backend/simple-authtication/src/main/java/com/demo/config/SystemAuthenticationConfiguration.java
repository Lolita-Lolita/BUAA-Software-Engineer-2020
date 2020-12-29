package com.demo.config;



import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import lombok.extern.slf4j.Slf4j;

/**
 * AOP的例子，在此sdk中使用只为进行系统认证的一种方案
 */
@EnableConfigurationProperties(SecurityProperties.class)
@Component
@Aspect
@Slf4j
public class SystemAuthenticationConfiguration {

	private @Autowired SecurityProperties properties;
	
	@Pointcut("@annotation(com.demo.config.annotation.Authority)")
	public void auth() {
	}
	
	@Around("auth()")
	public ResponseEntity<?> verifyToken(ProceedingJoinPoint point) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token = request.getHeader("service-id");
		try {
			if (verify(token)) {
				return (ResponseEntity<?>) point.proceed();
			} else {
				log.error("no permission");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(
						ForbiddenDTO.builder()
							.timestamp(new Date())
							.status(HttpStatus.FORBIDDEN.value())
							.error("Forbidden")
							.message("Access Denied")
							.path(request.getServletPath())
							.build()
				);
				
	}

	//验证 token 的具体方案，需要自己根据设计来实现
	private boolean verify(String token) {
		return Arrays.asList(properties.getWhiteList()).contains(token);
	}
}
