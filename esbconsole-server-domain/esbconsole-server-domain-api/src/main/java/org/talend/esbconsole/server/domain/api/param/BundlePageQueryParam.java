package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 构件 分页查询参数
 *
 * @author xingzilong
 * @date 2021/08/22
 */
@Data
@NoArgsConstructor
public class BundlePageQueryParam extends BasePageQueryRequest {

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
