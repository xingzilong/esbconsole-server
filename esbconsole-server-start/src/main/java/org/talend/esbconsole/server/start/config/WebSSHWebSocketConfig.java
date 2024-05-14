package org.talend.esbconsole.server.start.config;

import org.talend.esbconsole.server.start.handler.WebSSHWebSocketHandler;
import org.talend.esbconsole.server.start.interceptor.WebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * websocket相关配置
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Configuration
@EnableWebSocket
public class WebSSHWebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSSHWebSocketHandler webSSHWebSocketHandler;

    @Autowired
    private WebSocketInterceptor webSocketInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //socket通道
        //指定处理器和路径
        webSocketHandlerRegistry.addHandler(webSSHWebSocketHandler, "/api/ws/karafConsole")
                .addInterceptors(webSocketInterceptor)
                .setAllowedOrigins("*");
    }
}
