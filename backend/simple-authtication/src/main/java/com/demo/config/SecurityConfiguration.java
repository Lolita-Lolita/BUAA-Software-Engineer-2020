package com.demo.config;

import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import com.demo.service.database.DataService;
import com.demo.service.remote.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
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
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(value = "security.filter.enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private @Autowired UserPermissionDetail userDetail;
	private @Autowired LoginSuccessHandler login;
	private @Autowired SecurityProperties properties;

	@EntityScan("com.demo.entity")
	@EnableJpaRepositories("com.demo.repository")
	@ConditionalOnMissingBean(UserService.class)
	@ConditionalOnProperty(value = "security.auth.type", havingValue = "DATABASE", matchIfMissing = true)
	public static class DataConfiguration {

		public @Bean UserService dataBaseService(@Autowired UserRepository repository) {
			return new DataService(repository);
		}
	}

	@EntityScan("${security.scan-entity}")
	@EnableJpaRepositories("${security.scan-repositories}")
	@ConditionalOnMissingBean(UserService.class)
	@ConditionalOnExpression(value = "'${security.auth.type:DATABASE}'.equals('DATABASE') && ${security.auto-scan:true}")
	public static class EntityClass {

	}

	@ConditionalOnProperty(value = "security.auth.type", havingValue = "REMOTE")
	@ConditionalOnMissingBean(UserService.class)
	public @Bean UserService remoteService(@Autowired(required = false) LoadBalancerClient lc, @Autowired SecurityProperties properties) {
		return new RemoteService(lc, properties);
	}


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
				.usernameParameter("userName")
				.successHandler(login)
				.failureHandler(login)
			.and().logout().permitAll()
				.logoutSuccessHandler(login)
				.deleteCookies("JSESSIONID")
				.clearAuthentication(true)
			.and().rememberMe().tokenValiditySeconds(7200)
			.and().exceptionHandling()
				.authenticationEntryPoint(new Http403ForbiddenEntryPoint());
	}
}
