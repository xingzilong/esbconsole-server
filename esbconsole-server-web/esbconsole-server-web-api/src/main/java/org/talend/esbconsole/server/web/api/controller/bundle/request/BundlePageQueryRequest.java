package org.talend.esbconsole.server.web.api.controller.bundle.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 构件分页查询请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class BundlePageQueryRequest extends BasePageQueryRequest {

    /**
     * bundle 名称 SymbolicName
     */
    private String symbolicName;

    /**
     * bundle 名称 BundleName
     */
    private String bundleName;

    /**
     * bundle 状态 State
     */
    private String state;

    /**
     * bundle 启动优先级 StartLevel
     */
    private Integer startLevel;

    /**
     * bundle 更新位置
     */
    private String updateLocation;

}
