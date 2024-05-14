package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * api调用top数据集请求参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class APICallAnalysisParam extends BasePageQueryRequest {
    /**
     * 服务id
     */
    private String id;

    /**
     * 消费方系统名
     */
    private String consumerSystem;

    /**
     * 时间区间
     */
    private TimeInterval timeInterval;
}
