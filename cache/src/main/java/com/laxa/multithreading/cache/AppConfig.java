package com.laxa.multithreading.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;

/**
 * Created by alex4 on 2/22/17.
 */
@Configuration
@EnableCaching
@ComponentScan({"com.laxa.multithreading.cache.*"})
public class AppConfig {

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new PathResource("/Users/alex4/git/multithreading/cache/src/main/resource/ehcache.xml"));
        factoryBean.setShared(true);
        return factoryBean;
    }
}