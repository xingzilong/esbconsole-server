package org.talend.esbconsole.server.web.api.controller.joblog.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 任务的调用日志
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class JobLogVO {

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
    private String executTime;


}
