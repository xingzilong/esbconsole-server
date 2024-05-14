package org.talend.esbconsole.server.web.api.controller.servicelog.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 服务接口的调用日志
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class ServiceLogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID（请求日志的主键ID）
     */
    private Long id;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 所属业务系统
     */
    private String businessSystem;

    /**
     * 服务接口的唯一标识，目前使用的时serviceaddress作为其值
     */
    private String serviceKey;

    /**
     * webservice服务的风格类型，目前有两种： SAOP、RESTFUL
     */
    private String type;

    /**
     * 消费方IP，即请求该服务接口的客户端IP地址，目前只能是IPV4的地址（IPV6的地址也能记录，但是可能存在一些未知问题）
     */
    private String consumerIp;

    /**
     * http状态码，访问控制功能新增
     */
    private Integer httpStatus;

    /**
     * 失败原因，若此次请求为非正常（失败）响应，则记录错误相应信息（失败响应的原因）
     */
    private String failureCause;

    /**
     * 调用开始时间
     */
    private String startTime;

    /**
     * 响应耗时
     */
    private Long responseTime;

    /**
     * 消息ID，目前webserviceSOAP服务接口的请求记录存在消息ID
     */
    private String miMessageId;

    /**
     * flowid,属于同一个服务接口的同一个客户端的同一次的请求和响应的flowid是相同的
     */
    private String miFlowId;

}