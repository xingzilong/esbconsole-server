package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * servicelog 服务调用日志
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ServiceLogModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自定义名称
     */
    private String serviceName;

    /**
     * 所属业务系统
     */
    private String businessSystem;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 服务接口的调用时间戳
     */
    private LocalDateTime eiTimestamp;

    /**
     * 事件类型
     */
    private String eiEventType;

    /**
     * 自定义ID
     */
    private String origCustomId;

    /**
     * 处理此次调用的进程号
     */
    private String origProcessId;

    /**
     * 处理此次调用的主机名
     */
    private String origHostname;

    /**
     * 处理此次调用的主IP
     */
    private String origIp;

    private String origPrincipal;

    /**
     * 调用的服务接口的PortType
     */
    private String miPortType;

    /**
     * 调用的服务接口的操作名称
     */
    private String miOperationName;

    /**
     * 消息ID，目前webserviceSOAP服务接口的请求记录存在消息ID
     */
    private String miMessageId;

    /**
     * flowid,属于同一个服务接口的同一个客户端的同一次的请求和响应的flowid是相同的
     */
    private String miFlowId;

    /**
     * 传输类型
     */
    private String miTransportType;

    /**
     * 服务接口的唯一标识，目前使用的时serviceaddress作为其值
     */
    private String serviceKey;

    /**
     * http请求方法，http请求行中的内容信息之一
     */
    private String httpMethod;

    /**
     * 消费方IP，即请求该服务接口的客户端IP地址，目前只能是IPV4的地址（IPV6的地址也能记录，但是可能存在一些未知问题）
     */
    private String consumerIp;

    /**
     * http状态码，访问控制功能新增
     */
    private Integer httpStatus;

    /**
     * 响应时间，一个服务接口从请求至相应之间的耗时，以毫秒为单位
     */
    private Long responseTime;

    /**
     * 失败原因，若此次请求为非正常（失败）响应，则记录错误相应信息（失败响应的原因）
     */
    private String failureCause;

    /**
     * 报文的类型，用于标志此条报文日志是request（REQ）还是response（RESP）
     */
    private String messageType;

}