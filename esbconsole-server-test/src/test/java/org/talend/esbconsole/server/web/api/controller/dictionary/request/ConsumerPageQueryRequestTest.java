package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ConsumerPageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerPageQueryRequestTest {

    private void init(ConsumerPageQueryRequest request) {
        request.setCreateTime(new TimeInterval());
        request.setCreateTimeSort("t");
        request.setIp("127.0.0.1");
        request.setStatus("0");
        request.setSystemName("test");
    }

    @Test
    public void test() {
        ConsumerPageQueryRequest request = new ConsumerPageQueryRequest();
        ConsumerPageQueryRequest consumerPageQueryRequest = request;
        assertTrue(request.equals(consumerPageQueryRequest));

        consumerPageQueryRequest = new ConsumerPageQueryRequest();
        assertTrue(request.equals(consumerPageQueryRequest));
        assertFalse(request.equals(null));

        init(consumerPageQueryRequest);
        consumerPageQueryRequest.toString();
        consumerPageQueryRequest.hashCode();
        assertFalse(request.equals(consumerPageQueryRequest));

        init(request);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        request.setCreateTime(timeInterval);
        assertFalse(request.equals(consumerPageQueryRequest));

        init(request);
        request.setCreateTimeSort("tt");
        assertFalse(request.equals(consumerPageQueryRequest));

        init(request);
        request.setIp("");
        assertFalse(request.equals(consumerPageQueryRequest));

        init(request);
        request.setStatus("1");
        assertFalse(request.equals(consumerPageQueryRequest));

        init(request);
        request.setSystemName("tt");
        assertFalse(request.equals(consumerPageQueryRequest));


    }
}
