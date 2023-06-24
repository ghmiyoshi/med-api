package br.com.alura.med.config.cache;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import static javax.management.timer.Timer.ONE_MINUTE;

@Slf4j
@AllArgsConstructor
@Configuration
public class CachingConfig {

    public static final String MEDICOS = "medicos";

    private CacheManager cacheManager;

    @Scheduled(fixedRate = ONE_MINUTE)
    public void evictAllCachesAtIntervals() {
        log.info("{}::evictAllCachesAtIntervals - Limpando caches", getClass().getSimpleName());
        cacheManager.getCacheNames().forEach(cache -> cacheManager.getCache(cache).clear());
    }

}
