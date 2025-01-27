package org.talend.esbconsole.server.web.api.controller.feature.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 特性分页查询请求参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class FeaturePageQueryRequest extends BasePageQueryRequest {

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
