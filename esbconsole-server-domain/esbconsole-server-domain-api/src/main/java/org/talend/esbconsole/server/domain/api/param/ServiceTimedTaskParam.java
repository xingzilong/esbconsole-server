package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务分析-定时路由表格分页获取参数
 *
 * @author xingzilong
 * @create: 2021/08/24
 **/
@Data
@NoArgsConstructor
public class ServiceTimedTaskParam extends BasePageQueryRequest {
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 调用时间
     */
    private TimeInterval timeInterval;

    /**
     * service的id
     */
    private String id;
}
