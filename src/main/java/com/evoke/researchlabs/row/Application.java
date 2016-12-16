package com.evoke.researchlabs.row;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * @author apandiri
 * @description WebApplication class is a starting point for triggering spring boot application
 * @version 1.0
 */
@EnableCaching
@Configuration
@SpringBootApplication
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
    	logger.debug("main method started");
        SpringApplication.run(Application.class, args);
        logger.debug("main method ended");
    }
    
    @Bean
	public CacheManager getEhCacheManager(){
	        return  new EhCacheCacheManager(getEhCacheFactory().getObject());
	}
	@Bean
	public EhCacheManagerFactoryBean getEhCacheFactory(){
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factoryBean.setShared(true);
		return factoryBean;
	}
    
    
}
