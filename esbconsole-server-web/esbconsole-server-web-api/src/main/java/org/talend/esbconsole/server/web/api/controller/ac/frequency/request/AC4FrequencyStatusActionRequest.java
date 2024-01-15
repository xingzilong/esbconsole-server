package org.talend.esbconsole.server.web.api.controller.ac.frequency.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 访问控制-频次控制规则其他操作请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class AC4FrequencyStatusActionRequest implements Serializable {
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
     * 启用类型 两种情况：
     * 一、值为 global，
     * 启用全局控制，即频次的控制是针对服务而言。
     * 二、值为 consumer，
     * 启用消费方控制，即频次的控制在针对服务的基础上，细分消费者，消费者的差异根据消费方IP区分。
     * <p>
     * 注意：不存在同时启用以上两种类型以上的情况，有且仅有以上情况中的一种
     */
    @Pattern(regexp = "^(global|consumer)$", message = "类型选择不正确")
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
    @Pattern(regexp = "^[01]$", message = "状态码选择不正确")
    private String status;
}
