package org.talend.esbconsole.server.web.api.controller.service.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * api运行分析求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class APIRunAnalysisRequest {
    /**
     * 服务id
     */
    @NotNull
    @Size(max = 36)
    private String id;

    /**
     * 时间区间
     */
    @Valid
    private TimeInterval timeInterval;
}
