package org.talend.esbconsole.server.web.api.controller.servicelog.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceLogPageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceLogPageQueryRequestTest {

    public void init(ServiceLogPageQueryRequest request) {

        request.setType("soap");
        request.setBusinessSystemId("esb");
        request.setConsumerIp("127.0.0.1");
        request.setHttpStatus("success");
        request.setPageNum(1);
        request.setPageSize(10);
        request.setServiceName("test");
        request.setTimeSort("6789");
        request.setRequestTime(null);

    }

    @Test
    public void test() {
        ServiceLogPageQueryRequest request = new ServiceLogPageQueryRequest();
        ServiceLogPageQueryRequest qr = new ServiceLogPageQueryRequest();
        request.hashCode();
        assertTrue(request.equals(qr));
        init(request);
        assertFalse(request.equals(qr));
        assertFalse(request.equals(null));
        ServiceLogPageQueryRequest pqr = request;
        assertTrue(request.equals(pqr));
        request.toString();
        request.hashCode();
        init(qr);
        assertTrue(request.equals(qr));
        qr.setType("restful");
        assertFalse(request.equals(qr));
    }
}
