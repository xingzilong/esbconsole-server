package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API调用分析表
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class APICallAnalysisTableDTO {

    /**
     * 消费方系统
     */
    private String consumerSystem;

    /**
     * 消费方系统
     */
    private String consumerIP;

    /**
     * 调用次数
     */
    private Long callTotal;

    /**
     * 失败次数
     */
    private Long callFailureTotal;

    /**
     * 频次限制失败
     */
    private Long callFailureTotal4Frequency;

    /**
     * 流量限制失败
     */
    private Long callFailureTotal4Flow;

    /**
     * IP限制失败
     */
    private Long callFailureTotal4IP;

    /**
     * 平均响应时间
     */
    private double avgResponseTime;

}
