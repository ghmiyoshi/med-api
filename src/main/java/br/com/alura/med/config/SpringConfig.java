package br.com.alura.med.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@Configuration
public class SpringConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CachingConfig.MEDICOS);
    }
}
