package org.talend.esbconsole.server.web.api.controller.bundle.converter;

import org.talend.esbconsole.server.domain.api.param.BundlePageQueryParam;
import org.talend.esbconsole.server.web.api.controller.bundle.request.BundlePageQueryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link BundleWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BundleWebConverterImplTest {

    @InjectMocks
    private BundleWebConverterImpl bundleWebConverterImpl;

    @Test
    public void req2paramTest() {
        BundlePageQueryRequest request = new BundlePageQueryRequest();
        BundlePageQueryParam bundlePageQueryParam = bundleWebConverterImpl.req2param(request);
        assertNotNull(bundlePageQueryParam);
        request = null;
        BundlePageQueryParam bundlePageQueryParam1 = bundleWebConverterImpl.req2param(request);
        assertNull(bundlePageQueryParam1);
    }
}
