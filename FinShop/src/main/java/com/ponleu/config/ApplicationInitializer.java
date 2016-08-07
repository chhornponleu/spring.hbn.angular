package com.ponleu.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Properties;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.ponleu.config.context.WebMvcCtxConfig;
import com.ponleu.config.context.WebRootCtxConfig;
import com.ponleu.config.context.WebSecurityCtxConfig;
import com.ponleu.config.datasource.DataSourceConfig;

@Configuration
public class ApplicationInitializer implements WebApplicationInitializer {

	private static final String PROPERTIES_FILE = "/application.properties";
	private static final String SERVLET_NAME = "ShopCommerce";
	private static final String SERVLET_MAPPING_URL = "/";
	private static final String LOAD_ON_STARTUP = "1";
	private static final String COOKIE_NAME = "JCOOKIE";
	private static final String ENCODING = "UTF-8";
	private static final boolean FORCE_ENCODING = true;
	private static final boolean IS_MATCH_AFTER = true;

	private static final String ENCODING_FILTER_NAME = "characterEncoding";
	private static final String ENCODING_FILTER_URL = "/*";

	private InputStream file;
	protected Properties properties;

	public ApplicationInitializer() {
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		this.loadPropertiesFile();
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
		
		if (this.enabledEncodingFilter()) {
			CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
			characterEncodingFilter.setEncoding(ENCODING);
			characterEncodingFilter.setForceEncoding(FORCE_ENCODING);
			FilterRegistration.Dynamic characterEncoding = servletContext.addFilter(
					this.properties.getProperty("mvc.characterEncoding.name", ENCODING_FILTER_NAME),
					characterEncodingFilter);
			characterEncoding.addMappingForUrlPatterns(dispatcherTypes, IS_MATCH_AFTER,
					this.properties.getProperty("mvc.characterEncoding.url", ENCODING_FILTER_URL));
		}

		if (this.enabledContextRoot()) {
			AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
			rootContext.register(this.registerContextRoot());
			servletContext.addListener(new ContextLoaderListener(rootContext));
		}
		this.init(servletContext);
	}

	protected void init(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		mvcContext.register(this.registerContextMvc());
		ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet(
				this.properties.getProperty("mvc.servlet.name", SERVLET_NAME), 
				new DispatcherServlet(mvcContext));
		
		dispatcherServlet.setLoadOnStartup(Integer.parseInt(this.properties.getProperty("mvc.load.onStartup", LOAD_ON_STARTUP)));
		dispatcherServlet.addMapping(this.properties.getProperty("mvc.mapping.url", SERVLET_MAPPING_URL));
		servletContext.getSessionCookieConfig().setName(this.properties.getProperty("mvc.cookie.name", COOKIE_NAME));
	}
	
	protected void loadPropertiesFile() {
		this.file = this.getClass().getResourceAsStream(PROPERTIES_FILE);
		this.properties = new Properties();
		try {
			this.properties.load(this.file);
			this.file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected boolean enabledContextRoot() {
		return true;
	};

	protected boolean enabledEncodingFilter() {
		return false;
	};

	protected Class<?>[] registerContextMvc() {
		return new Class<?>[] { WebMvcCtxConfig.class };
	};

	protected Class<?>[] registerContextRoot() {
		return new Class<?>[] { WebRootCtxConfig.class, DataSourceConfig.class, WebSecurityCtxConfig.class };
	};

}
