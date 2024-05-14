package org.talend.esbconsole.server.domain.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 访问控制-流量控制其他操作参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class AC4IPStatusActionParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据库主键
     */
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
     * 启用黑名单控制时，值为 black
     * 启用白名单控制时，值为 white
     * 注意：不存在同时启动黑名单和白名单的情况
     */
    private String type;

    /**
     * IP黑名单
     */
    private String blackList;

    /**
     * IP白名单
     */
    private String whiteList;

    /**
     * 状态：0-禁用，1-正常
     */
    private String status;

    public AC4IPStatusActionParam(String id, String serviceKey) {
        this.id = id;
        this.serviceKey = serviceKey;
    }

}
