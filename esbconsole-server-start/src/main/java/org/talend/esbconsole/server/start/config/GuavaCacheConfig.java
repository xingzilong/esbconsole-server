package org.talend.esbconsole.server.start.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * GuavaCache相关配置
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Configuration
@EnableCaching
@Slf4j
public class GuavaCacheConfig {

    @Value("${guava.cache.config.concurrency-level}")
    private int concurrencyLevel;

    @Value("${guava.cache.config.max-imum-size}")
    private long maximumSize;

    @Value("${guava.cache.config.expire-after-write}")
    private long expireAfterWrite;

    @Value("${guava.cache.config.expire-after-access}")
    private long expireAfterAccess;

    @Value("${guava.cache.config.refresh-after-write}")
    private long refreshAfterWrite;

    @Value("${guava.cache.config.initial-capacity}")
    private int initialCapacity;

    @Bean
    public CacheManager cacheManager() {
        log.info(">>>>>>Guava Cache初始化配置>>>>>>concurrencyLevel：" + concurrencyLevel);
        log.info(">>>>>>Guava Cache初始化配置>>>>>>maximumSize：" + maximumSize);
        log.info(">>>>>>Guava Cache初始化配置>>>>>>expireAfterWrite：" + expireAfterWrite);
        log.info(">>>>>>Guava Cache初始化配置>>>>>>expireAfterAccess：" + expireAfterAccess);
        log.info(">>>>>>Guava Cache初始化配置>>>>>>refreshAfterWrite：" + refreshAfterWrite);
        log.info(">>>>>>Guava Cache初始化配置>>>>>>initialCapacity：" + initialCapacity);
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(CacheBuilder.newBuilder()
                        // 设置并发级别为8 并发级别是指可以同事写缓存的线程数
                        .concurrencyLevel(concurrencyLevel)
                        // 设置写缓存后10s过期
                        // .expireAfterWrite(expireAfterWrite, TimeUnit.MINUTES)
                        // 此缓存对象经过多少秒没有被访问则过期。
                        .expireAfterAccess(expireAfterAccess, TimeUnit.MINUTES)
                        // 设置缓存容量的初始容量为10
                        .initialCapacity(initialCapacity)
                        // 设置缓存最大容量为100,超过100之后会按照LRU最近最少使用算法来移除缓存项
                        // .maximumSize(maximumSize)
                        // 设置要统计缓存的命中率
                        .recordStats()
                        // 设置缓存移除通知
                        .removalListener(new RemovalListener<Object, Object>() {
                            @Override
                            public void onRemoval(RemovalNotification<Object, Object> notification) {
                                log.info(notification.getKey() + " was removed, cause is " + notification.getCause());
                            }
                        })
                // 如果缓存过期，恰好有多个线程读取同一个key的值，那么guava只允许一个线程去加载数据，其余线程阻塞。
                // 这虽然可以防止大量请求穿透缓存，但是效率低下。使用refreshAfterWrite可以做到：只阻塞加载数据的线程，其余线程返回旧数据。
                // .refreshAfterWrite(refreshAfterWrite, TimeUnit.MINUTES)
        );
        cacheManager.getCache("token-cache");
        return cacheManager;
    }

}
