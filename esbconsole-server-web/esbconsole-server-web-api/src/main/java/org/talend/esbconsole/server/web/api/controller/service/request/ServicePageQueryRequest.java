package org.talend.esbconsole.server.web.api.controller.service.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * service分页查询请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ServicePageQueryRequest extends BasePageQueryRequest {

    /**
     * 自定义名称
     */
    private String name;

    /**
     * 所属业务系统的id
     */
    private String businessSystemId;

    /**
     * 服务的类型，目前有五种类型：
     * "restful_ws"             kar文件的形式、 wsAPI接口
     * "soap_ws"                jar文件形式、 wsAPI接口
     * "timed_route"            kar文件的形式、 定时任务
     * "conventional_route"     kar文件的形式、 常规路由
     * "proxy_route"            kar文件的形式 需要在部署时传入代理后的地址 代理路由
     */
    private String serviceType;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型 两种 kar 或 jar
     */
    @Pattern(regexp = "^(kar|jar)$", message = "文件类型不正确")
    private String fileType;

    /**
     * 0 表示未启用SAM监控， 1 表示启用了SAM监控
     */
    @Pattern(regexp = "^[01]$", message = "类型不正确")
    private String enabledSAM;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态：0-禁用，1-正常
     */
    @Pattern(regexp = "^[01]$", message = "状态码不正确")
    private String status;

    /**
     * 责任人
     */
    private String responsiblePerson;

    /**
     * 创建时间的区间条件
     */
    @Valid
    private TimeInterval createTime;

    /**
     * 创建时间的排序关键字
     *
     * @author xingzilong
     * @date 2023-07-31
     */
    private String createTimeSort;
}
