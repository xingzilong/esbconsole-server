package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访问控制-频次控制分页查询参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class AC4FrequencyPageQueryParam extends BasePageQueryRequest {

    /**
     * 服务名称
     */
    private String serviceName;

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
     * 状态：0-禁用，1-正常
     */
    private String status;

    /**
     * 创建时间的区间条件
     */
    private TimeInterval createTime;

    /**
     * 创建时间的排序关键字
     * xingzilong
     * 2021-07-31
     */
    private String createTimeSort;
}
