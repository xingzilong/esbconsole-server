package org.talend.esbconsole.server.web.api.controller.ac.frequency.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4FrequencyStatusActionRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FrequencyStatusActionRequestTest {

    private void init(AC4FrequencyStatusActionRequest request) {
        request.setId("41231");
        request.setServiceName("test");
        request.setServiceKey("t");
        request.setType("global");
        request.setTimeInterval(5L);
        request.setThreshold(10L);
        request.setStatus("0");
    }

    @Test
    public void test() {
        AC4FrequencyStatusActionRequest request = new AC4FrequencyStatusActionRequest();
        AC4FrequencyStatusActionRequest ac4FrequencyStatusActionRequest = new AC4FrequencyStatusActionRequest();
        assertTrue(request.equals(ac4FrequencyStatusActionRequest));
        assertFalse(request.equals(null));

        init(ac4FrequencyStatusActionRequest);
        ac4FrequencyStatusActionRequest.toString();
        ac4FrequencyStatusActionRequest.hashCode();
        assertFalse(request.equals(ac4FrequencyStatusActionRequest));

        ac4FrequencyStatusActionRequest.getId();
        ac4FrequencyStatusActionRequest.getServiceKey();
        ac4FrequencyStatusActionRequest.getServiceName();
        ac4FrequencyStatusActionRequest.getStatus();
        ac4FrequencyStatusActionRequest.getThreshold();
        ac4FrequencyStatusActionRequest.getTimeInterval();
        ac4FrequencyStatusActionRequest.getType();

        init(request);
        assertTrue(request.equals(ac4FrequencyStatusActionRequest));

        init(request);
        request.setId("123");
        assertFalse(request.equals(ac4FrequencyStatusActionRequest));

        init(request);
        request.setServiceName("tt");
        assertFalse(request.equals(ac4FrequencyStatusActionRequest));

        init(request);
        request.setServiceKey("tt");
        assertFalse(request.equals(ac4FrequencyStatusActionRequest));

        init(request);
        request.setType("consumer");
        assertFalse(request.equals(ac4FrequencyStatusActionRequest));

        init(request);
        request.setTimeInterval(10L);
        assertFalse(request.equals(ac4FrequencyStatusActionRequest));

        init(request);
        request.setThreshold(20L);
        assertFalse(request.equals(ac4FrequencyStatusActionRequest));

        init(request);
        request.setStatus("1");
        assertFalse(request.equals(ac4FrequencyStatusActionRequest));
    }
}
