package org.talend.esbconsole.server.web.api.controller.joblog.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据库表 log_catcher 对应的实体类
 * 此表记录了esb任务模型中节点的异常日志
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class LogCatcherVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 时间
     */
    private String moment;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 进程ID
     */
    private String pid;

    /**
     * 根进程ID
     */
    private String rootPid;

    /**
     * 父进程ID
     */
    private String fatherPid;

    /**
     * 项目
     */
    private String project;

    /**
     * 任务
     */
    private String job;

    /**
     * 上下文
     */
    private String context;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 类型
     */
    private String type;

    /**
     * 来源
     */
    private String origin;

    /**
     * 消息
     */
    private String message;

    /**
     * 状态码
     */
    private Integer code;

}