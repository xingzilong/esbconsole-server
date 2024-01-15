package org.talend.esbconsole.server.web.api.controller.ac.frequency.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4FrequencyPageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FrequencyPageQueryRequestTest {

    private void init(AC4FrequencyPageQueryRequest request) {
        request.setServiceName("test");
        request.setCreateTime(new TimeInterval());
        request.setCreateTimeSort("t");
        request.setType("single");
        request.setStatus("0");
    }

    @Test
    public void test() {
        AC4FrequencyPageQueryRequest request = new AC4FrequencyPageQueryRequest();
        AC4FrequencyPageQueryRequest ac4FrequencyPageQueryRequest = new AC4FrequencyPageQueryRequest();

        assertTrue(request.equals(ac4FrequencyPageQueryRequest));
        assertFalse(request.equals(null));

        init(ac4FrequencyPageQueryRequest);
        assertFalse(request.equals(ac4FrequencyPageQueryRequest));

        ac4FrequencyPageQueryRequest.toString();
        ac4FrequencyPageQueryRequest.hashCode();

        ac4FrequencyPageQueryRequest.getCreateTime();
        ac4FrequencyPageQueryRequest.getCreateTimeSort();
        ac4FrequencyPageQueryRequest.getServiceName();
        ac4FrequencyPageQueryRequest.getStatus();
        ac4FrequencyPageQueryRequest.getType();

        init(request);
        assertTrue(request.equals(ac4FrequencyPageQueryRequest));

        init(request);
        request.setServiceName("tt");
        assertFalse(request.equals(ac4FrequencyPageQueryRequest));

        init(request);
        request.setCreateTime(null);
        assertFalse(request.equals(ac4FrequencyPageQueryRequest));

        init(request);
        request.setCreateTimeSort("tt");
        assertFalse(request.equals(ac4FrequencyPageQueryRequest));

        init(request);
        request.setType("global");
        assertFalse(request.equals(ac4FrequencyPageQueryRequest));

        init(request);
        request.setStatus("1");
        assertFalse(request.equals(ac4FrequencyPageQueryRequest));

        request.canEqual(ac4FrequencyPageQueryRequest);
    }

}
