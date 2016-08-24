package com.ponleu.config.context;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { 
	"com.ponleu.app.controllers",
	"com.ponleu.app.apis"
})
@PropertySource(value = "classpath:application.properties")
public class WebMvcCtxConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcCtxConfig.class);

	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean COOKIE_HTTP_ONLY = true;

	@Inject
	private Environment env;

	public WebMvcCtxConfig() {
		LOGGER.info("Initializing Web Mvc Config...");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(env.getProperty("mvc.resource.mapping"))
				.addResourceLocations(env.getProperty("mvc.resource.location")).setCachePeriod(0);
		
		registry.addResourceHandler(env.getProperty("mvc.resource.products.mapping"))
				.addResourceLocations("file:" + env.getProperty("mvc.resource.products.location"));
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping(this.env.getProperty("mvc.cors.mapping"));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LOGGER.debug("Initializing interceptor....");
		registry.addInterceptor(this.interceptor());
	}

	@Bean
	public HandlerInterceptor interceptor() {
		LOGGER.debug("Initializing locale change interceptor....");
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName(this.env.getProperty("mvc.locale.paramName"));
		return interceptor;
	}

	@Bean
	public LocaleResolver localeResolver() {
		LOGGER.debug("Initializing locale resolver....");
		boolean isCookieResolver = Boolean.parseBoolean(this.env.getProperty("mvc.locale.cookie"));
		if (isCookieResolver) {
			LOGGER.debug("Initializing cookie locale resolver....");
			CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
			cookieLocaleResolver.setDefaultLocale(this.getDefaultLocale());
			cookieLocaleResolver.setCookieName(this.env.getProperty("mvc.locale.paramName"));
			cookieLocaleResolver.setCookieHttpOnly(COOKIE_HTTP_ONLY);
			return cookieLocaleResolver;
		} else {
			LOGGER.debug("Initializing session locale resolver....");
			SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
			sessionLocaleResolver.setDefaultLocale(this.getDefaultLocale());
			return sessionLocaleResolver;
		}
	}

	private Locale getDefaultLocale() {
		return new Locale(this.env.getProperty("mvc.locale.default"));
	}

	@Bean
	public MultipartResolver multipartResolver() throws IOException {
		LOGGER.debug("Initializing multipart resolver....");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding(DEFAULT_ENCODING);
		multipartResolver.setMaxInMemorySize(Integer.parseInt(this.env.getProperty("mvc.upload.maxInMemorySize")));
		multipartResolver.setMaxUploadSize(Long.parseLong(this.env.getProperty("mvc.upload.maxUploadSize")));
		multipartResolver.setMaxUploadSizePerFile(Long.parseLong(this.env.getProperty("mvc.upload.maxUploadSizePerFile")));
		multipartResolver.setResolveLazily(Boolean.parseBoolean(this.env.getProperty("mvc.upload.resolveLazily")));
		return multipartResolver;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper());
		converters.add(converter);
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Hibernate5Module());
		return objectMapper;
	}
}
