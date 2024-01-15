package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访问控制-IP控制分页查询参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class AC4IPPageQueryParam extends BasePageQueryRequest {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 启用类型 两种情况：
     * 启用黑名单控制时，值为 black
     * 启用白名单控制时，值为 white
     * 注意：不存在同时启动黑名单和白名单的情况
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
     * 2023-07-31
     */
    private String createTimeSort;
}
