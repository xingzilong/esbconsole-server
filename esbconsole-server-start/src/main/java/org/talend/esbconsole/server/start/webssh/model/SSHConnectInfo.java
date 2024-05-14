package org.talend.esbconsole.server.start.webssh.model;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import org.springframework.web.socket.WebSocketSession;

/**
 * ssh连接信息
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class SSHConnectInfo {
    /**
     * websocket会话
     */
    private WebSocketSession webSocketSession;

    /**
     * JSch ssh
     */
    private JSch jSch;

    /**
     * ssh通道，用于输入命令和读取结果
     */
    private Channel channel;


    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    public JSch getjSch() {
        return jSch;
    }

    public void setjSch(JSch jSch) {
        this.jSch = jSch;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
