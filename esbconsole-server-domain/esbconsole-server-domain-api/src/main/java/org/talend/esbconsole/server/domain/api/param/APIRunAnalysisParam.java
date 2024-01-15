package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * api调用top数据集请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class APIRunAnalysisParam {
    /**
     * 服务id
     */
    private String id;

    /**
     * 时间区间
     */
    private TimeInterval timeInterval;
}
