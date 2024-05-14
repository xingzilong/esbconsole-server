package org.talend.esbconsole.server.tools.common.constant;

/**
 * 通用常量信息
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class Constants {

    /**
     * esbsam包名
     */
    public static final String ESB_SAM_PACKAGE = "org.talend.esb.sam.agent.feature.EventFeature";
    /**
     * 数据获取成功
     */
    public static final String DATA_ACQUISITION_SUCCESSFUL = "数据获取成功";
    /**
     * value
     */
    public static final String VALUE = "value";
    /**
     * 失败
     */
    public static final String FAILURE = "failure";
    /**
     * 成功
     */
    public static final String SUCCESS = "success";
    /**
     * websocket自定义握手请求头，用于存放认证信息
     */
    public static final String CUSTOM_WEBSOCKET_HEADER = "Sec-WebSocket-Protocol";
    /**
     * JWTFilterException重定向的路径
     */
    public static final String JWT_FILTER_EXCEPTION_FORWARD_PATH = "/api/exception/authentication";
    /**
     * JWTFilterException
     */
    public static final String JWT_FILTER_EXCEPTION = "JWTFilterException";
    /**
     * 缓存存储token的前缀
     */
    public static final String CACHE_TOKEN_PREFIX = "cacheid:";
    /**
     * 缓存存储token的键
     */
    public static final String TOKEN_CACHE = "token-cache";
    /**
     * 字符集编码 UTF-8
     */
    public static final String UTF8 = "UTF-8";
    /**
     * 字符集编码 GBK
     */
    public static final String GBK = "GBK";
    /**
     * feature
     */
    public static final String NAME = "name";
    /**
     * feature
     */
    public static final String FEATURE = "feature";
    /**
     * karaf name
     */
    public static final String KARAF_NAME = "trun";
    /**
     * restfulwebservice
     */
    public static final String RESTFUL_WS = "restful_ws";
    /**
     * soapwebservice
     */
    public static final String SOAP_WS = "soap_ws";
    /**
     * 定时路由
     */
    public static final String TIMED_ROUTE = "timed_route";
    /**
     * 常规路由
     */
    public static final String CONVENTIONAL_ROUTE = "conventional_route";
    /**
     * 代理路由
     */
    public static final String PROXY_ROUTE = "proxy_route";
    /**
     * 未知类型
     */
    public static final String UNKNOWN = "unknown";
    /**
     * 升序关键字
     */
    public static final String ASC = "asc";
    /**
     * 降序关键字
     */
    public static final String DESC = "desc";
    /**
     * 英文 ?
     */
    public static final String QUERY = "?";
    /**
     * 一个空格字符串
     */
    public static final String SPACE = " ";
    /**
     * SOAP webservice风格类型
     */
    public static final String SOAP = "soap";
    /**
     * RESTFUL webservice风格类型
     */
    public static final String RESTFUL = "restful";
    /**
     * IP黑白名单访问控制中使用黑名单控制的类型标志符
     */
    public static final String IP_TYPE_BLACK = "black";
    /**
     * IP黑白名单访问控制中使用白名单控制的类型标志符
     */
    public static final String IP_TYPE_WHITE = "white";
    /**
     * 频次访问控制中使用全局控制的类型标志符
     */
    public static final String FTEQUENCY_TYPE_GLOBAL = "global";
    /**
     * 频次访问控制中使用消费方控制的类型标志符
     */
    public static final String FTEQUENCY_TYPE_CONSUMER = "consumer";
    /**
     * 流量访问控制中使用单次流量控制的类型标志符
     */
    public static final String FLOW_TYPE_SINGLE = "single";
    /**
     * 流量访问控制中使用区间-全局控制的类型标志符
     */
    public static final String FLOW_TYPE_INTERVAL_GLOBAL = "global";
    /**
     * 流量访问控制中使用区间-消费方控制的类型标志符
     */
    public static final String FLOW_TYPE_INTERVAL_CONSUMER = "consumer";
    /**
     * 受IP控制访问控制规则而请求失败的原因，字符串描述
     */
    public static final String REQUEST_FAILURE_CAUSE_IP = "IP";
    /**
     * 受流量控制访问控制规则而请求失败的原因，字符串描述
     */
    public static final String REQUEST_FAILURE_CAUSE_FLOW = "FLOW";
    /**
     * 受频次控制访问控制规则而请求失败的原因，字符串描述
     */
    public static final String REQUEST_FAILURE_CAUSE_FREQUENCY = "FREQUENCY";
    /**
     * 报文的类型，用于标志此条报文日志是request（REQ）。
     */
    public static final String MESSAGE_TYPE_REQ = "REQ";
    /**
     * 报文的类型，用于标志此条报文日志是response（RESP）。
     */
    public static final String MESSAGE_TYPE_RESP = "RESP";
    /**
     * 随机生成uuid的key名
     */
    public static final String WEBSSH_USER_KEY = "user_uuid";
    /**
     * 发送指令：连接
     */
    public static final String WEBSSH_OPERATION_CONNECT = "connect";
    /**
     * 发送指令：命令
     */
    public static final String WEBSSH_OPERATION_COMMAND = "command";
    /**
     * http请求
     */
    public static final String HTTP = "http://";
    /**
     * https请求
     */
    public static final String HTTPS = "https://";
    /**
     * 通用成功标识
     */
    public static final String SUCCESS4INT = "0";
    /**
     * 通用失败标识
     */
    public static final String FAIL4INT = "1";
    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";
    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * request header中认证信息的key
     */
    public static final String AUTHORIZATION = "Authorization";

    private Constants() {
    }


}
