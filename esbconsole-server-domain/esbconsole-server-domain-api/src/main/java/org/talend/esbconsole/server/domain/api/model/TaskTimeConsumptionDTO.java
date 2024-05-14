package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 定时任务分析耗时统计
 *
 * @author xingzilong
 * @date 2021/08/23
 **/
@Data
@NoArgsConstructor
public class TaskTimeConsumptionDTO {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 执行时间
     */
    private List<String> executionTime;

    /**
     * 执行耗时
     */
    private List<Long> timeConsumed;

    /**
     * 单位 执行时间 ms s m h
     */
    private String unit;
}
