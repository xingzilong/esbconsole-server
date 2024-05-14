package org.talend.esbconsole.server.web.api.controller.service.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * api调用top数据集请求参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class APICallAnalysisRequest extends BasePageQueryRequest {
    /**
     * 服务id
     */
    @NotNull()
    @Size(max = 36)
    private String id;

    /**
     * 消费方系统名
     */
    @NotNull
    private String consumerSystem;

    /**
     * 时间区间
     */
    @Valid
    private TimeInterval timeInterval;
}
