package org.talend.esbconsole.server.web.api.controller.feature.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FeaturePageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FeaturePageQueryRequestTest {

    public void init(FeaturePageQueryRequest request) {

        request.setPageNum(1);
        request.setPageSize(10);
        request.setInstalled(false);
        request.setName("test");
        request.setVersion("v1");
    }

    @Test
    public void test() {

        FeaturePageQueryRequest request = new FeaturePageQueryRequest();
        FeaturePageQueryRequest ar = request;
        request.hashCode();
        assertTrue(request.equals(ar));
        assertFalse(request.equals(null));
        ar = new FeaturePageQueryRequest();
        assertTrue(request.equals(ar));
        init(request);
        assertFalse(request.equals(ar));
        request.hashCode();
        request.toString();
        init(ar);
        assertTrue(request.equals(ar));

    }
}
