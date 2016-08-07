package com.ponleu.shopcommerce.test.context;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableCaching
@EnableTransactionManagement
@ComponentScan(basePackages = { 
	"com.ponleu.shopcommerce.app.*.dao",
	"com.ponleu.shopcommerce.app.*.services"
})
@PropertySource(value = { "classpath:application.properties" })
public class ApplicationTestInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTestInitializer.class);

	@Inject
	private Environment env;

	public ApplicationTestInitializer() {
	}

	@Bean
	public DataSource dataSource() throws PropertyVetoException, JsonParseException, JsonMappingException, IOException {
		LOGGER.debug("Initializing database connection....");
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(this.env.getProperty("datasource.jdbc.driverClassName"));
		dataSource.setJdbcUrl(this.env.getProperty("datasource.jdbc.url"));
		dataSource.setMinPoolSize(Integer.parseInt(this.env.getProperty("datasource.jdbc.minPoolSize")));
		dataSource.setMaxPoolSize(Integer.parseInt(this.env.getProperty("datasource.jdbc.maxPoolSize")));
		dataSource.setInitialPoolSize(Integer.parseInt(this.env.getProperty("datasource.jdbc.initialPoolSize")));
		dataSource.setMaxIdleTime(Integer.parseInt(this.env.getProperty("datasource.jdbc.maxIdleTime")));
		dataSource.setMaxConnectionAge(Integer.parseInt(this.env.getProperty("datasource.jdbc.maxConnectionAge")));
		dataSource.setPassword("12345");
		dataSource.setUser("root");
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager()
			throws JsonParseException, JsonMappingException, PropertyVetoException, IOException {
		
		LOGGER.debug("Initializing Hibernate transaction manager....");
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setDataSource(this.dataSource());
		transactionManager.setSessionFactory(this.localSessionFactoryBean().getObject());
		
		LOGGER.debug("End initializing Hibernate transaction manager....");
		
		return transactionManager;
	}

	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean()
			throws JsonParseException, JsonMappingException, PropertyVetoException, IOException {
		LOGGER.debug("Initializing LocalSessionFactoryBean....");
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(this.dataSource());
		sessionFactoryBean.setPackagesToScan(this.env.getProperty("datasource.model.packagesToScan"));
		sessionFactoryBean.setHibernateProperties(this.hibernateProperties());
		return sessionFactoryBean;
	}
	
	@Bean
	public CacheManager cacheManager() {
		LOGGER.debug("Initializing ehcache manager....");
		return new EhCacheCacheManager(this.ehCacheManagerFactoryBean().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		LOGGER.debug("Initializing ehcache manager factory bean....");
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean
				.setConfigLocation(new ClassPathResource(this.env.getProperty("datasource.ehcache.configLocation")));
		ehCacheManagerFactoryBean.setShared(Boolean.parseBoolean(this.env.getProperty("datasource.ehcache.share")));
		return ehCacheManagerFactoryBean;
	}

	private Properties hibernateProperties() {
		LOGGER.debug("Initializing Hibernate properties....");
		Properties properties = new Properties();
		properties.put("hibernate.dialect", this.env.getProperty("datasource.hibernate.dialect"));
		properties.put("hibernate.show_sql", this.env.getProperty("datasource.hibernate.show_sql"));
		properties.put("hibernate.format_sql", this.env.getProperty("datasource.hibernate.format_sql"));
		properties.put("hibernate.cache.use_second_level_cache",
				this.env.getProperty("datasource.hibernate.second_level_cache"));
		properties.put("hibernate.cache.use_query_cache", this.env.getProperty("datasource.hibernate.use_query_cache"));
		properties.put("hibernate.cache.region.factory_class",
				this.env.getProperty("datasource.hibernate.factory_class"));
		properties.put("hibernate.generate_statistics",
				this.env.getProperty("datasource.hibernate.generate_statistics"));
		properties.put("hibernate.connection.characterEncoding", "UTF-8");
		properties.put("hibernate.connection.charSet", "UTF-8");
		//properties.put("hibernate.hbm2ddl.auto", "validate");
		return properties;
	}

}
