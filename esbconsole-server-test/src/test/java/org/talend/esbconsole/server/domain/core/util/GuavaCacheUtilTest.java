package org.talend.esbconsole.server.domain.core.util;

import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import static org.mockito.Mockito.*;

/**
 * {@link GuavaCacheUtil} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/12
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class GuavaCacheUtilTest {

    @Mock
    CacheManager guavaCacheManager;

    @InjectMocks
    GuavaCacheUtil guavaCacheUtil;

    @Test
    public void guavaCacheTest() {
        Cache cache = mock(Cache.class);
        when(guavaCacheManager.getCache(Mockito.eq(Constants.TOKEN_CACHE))).thenReturn(cache);
        guavaCacheUtil.guavaCache();

        verify(guavaCacheManager).getCache(Mockito.eq(Constants.TOKEN_CACHE));
    }

    @Test
    public void setUserDetailsTest() {
        String id = "";
        LoginUserDetails loginUserDetails = mock(LoginUserDetails.class);
        Cache cache = mock(Cache.class);

        when(guavaCacheManager.getCache(Mockito.eq(Constants.TOKEN_CACHE))).thenReturn(cache);

        doNothing().when(cache).put(Mockito.any(), Mockito.any());

        guavaCacheUtil.setUserDetails(id, loginUserDetails);
    }

    @Test
    public void getUserDetailsTest() {

        Cache cache = mock(Cache.class);
        when(guavaCacheManager.getCache(Mockito.eq(Constants.TOKEN_CACHE))).thenReturn(cache);

        ValueWrapper valueWrapper = mock(ValueWrapper.class);
        when(cache.get(Mockito.any())).thenReturn(valueWrapper);

        LoginUserDetails loginUserDetails = mock(LoginUserDetails.class);
        when(valueWrapper.get()).thenReturn(loginUserDetails);

        guavaCacheUtil.getUserDetails("");

        verify(guavaCacheManager).getCache(Mockito.eq(Constants.TOKEN_CACHE));
        verify(cache).get(Mockito.any());

        valueWrapper = null;
        when(cache.get(Mockito.any())).thenReturn(valueWrapper);
        guavaCacheUtil.getUserDetails("");

    }

    @Test
    public void removeUserDetailsTest() {
        Cache cache = mock(Cache.class);
        when(guavaCacheManager.getCache(Mockito.eq(Constants.TOKEN_CACHE))).thenReturn(cache);

        doNothing().when(cache).evict(Mockito.any());
        guavaCacheUtil.removeUserDetails("");

        verify(guavaCacheManager).getCache(Mockito.eq(Constants.TOKEN_CACHE));
        verify(cache).evict(Mockito.any());
    }
}
