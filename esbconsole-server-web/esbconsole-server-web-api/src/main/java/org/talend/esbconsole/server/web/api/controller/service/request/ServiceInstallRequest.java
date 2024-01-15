package org.talend.esbconsole.server.web.api.controller.service.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * service部署请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ServiceInstallRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自定义名称
     */
    @NotNull
    private String name;

    /**
     * 服务的类型，目前有五种类型：
     * "restful_ws"             kar文件的形式、 wsAPI接口
     * "soap_ws"                jar文件形式、 wsAPI接口
     * "timed_route"            kar文件的形式、 定时任务
     * "conventional_route"     kar文件的形式、 常规路由
     * "proxy_route"            kar文件的形式 需要在部署时传入代理后的地址 代理路由
     */
    @NotNull
    private String serviceType;

    /**
     * 代理地址，即将源服务代理发布之后的地址
     */
    private String proxyAddress;

    /**
     * 所属业务系统的id
     */
    @NotNull
    private String businessSystemId;

    /**
     * 描述
     */
    private String description;

    /**
     * 责任人
     */
    @NotNull
    private String responsiblePerson;


}
