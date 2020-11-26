package com.demo.action.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 缓存管理器
 *
 * @author jingLv
 * @date 2020/11/26
 */
@Slf4j
@EnableCaching
@Configuration
public class CaffeineCacheConfig {
    /**
     * CacheManager实现类
     *
     * @return
     */
    @Bean("cacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        // 缓存集合
        ArrayList<CaffeineCache> caches = new ArrayList<>();
        caches.add(new CaffeineCache("users-cache",
                Caffeine.newBuilder()
                        // 指定Key下的最大缓存数据量
                        .maximumSize(1000)
                        // 最后一次访问之后，120秒过期
                        .expireAfterAccess(120, TimeUnit.SECONDS)
                        .build()));
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
