package org.talend.esbconsole.server.start.interceptor;

import org.talend.esbconsole.server.domain.core.util.GuavaCacheUtil;
import org.talend.esbconsole.server.domain.core.util.LoginUserDetails;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.tools.common.utils.JWTUtil;
import org.talend.esbconsole.server.tools.common.utils.StringUtil;
import org.talend.esbconsole.server.tools.common.utils.UUIDUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * websocket拦截器 即握手阶段
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Component
public class WebSocketInterceptor implements HandshakeInterceptor {

    @Autowired
    private GuavaCacheUtil guavaCacheUtil;

    /**
     * Handler处理前调用
     *
     * @return: boolean
     * @author: xingzilong
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            String token = request.getHeader("Sec-WebSocket-Protocol");
            // 认证失败
            if (!authentication(token)) {
                return false;
            }
            //将uuid放到websocketsession中
            map.put(Constants.WEBSSH_USER_KEY, UUIDUtil.getUUID());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        HttpServletRequest httpRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        HttpServletResponse httpResponse = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
        if (StringUtil.isNotEmpty(httpRequest.getHeader(Constants.CUSTOM_WEBSOCKET_HEADER))) {
            httpResponse.addHeader(Constants.CUSTOM_WEBSOCKET_HEADER, httpRequest.getHeader(Constants.CUSTOM_WEBSOCKET_HEADER));
        }
    }


    private boolean authentication(String token) {
        // token为null或""
        if (StringUtil.isEmpty(token)) {
            return false;
        }
        // 解析token
        String cacheId = null;
        try {
            Claims claims = JWTUtil.parseJWT(token);
            cacheId = claims.getSubject();
        } catch (ExpiredJwtException e) {
            log.warn("JWT过期：" + cacheId);
            return false;
        } catch (Exception e) {
            log.warn("无效token，cacheid：" + cacheId);
            return false;
        }
        // 从Guava Cache中获取用户信息
        LoginUserDetails loginUserDetails = guavaCacheUtil.getUserDetails(cacheId);
        if (Objects.isNull(loginUserDetails)) {
            log.warn("Guava Cache缓存过期，cacheid：" + cacheId);
            return false;
        }
        Set<String> authoritiesRaw = loginUserDetails.getAuthoritiesRaw();
        // 判断用户权限集合中是否存在authority
        return authoritiesRaw.contains("system:karafconsole:websocket");
    }
}
