package org.talend.esbconsole.server.web.api.controller.feature.converter;

import org.talend.esbconsole.server.domain.api.param.FeaturePageQueryParam;
import org.talend.esbconsole.server.web.api.controller.feature.request.FeaturePageQueryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link FeatureWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FeatureWebConverterImplTest {

    @InjectMocks
    private FeatureWebConverterImpl featureWebConverterImpl;

    @Test
    public void req2paramTest() {
        FeaturePageQueryRequest request = new FeaturePageQueryRequest();
        FeaturePageQueryParam featurePageQueryParam = featureWebConverterImpl.req2param(request);
        assertNotNull(featurePageQueryParam);
        request = null;
        FeaturePageQueryParam featurePageQueryParam1 = featureWebConverterImpl.req2param(request);
        assertNull(featurePageQueryParam1);
    }
}
