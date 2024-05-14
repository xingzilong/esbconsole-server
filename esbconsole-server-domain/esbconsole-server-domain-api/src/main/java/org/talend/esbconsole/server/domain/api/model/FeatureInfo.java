package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Feature详细信息
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class FeatureInfo {
    /**
     * 列入黑名单
     */
    private Boolean blacklisted;

    /**
     * feature包含的bundle
     */
    private List<String> bundles;

    /**
     * 配置文件
     */
    private Object configurationFiles;

    /**
     * 配置项
     */
    private Object configurations;

    /**
     * 依赖项
     */
    private Object dependencies;

    /**
     * 已安装
     */
    private Boolean installed;

    /**
     * feature名称
     */
    private String name;

    /**
     * 必需的
     */
    private Boolean required;

    /**
     * 版本
     */
    private String version;

    private List<BundleInfo> bundleInfoList;

    public FeatureInfo(Boolean blacklisted, List<String> bundles, Boolean installed, String name, Boolean required,
                       String version) {
        this.blacklisted = blacklisted;
        this.bundles = bundles;
        this.installed = installed;
        this.name = name;
        this.required = required;
        this.version = version;
        this.bundleInfoList = new ArrayList<>();
    }
}
