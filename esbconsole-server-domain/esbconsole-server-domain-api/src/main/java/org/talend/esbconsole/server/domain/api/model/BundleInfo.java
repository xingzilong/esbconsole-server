package org.talend.esbconsole.server.domain.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Bundle详细信息
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BundleInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * bundle ID Identifier
     */
    private Long identifier;

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
     * bundle 版本 Version
     */
    private String version;

    /**
     * bundle 更新位置
     */
    private String updateLocation;

    /**
     * bundle 最后修改时间 LastModified
     */
    private Long lastModified;

}
