package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * 建立 spring autoconfigure 的 security configuration
 */
@Configuration
@ComponentScan("com.demo.config")
@EntityScan("com.demo.entity")
@EnableJpaRepositories("com.demo.repository")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(value = "security.filter.enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@EntityScan("${security.scan-entity}")
	@EnableJpaRepositories("${security.scan-repositories}")
	@ConditionalOnProperty(value = "security.auto-scan", havingValue = "true", matchIfMissing = true)
	public static class EntityClass {

	}

	private @Autowired UserPermissionDetail userDetail;
	private @Autowired LoginSuccessHandler login;
	private @Autowired SecurityProperties properties;

	/**
	 * 指定使用自定义的 userDetailsService 还有 PasswordEncoder
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetail).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * http认证的配置，指定认证体系里面采用什么样的认证方式并详细配置
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable()
			.and()
			.cors().disable()
			.csrf().disable()
			.httpBasic().disable()
			.authorizeRequests()
				.antMatchers(properties.getSysPath()).permitAll()
				.anyRequest().authenticated()
			.and().formLogin().permitAll()
				.successHandler(login)
				.failureHandler(login)
			.and().logout().permitAll()
				.logoutSuccessHandler(login)
				.deleteCookies("JSESSIONID")
				.clearAuthentication(true)
			.and().rememberMe().tokenValiditySeconds(15)
			.and().exceptionHandling()
				.authenticationEntryPoint(new Http403ForbiddenEntryPoint());
	}
}
