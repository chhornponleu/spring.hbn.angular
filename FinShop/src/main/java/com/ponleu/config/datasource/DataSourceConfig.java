package com.ponleu.config.datasource;

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
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableCaching
@EnableTransactionManagement
@ComponentScan(basePackages = { 
	"com.ponleu.app.daos"
})
@PropertySource(value = { "classpath:application.properties" })
public class DataSourceConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

	@Inject
	private Environment env;

	public DataSourceConfig() {
	}

	@Bean
	public DataSource dataSource() throws PropertyVetoException, JsonParseException, JsonMappingException, IOException {
		LOGGER.debug("Initializing database connection....");
		
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(this.env.getProperty("datasource.jdbc.url"));
		config.setDriverClassName(this.env.getProperty("datasource.jdbc.driverClassName"));
		config.setUsername(this.env.getProperty("datasource.jdbc.username"));
		config.setPassword(this.env.getProperty("datasource.jdbc.password"));
		config.setMaximumPoolSize(Integer.parseInt(this.env.getProperty("datasource.jdbc.maxPoolSize")));
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		
		return new HikariDataSource(config);
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
		properties.put("hibernate.cache.use_second_level_cache", this.env.getProperty("datasource.hibernate.second_level_cache"));
		properties.put("hibernate.cache.use_query_cache", this.env.getProperty("datasource.hibernate.use_query_cache"));
		properties.put("hibernate.cache.region.factory_class", this.env.getProperty("datasource.hibernate.factory_class"));
		properties.put("hibernate.generate_statistics", this.env.getProperty("datasource.hibernate.generate_statistics"));
		properties.put("hibernate.connection.characterEncoding", "UTF-8");
		properties.put("hibernate.connection.charSet", "UTF-8");
		//properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

}
