package com.ponleu.config.context;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityCtxConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityCtxConfig.class);
	private static final String FAVICON_URL = "/favicon.ico";
	private static final String SUCCESS_URL = "/";
	private static final String FAILURE_URL = "/user/login?failure=true";
	private static final String LOGIN_URL = "/user/login";
	private static final String LOGIN_PROCESS_URL = "/user/authenticate";
	private static final String DEFAULT_SUCCESS_URL = "/";
	private static final String USER_NAME_PARAMETER = "username";
	private static final String PASSWORD_PARAMETER = "password";
	private static final String LOGOUT_URL = "/user/logout";
	private static final String LOGOUT_SUCCESS_URL = "/user/logout/success";
	private static final String INVALID_SESSION_URL = "/auth/invalid_session";
	private static final String SESSION_EXPIRED_URL = "/auth/expired";
	
	@Inject
	private Environment env;

	@Inject
	@Qualifier("jdbcAuthenticationPropvider")
	private AuthenticationProvider jdbcAuthenticationPropvider;
	
//	@Inject 
//	private AjaxTimeoutRedirectFilter ajaxTimeoutRedirectFilter;

	public WebSecurityCtxConfig() {}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOGGER.debug("Initializing http interceptor....");
		http //.addFilterAfter(this.ajaxTimeoutRedirectFilter, ExceptionTranslationFilter.class)
			.authorizeRequests()
				.antMatchers(FAVICON_URL).permitAll()
				.anyRequest().authenticated()
				//.anyRequest().permitAll()
				.and()
			.formLogin()
				.defaultSuccessUrl(SUCCESS_URL)
				.failureUrl(FAILURE_URL)
				.loginPage(LOGIN_URL)
				.loginProcessingUrl(LOGIN_PROCESS_URL)
				.defaultSuccessUrl(DEFAULT_SUCCESS_URL, true)
				.usernameParameter(USER_NAME_PARAMETER)
				.passwordParameter(PASSWORD_PARAMETER)
				.permitAll()
				.and()
			.logout()
				.logoutUrl(LOGOUT_URL)
				.logoutSuccessUrl(LOGOUT_SUCCESS_URL)
				.invalidateHttpSession(true)
				.deleteCookies(this.env.getProperty("security.cookie.name"))
				//;
			.and()
			.sessionManagement()
				.sessionFixation().newSession()
				.invalidSessionUrl(INVALID_SESSION_URL)
				.maximumSessions(Integer.parseInt(this.env.getProperty("security.cookie.maxLoginSession")))
				.expiredUrl(SESSION_EXPIRED_URL);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(this.env.getProperty("mvc.resource.mapping"))
			.antMatchers(this.env.getProperty("mvc.resource.products.mapping"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		LOGGER.debug("Initializing Authentication....");
		auth.authenticationProvider(this.jdbcAuthenticationPropvider);
	}

	
}