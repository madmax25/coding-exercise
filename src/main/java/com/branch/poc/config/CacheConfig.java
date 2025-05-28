package com.branch.poc.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${cache.userInfo.ttl.in.seconds}")
    private int cacheUserInfoTTLinSeconds;

    @Bean
    public CaffeineCacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("userInfo");
        cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(Duration.ofSeconds(cacheUserInfoTTLinSeconds)));
        return cacheManager;
    }
}
