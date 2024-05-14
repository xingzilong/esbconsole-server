package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 定时任务数据分析
 *
 * @author xingzilong
 * @date 2021/08/24
 **/
@NoArgsConstructor
@Data
public class TimedTaskTableDTO {

    /**
     * 此次执行记录的uuid
     */
    private String uuid;

    /**
     * 任务名称
     */
    private String job;

    /**
     * 运行状态
     */
    private String status;

    /**
     * 运行耗时
     */
    private Long time;

    /**
     * 运行时间
     */
    private LocalDateTime executTime;
}
