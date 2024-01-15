package org.talend.esbconsole.server.start.webssh.service;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * webssh业务处理逻辑
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public interface WebSSHService {

    /**
     * 初始化连接信息
     *
     * @param session
     */
    void initConnection(WebSocketSession session);

    /**
     * 接受处理客户端发送的消息
     *
     * @param message 客户端消息
     * @param session session
     */
    void receiveMessageHandler(String message, WebSocketSession session);

    /**
     * 发送消息给客户端
     *
     * @param session
     * @param buffer  消息内容的字节数组
     * @throws IOException
     */
    void sendMessageHandler(WebSocketSession session, byte[] buffer) throws IOException;

    /**
     * 关闭连接
     *
     * @param session
     */
    void close(WebSocketSession session);
}
