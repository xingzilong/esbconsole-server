package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 特性 分页查询参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class FeaturePageQueryParam extends BasePageQueryRequest {

    /**
     * 已安装
     */
    private Boolean installed;

    /**
     * feature名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;
}
