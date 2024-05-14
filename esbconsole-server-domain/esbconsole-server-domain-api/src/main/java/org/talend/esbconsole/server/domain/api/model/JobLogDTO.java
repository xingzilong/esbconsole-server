package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 任务的调用日志
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class JobLogDTO {

    /**
     * 服务名称
     */
    private String serviceName;

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

//    /**
//     * 记录列表
//     */
//    private List<StatCatcherDTO> recordList;

}
