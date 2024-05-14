package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BundlePageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
public class BundlePageQueryParamTest {

    private void init(BundlePageQueryParam param) {
        param.setBundleName("test");
        param.setStartLevel(1);
        param.setState("0");
        param.setSymbolicName("ttttt");
        param.setUpdateLocation("D");
    }

    @Test
    public void test() {
        BundlePageQueryParam param = new BundlePageQueryParam();
        BundlePageQueryParam bundlePageQueryParam = new BundlePageQueryParam();

        assertTrue(param.equals(bundlePageQueryParam));
        assertFalse(param.equals(null));

        init(bundlePageQueryParam);
        bundlePageQueryParam.toString();
        bundlePageQueryParam.hashCode();
        assertFalse(param.equals(bundlePageQueryParam));

        bundlePageQueryParam.getBundleName();
        bundlePageQueryParam.getStartLevel();
        bundlePageQueryParam.getState();
        bundlePageQueryParam.getSymbolicName();
        bundlePageQueryParam.getUpdateLocation();

        init(param);
        assertTrue(param.equals(bundlePageQueryParam));


        param.setBundleName("tt");
        assertFalse(param.equals(bundlePageQueryParam));

        init(param);
        param.setStartLevel(2);
        assertFalse(param.equals(bundlePageQueryParam));

        init(param);
        param.setState("1");
        assertFalse(param.equals(bundlePageQueryParam));

        init(param);
        param.setSymbolicName("tt");
        assertFalse(param.equals(bundlePageQueryParam));

        init(param);
        param.setUpdateLocation("C");
        assertFalse(param.equals(bundlePageQueryParam));
    }

}
