package org.talend.esbconsole.server.start.webssh.service.impl;

import com.alibaba.fastjson2.JSON;
import org.talend.esbconsole.server.start.webssh.model.SSHConnectInfo;
import org.talend.esbconsole.server.start.webssh.model.WebSSHData;
import org.talend.esbconsole.server.start.webssh.service.WebSSHService;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.tools.common.utils.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * webssh业务处理逻辑接口的 {@link WebSSHService} 的实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Service
public class WebSSHServiceImpl implements WebSSHService {
    /**
     * 存储ssh连接信息的map。 待优化-应该写一个单例对象去存储 xingzilong 2024-01-15
     */
    private static Map<String, Object> sshMap = new ConcurrentHashMap<>();

    /**
     * 线程池 待优化  xingzilong 2021-09-20
     */
    private ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * ssh主机
     */
    @Value("${karaf.console.ssh.host}")
    private String sshHost;

    /**
     * 端口
     */
    @Value("${karaf.console.ssh.port}")
    private Integer sshPort;

    /**
     * 用户
     */
    @Value("${karaf.console.ssh.username}")
    private String sshUsernmae;

    /**
     * 密码
     */
    @Value("${karaf.console.ssh.password}")
    private String sshPassword;

    /**
     * 字符集编码
     */
    @Value("${karaf.console.ssh.charset}")
    private String charset;


    @Override
    public void initConnection(WebSocketSession session) {
        JSch jSch = new JSch();
        SSHConnectInfo sshConnectInfo = new SSHConnectInfo();
        sshConnectInfo.setjSch(jSch);
        sshConnectInfo.setWebSocketSession(session);
        String uuid = String.valueOf(session.getAttributes().get(Constants.WEBSSH_USER_KEY));
        // 将这个ssh连接信息放入map中
        sshMap.put(uuid, sshConnectInfo);
    }

    @Override
    public void receiveMessageHandler(String buffer, WebSocketSession session) {
        ObjectMapper objectMapper = new ObjectMapper();
        WebSSHData webSSHData = null;
        try {
            webSSHData = objectMapper.readValue(buffer, WebSSHData.class);
        } catch (IOException e) {
            log.error("Json转换异常，异常信息:{}", e.getMessage());
            return;
        }
        String userId = String.valueOf(session.getAttributes().get(Constants.WEBSSH_USER_KEY));
        if (Constants.WEBSSH_OPERATION_CONNECT.equals(webSSHData.getOperation())) {
            if (StringUtil.isEmpty(webSSHData.getHost())) {
                webSSHData.setHost(sshHost);
                webSSHData.setPort(sshPort);
                webSSHData.setUsername(sshUsernmae);
                webSSHData.setPassword(sshPassword);
            }
            // 找到刚才存储的ssh连接对象
            SSHConnectInfo sshConnectInfo = (SSHConnectInfo) sshMap.get(userId);
            // 启动线程异步处理
            WebSSHData finalWebSSHData = webSSHData;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        connectToSSH(sshConnectInfo, finalWebSSHData, session);
                    } catch (JSchException | IOException e) {
                        log.error("webssh连接异常（初始化连接），异常信息:{}", e.getMessage());
                        close(session);
                    }
                }
            });
        } else if (Constants.WEBSSH_OPERATION_COMMAND.equals(webSSHData.getOperation())) {
            String command = webSSHData.getCommand();
            SSHConnectInfo sshConnectInfo = (SSHConnectInfo) sshMap.get(userId);
            if (sshConnectInfo != null) {
                try {
                    transmitToSSH((ChannelShell) sshConnectInfo.getChannel(), webSSHData);
                } catch (IOException e) {
                    log.error("webssh连接异常（发送指令），异常信息:{}", e.getMessage());
                    close(session);
                }
            }
        } else {
            log.error("不支持的操作");
            close(session);
        }
    }

    @Override
    public void sendMessageHandler(WebSocketSession session, byte[] buffer) throws IOException {
        WebSSHData webSSHData = new WebSSHData();
        webSSHData.setMessage(new String(buffer, charset));
        TextMessage textMessage = new TextMessage(JSON.toJSONString(webSSHData));
        session.sendMessage(textMessage);
    }

    @Override
    public void close(WebSocketSession session) {
        String userId = String.valueOf(session.getAttributes().get(Constants.WEBSSH_USER_KEY));
        SSHConnectInfo sshConnectInfo = (SSHConnectInfo) sshMap.get(userId);
        if (sshConnectInfo != null) {
            // 断开连接
            if (sshConnectInfo.getChannel() != null) sshConnectInfo.getChannel().disconnect();
            // map中移除
            sshMap.remove(userId);
        }
    }

    /**
     * 使用jsch连接终端
     *
     * @param sshConnectInfo   ssh连接信息对象
     * @param webSSHData       连接信息存储对象 包含ssh连接信息
     * @param webSocketSession
     * @throws JSchException
     * @throws IOException
     */
    private void connectToSSH(SSHConnectInfo sshConnectInfo, WebSSHData webSSHData, WebSocketSession webSocketSession) throws JSchException, IOException {
        Session session = null;
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        // 获取jsch的会话
        session = sshConnectInfo.getjSch().getSession(webSSHData.getUsername(), webSSHData.getHost(), webSSHData.getPort());
        session.setConfig(config);
        // 设置密码
        session.setPassword(webSSHData.getPassword());
        // 连接  超时时间30s
        session.connect(30000);

        // 开启shell通道
        Channel channel = session.openChannel("shell");

        ChannelShell channelShell = (ChannelShell) channel;
        channelShell.setPtySize(webSSHData.getColumns(), webSSHData.getRows(), webSSHData.getWidth(), webSSHData.getHeight());

        // 通道连接 超时时间3s
        channelShell.connect(3000);

        // 设置channel
        sshConnectInfo.setChannel(channelShell);

        // 读取终端返回的信息流
        InputStream inputStream = channelShell.getInputStream();
        try {
            // 循环读取
            byte[] buffer = new byte[1024];
            int i = 0;
            // 如果没有数据来，线程会一直阻塞在这个地方等待数据。
            while ((i = inputStream.read(buffer)) != -1) {
                sendMessageHandler(webSocketSession, Arrays.copyOfRange(buffer, 0, i));
            }

        } finally {
            log.info("ssh被关闭");
            // 断开连接后关闭会话
            session.disconnect();
            channelShell.disconnect();
            inputStream.close();

//            byte[] bufferPrefix = new byte[]{27,91,63,50,48,48,52,104};
//            byte[] bufferData = "logout bye".getBytes();
//            byte[] buffer = new byte[bufferPrefix.length + bufferData.length];
//
//            System.arraycopy(bufferPrefix, 0, buffer, 0, bufferPrefix.length);
//            System.arraycopy(bufferData, 0, buffer, bufferPrefix.length, bufferData.length);
//            sendMessageHandler(webSocketSession, buffer);
            // 不知道为啥前端不显示
//            sendMessageHandler(webSocketSession, "logout bye".getBytes());
            webSocketSession.close();
        }

    }

    /**
     * 向ssh发送指令
     *
     * @param channel ssh通信管道
     * @param webSSHData 指令
     * @throws IOException
     */
    private void transmitToSSH(ChannelShell channel, WebSSHData webSSHData) throws IOException {
        if (channel != null) {
//            channel.setPtySize(webSSHData.getColumns(), webSSHData.getRows(), webSSHData.getWidth(), webSSHData.getHeight());
            OutputStream outputStream = channel.getOutputStream();
            outputStream.write(webSSHData.getCommand().getBytes());
            outputStream.flush();
        }
    }
}
