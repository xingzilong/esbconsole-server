package org.talend.esbconsole.server.domain.core.util.listpage.strategy;

import org.talend.esbconsole.server.domain.api.model.FeatureInfo;
import org.talend.esbconsole.server.domain.api.param.FeaturePageQueryParam;

/**
 * Feature的过滤匹配策略
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class FeatureMatchStrategy implements MatchStrategy<FeatureInfo> {
    /**
     * 过滤条件
     */
    private FeaturePageQueryParam query;

    public FeatureMatchStrategy() {
    }

    public FeatureMatchStrategy(FeaturePageQueryParam query) {
        this.query = query;
    }

    public FeaturePageQueryParam getQuery() {
        return query;
    }

    public void setQuery(FeaturePageQueryParam query) {
        this.query = query;
    }

    @Override
    public boolean isMatch(FeatureInfo featureInfo) {
        return (query.getName() == null || "".equals(query.getName()) || featureInfo.getName().contains(query.getName()))
                && (query.getInstalled() == null || "".equals(query.getInstalled()) || (query.getInstalled().booleanValue() == featureInfo.getInstalled().booleanValue()))
                && (query.getVersion() == null || "".equals(query.getVersion()) || featureInfo.getVersion().contains(query.getVersion()));
    }
}
