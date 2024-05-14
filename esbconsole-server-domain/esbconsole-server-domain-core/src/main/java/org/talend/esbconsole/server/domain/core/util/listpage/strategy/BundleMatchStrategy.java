package org.talend.esbconsole.server.domain.core.util.listpage.strategy;

import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.param.BundlePageQueryParam;

/**
 * Bundle的过滤匹配策略
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class BundleMatchStrategy implements MatchStrategy<BundleInfo> {
    /**
     * 过滤条件
     */
    private BundlePageQueryParam query;

    public BundleMatchStrategy() {
    }

    public BundleMatchStrategy(BundlePageQueryParam query) {
        this.query = query;
    }

    public BundlePageQueryParam getQuery() {
        return query;
    }

    public void setQuery(BundlePageQueryParam query) {
        this.query = query;
    }

    @Override
    public boolean isMatch(BundleInfo bundleInfo) {
        return (query.getSymbolicName() == null || "".equals(query.getSymbolicName()) || bundleInfo.getSymbolicName().contains(query.getSymbolicName()))
                && (query.getBundleName() == null || "".equals(query.getBundleName()) || (bundleInfo.getBundleName() != null && bundleInfo.getBundleName().contains(query.getBundleName())))
                && (query.getUpdateLocation() == null || "".equals(query.getUpdateLocation()) || bundleInfo.getUpdateLocation().contains(query.getUpdateLocation()))
                && (query.getState() == null || "".equals(query.getState()) || bundleInfo.getState().equals(query.getState()));
    }
}
