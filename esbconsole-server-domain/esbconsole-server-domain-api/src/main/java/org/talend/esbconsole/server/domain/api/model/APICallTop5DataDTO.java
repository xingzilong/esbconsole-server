package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * api调用top5数据集
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class APICallTop5DataDTO {

    /**
     * 消费方IP
     */
    private String consumerIP;

    /**
     * 消费方系统名
     */
    private String consumerSystem;

    /**
     * 调用总数
     */
    private Long callTotal;

    /**
     * 调用失败总数
     */
    private Long callFailureTotal;

    /**
     * 服务标识
     */
    private String serviceKey;


}
