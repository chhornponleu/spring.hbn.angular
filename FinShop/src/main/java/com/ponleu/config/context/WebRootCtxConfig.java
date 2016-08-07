package com.ponleu.config.context;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ponleu.config.filters.AjaxTimeoutRedirectFilter;
import com.ponleu.config.security.JDBCAuthenticationProvider;

@Configuration
@ComponentScan(basePackages = { 
	"com.ponleu.app.services" 
})
@PropertySource(value = { "classpath:application.properties" })
public class WebRootCtxConfig {

	private static final Logger LOGGER = Logger.getLogger(WebRootCtxConfig.class);
	private static final String DEFAULT_MESSAGE_ENCODING = "UTF-8";

	@Inject
	private Environment env;

	@Bean
	public MessageSource messageSource() {
		LOGGER.debug("Initializing message source....");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(this.env.getProperty("mvc.messageSource.basename"));
		messageSource.setDefaultEncoding(DEFAULT_MESSAGE_ENCODING);
		messageSource.setCacheMillis(10);
		
		return messageSource;
	}
	
	@Bean(name = "bcryptPasswordEncoder")
	public PasswordEncoder passwordEncoder() {
		LOGGER.debug("Initializing BCryptPasswordEncoder....");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		LOGGER.debug("Initializing HibernateExceptionTranslator....");
		return new HibernateExceptionTranslator();
	}

	@Bean(name = "jdbcAuthenticationPropvider")
	public AuthenticationProvider jdbcAuthenticationPropvider() {
		return new JDBCAuthenticationProvider();
	}

	@Bean
	public AjaxTimeoutRedirectFilter ajaxTimeoutRedirectFilter() {
		return new AjaxTimeoutRedirectFilter();
	}
}
