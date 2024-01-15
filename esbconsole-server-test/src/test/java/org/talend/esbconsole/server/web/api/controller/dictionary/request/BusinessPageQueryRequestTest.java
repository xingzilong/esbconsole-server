package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BusinessPageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessPageQueryRequestTest {


    private void init(BusinessPageQueryRequest request) {
        request.setCreateTime(new TimeInterval());
        request.setCreateTimeSort("t");
        request.setStatus("1");
        request.setSystemName("test");
    }

    @Test
    public void test() {
        BusinessPageQueryRequest request = new BusinessPageQueryRequest();
        BusinessPageQueryRequest businessPageQueryRequest = request;
        assertTrue(request.equals(businessPageQueryRequest));

        businessPageQueryRequest = new BusinessPageQueryRequest();
        assertTrue(request.equals(businessPageQueryRequest));
        assertFalse(request.equals(null));

        init(businessPageQueryRequest);
        assertFalse(businessPageQueryRequest.equals(request));
        businessPageQueryRequest.toString();
        businessPageQueryRequest.hashCode();

        init(request);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        request.setCreateTime(timeInterval);
        assertFalse(businessPageQueryRequest.equals(request));

        init(request);
        request.setCreateTimeSort("tt");
        assertFalse(businessPageQueryRequest.equals(request));

        init(request);
        request.setStatus("0");
        assertFalse(businessPageQueryRequest.equals(request));

        init(request);
        request.setSystemName("tt");
        assertFalse(businessPageQueryRequest.equals(request));

    }
}
