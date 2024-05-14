package org.talend.esbconsole.server.domain.core.util;

import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * GuavaCache的工具类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Component
public class GuavaCacheUtil {

    @Autowired
    private CacheManager guavaCacheManager;

    @Bean
    public Cache guavaCache() {
        return guavaCacheManager.getCache(Constants.TOKEN_CACHE);
    }

    public void setUserDetails(String id, LoginUserDetails loginUserDetails) {
        guavaCacheManager.getCache(Constants.TOKEN_CACHE).put(Constants.CACHE_TOKEN_PREFIX + id, loginUserDetails);
    }

    public LoginUserDetails getUserDetails(String id) {
        Cache.ValueWrapper valueWrapper = guavaCacheManager.getCache(Constants.TOKEN_CACHE).get(Constants.CACHE_TOKEN_PREFIX + id);
        return valueWrapper == null ? null : (LoginUserDetails) valueWrapper.get();
    }

    public void removeUserDetails(String id) {
        guavaCacheManager.getCache(Constants.TOKEN_CACHE).evict(Constants.CACHE_TOKEN_PREFIX + id);
    }
}
