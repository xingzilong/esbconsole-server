package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务调用日志的分页查询参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class ServiceLogPageQueryParam extends BasePageQueryRequest {
    /**
     * 所属系统的id
     */
    private String businessSystemId;
    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 消费方IP
     */
    private String consumerIp;

    /**
     * webservice服务的风格类型，目前有两种： soap、restful
     */
    private String type;

    /**
     * 成功 或 失败
     * success、 fail
     */
    private String httpStatus;

    /**
     * 调用开始（请求）时间的区间条件
     */
    private TimeInterval requestTime;

    /**
     * 按照时间进行升序（asc）或降序（desc）
     */
    private String timeSort;
}
