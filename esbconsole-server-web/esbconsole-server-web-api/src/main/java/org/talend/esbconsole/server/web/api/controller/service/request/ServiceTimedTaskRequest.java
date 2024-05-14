package org.talend.esbconsole.server.web.api.controller.service.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 定时路由分析表格get参数
 *
 * @author xingzilong
 * @date 2021/08/24
 **/
@Data
@NoArgsConstructor
public class ServiceTimedTaskRequest extends BasePageQueryRequest {
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 调用时间
     */
    @Valid
    private TimeInterval timeInterval;

    /**
     * service的id
     */
    @NotBlank
    @Size(max = 36)
    private String id;
}
