package org.talend.esbconsole.server.start.webssh.model;

import lombok.Data;

/**
 * webssh客户端发送消息的报文格式
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
public class WebSSHData {
    /**
     * 操作类型，目前有两种 连接操作（初始化ssh连接） 指令操作（建立完ssh连接后的指令操作）
     * connect、command
     */
    private String operation;

    /**
     * 主机ip
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 指令内容
     */
    private String command;

    /**
     * 通信消息内容
     */
    private String message;

    /**
     * 终端字符列数
     */
    private int columns;

    /**
     * 终端字符行数
     */
    private int rows;

    /**
     * 终端宽度
     */
    private int width;

    /**
     * 终端高度
     */
    private int height;
}
