package org.talend.esbconsole.server.web.api.controller.ac.flow.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * 访问控制-流量控制规则分页请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class AC4FlowPageQueryRequest extends BasePageQueryRequest {

    /**
     * 服务名称
     */
    private String serviceName;

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
    @Pattern(regexp = "^(single|global|consumer)$", message = "输入了不存在的type")
    private String type;

    /**
     * 状态：0-禁用，1-正常
     */
    @Pattern(regexp = "^[01]$", message = "状态码不正确")
    private String status;

    /**
     * 创建时间的区间条件
     */
    @Valid
    private TimeInterval createTime;

    /**
     * 创建时间的排序关键字
     * xingzilong
     * 2023-07-31
     */
    private String createTimeSort;
}
