package org.talend.esbconsole.server.web.api.controller.ac.flow.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 访问控制-流量控制规则其他操作请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class AC4FlowStatusActionRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据库主键
     */
    @NotNull
    @Size(max = 36)
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
    @Pattern(regexp = "^(single|global|consumer)$", message = "类型选择不正确")
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
    @Pattern(regexp = "^[01]$", message = "状态码不正确")
    private String status;

}
