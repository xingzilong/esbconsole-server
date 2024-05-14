package org.talend.esbconsole.server.start.handler;

import org.talend.esbconsole.server.start.webssh.service.WebSSHService;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

/**
 * WebSSH的WebSocket处理器
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Component
public class WebSSHWebSocketHandler implements WebSocketHandler {

    @Autowired
    private WebSSHService webSSHService;

    /**
     * 用户连接上WebSocket的回调
     *
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("用户:{}，建立websocket连接", webSocketSession.getAttributes().get(Constants.WEBSSH_USER_KEY));
        //调用初始化连接
        webSSHService.initConnection(webSocketSession);
    }

    /**
     * 收到消息的回调
     *
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if (webSocketMessage instanceof TextMessage) {
            // 调用service接收消息
            String payload = ((TextMessage) webSocketMessage).getPayload();
            webSSHService.receiveMessageHandler(payload, webSocketSession);
        } else if (webSocketMessage instanceof BinaryMessage) {

        } else if (webSocketMessage instanceof PongMessage) {

        } else {
            log.error("Unexpected WebSocket message type: " + webSocketMessage);
        }
    }

    /**
     * 出现错误的回调
     *
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        log.error("数据传输错误");
    }


    /**
     * 连接关闭的回调
     *
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("用户:{}，断开websocket连接", String.valueOf(webSocketSession.getAttributes().get(Constants.WEBSSH_USER_KEY)));
        //调用service关闭连接
        webSSHService.close(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
