package org.talend.esbconsole.server.web.api.controller.ac.flow.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4FlowPageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FlowStatusActionRequestTest {

    private void init(AC4FlowStatusActionRequest request) {
        request.setId("45451");
        request.setIntervalThreshold(1L);
        request.setServiceKey("t");
        request.setServiceName("test");
        request.setSingleThreshold(1L);
        request.setStatus("0");
        request.setTimeInterval(300L);
        request.setType("single");
    }

    @Test
    public void test() {
        AC4FlowStatusActionRequest request = new AC4FlowStatusActionRequest();
        AC4FlowStatusActionRequest ac4FlowStatusActionRequest = new AC4FlowStatusActionRequest();

        assertTrue(request.equals(ac4FlowStatusActionRequest));
        assertFalse(request.equals(null));
        request.toString();
        request.hashCode();

        init(ac4FlowStatusActionRequest);

        request.getId();
        request.getIntervalThreshold();
        request.getServiceKey();
        request.getServiceName();
        request.getSingleThreshold();
        request.getStatus();
        request.getTimeInterval();
        request.getType();

        assertFalse(request.equals(ac4FlowStatusActionRequest));

        init(request);
        request.setId("12");
        assertFalse(request.equals(ac4FlowStatusActionRequest));

        init(request);
        request.setIntervalThreshold(5L);
        assertFalse(request.equals(ac4FlowStatusActionRequest));

        init(request);
        request.setServiceKey("tt");
        assertFalse(request.equals(ac4FlowStatusActionRequest));

        init(request);
        request.setServiceName("tt");
        assertFalse(request.equals(ac4FlowStatusActionRequest));

        init(request);
        request.setSingleThreshold(5L);
        assertFalse(request.equals(ac4FlowStatusActionRequest));

        init(request);
        request.setStatus("1");
        assertFalse(request.equals(ac4FlowStatusActionRequest));

        init(request);
        request.setTimeInterval(600L);
        assertFalse(request.equals(ac4FlowStatusActionRequest));

        init(request);
        request.setType("global");
        assertFalse(request.equals(ac4FlowStatusActionRequest));


        request.canEqual(ac4FlowStatusActionRequest);
    }
}
