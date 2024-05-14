package org.talend.esbconsole.server.domain.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 访问控制-频次控制其他操作参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class AC4FrequencyStatusActionParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据库主键
     */
    private String id;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务（webservice接口）的唯一标识，目前的取值为serviceaddress
     */
    private String serviceKey;

    /**
     * 启用类型 两种情况：
     * 一、值为 global，
     * 启用全局控制，即频次的控制是针对服务而言。
     * 二、值为 consumer，
     * 启用消费方控制，即频次的控制在针对服务的基础上，细分消费者，消费者的差异根据消费方IP区分。
     * <p>
     * 注意：不存在同时启用以上两种类型以上的情况，有且仅有以上情况中的一种
     */
    private String type;

    /**
     * 定义频率规则的时间区间，其秒为单位
     */
    private long timeInterval;

    /**
     * 定义频率规则的阈值，即在规定的时间区间内允许访问的最大次数
     */
    private long threshold;

    /**
     * 状态：0-禁用，1-正常
     */
    private String status;

    public AC4FrequencyStatusActionParam(String id, String serviceKey) {
        this.id = id;
        this.serviceKey = serviceKey;
    }

}
