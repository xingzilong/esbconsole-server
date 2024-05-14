package org.talend.esbconsole.server.web.api.controller.bundle.request;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BundlePageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/10
 */
public class BundlePageQueryRequestTest {

    private void init(BundlePageQueryRequest request) {
        request.setBundleName("test");
        request.setStartLevel(1);
        request.setState("0");
        request.setSymbolicName("ttttt");
        request.setUpdateLocation("D");
    }

    @Test
    public void test() {
        BundlePageQueryRequest request = new BundlePageQueryRequest();
        BundlePageQueryRequest bundlePageQueryRequest = new BundlePageQueryRequest();

        assertTrue(request.equals(bundlePageQueryRequest));
        assertFalse(request.equals(null));

        init(bundlePageQueryRequest);
        bundlePageQueryRequest.toString();
        bundlePageQueryRequest.hashCode();
        assertFalse(request.equals(bundlePageQueryRequest));

        bundlePageQueryRequest.getBundleName();
        bundlePageQueryRequest.getStartLevel();
        bundlePageQueryRequest.getState();
        bundlePageQueryRequest.getSymbolicName();
        bundlePageQueryRequest.getUpdateLocation();

        init(request);
        assertTrue(request.equals(bundlePageQueryRequest));


        request.setBundleName("tt");
        assertFalse(request.equals(bundlePageQueryRequest));

        init(request);
        request.setStartLevel(2);
        assertFalse(request.equals(bundlePageQueryRequest));

        init(request);
        request.setState("1");
        assertFalse(request.equals(bundlePageQueryRequest));

        init(request);
        request.setSymbolicName("tt");
        assertFalse(request.equals(bundlePageQueryRequest));

        init(request);
        request.setUpdateLocation("C");
        assertFalse(request.equals(bundlePageQueryRequest));
    }

}
