package org.talend.esbconsole.server.domain.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据库表 access_control_flow 对应的实体类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class AccessControlFlowDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据库主键
     */
    private String id;

    /**
     * 服务（webservice接口）的唯一标识，目前的取值为serviceaddress
     */
    private String serviceKey;

    /**
     * 启用类型 三种情况：
     * 一、值为 single，
     * 启动单次流量控制，即对单次请求的流量大小进行控制。
     * 二、值为 global，
     * 启用区间-全局流量控制，且是全局，即流量的控制是针对服务而言。
     * 三、值为 consumer，
     * 启用区间-消费方流量控制，即流量的控制在针对服务的基础上，细分消费者，消费者的差异根据消费方IP区分。
     * <p>
     * 注意：不存在同时启用以上两种类型以上的情况，有且仅有以上情况中的一种
     */
    private String type;

    /**
     * 定义流量控制规则的时间区间，其秒为单位
     */
    private long timeInterval;

    /**
     * 定义流量控制规则的阈值，即在规定的时间区间内允许访问的最大流量阈值
     */
    private long intervalThreshold;

    /**
     * 定义流量控制规则的单次流量阈值
     */
    private long singleThreshold;

    /**
     * 状态：0-禁用，1-正常
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
