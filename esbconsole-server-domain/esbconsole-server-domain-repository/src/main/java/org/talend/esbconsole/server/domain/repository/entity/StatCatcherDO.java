package org.talend.esbconsole.server.domain.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据库表 stat_catcher 对应的实体类
 * 此表记录了esb任务模型中节点的运行日志
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class StatCatcherDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 时间
     */
    private LocalDateTime moment;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 进程ID
     */
    private String pid;

    /**
     * 父进程ID
     */
    private String fatherPid;

    /**
     * 根进程ID
     */
    private String rootPid;

    /**
     * 系统进程ID
     */
    private Long systemPid;

    /**
     * 项目
     */
    private String project;

    /**
     * 任务
     */
    private String job;

    /**
     * 任务库ID
     */
    private String jobRepositoryId;

    /**
     * 任务版本
     */
    private String jobVersion;

    /**
     * 上下文
     */
    private String context;

    /**
     * 来源
     */
    private String origin;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 消息
     */
    private String message;

    /**
     * 持续时间
     */
    private Long duration;

}