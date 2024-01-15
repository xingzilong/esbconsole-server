package org.talend.esbconsole.server.web.api.controller.servicelog.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * 服务嗲用日志的分页请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ServiceLogPageQueryRequest extends BasePageQueryRequest {

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
    @Pattern(regexp = "^(success|fail)$", message = "状态选择不正确")
    private String httpStatus;

    /**
     * 调用开始（请求）时间的区间条件
     */
    @Valid
    private TimeInterval requestTime;

    /**
     * 按照时间进行升序（asc）或降序（desc）
     */
    private String timeSort;
}