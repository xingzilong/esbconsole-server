package org.talend.esbconsole.server.web.api.controller.service.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceTimedTaskRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceTimedTaskRequestTest {

    public void init(ServiceTimedTaskRequest request) {

        request.setPageNum(1);
        request.setPageSize(10);
        request.setTaskName("test");
        request.setId("456");
        request.setTimeInterval(new TimeInterval());
    }

    @Test
    public void test() {
        ServiceTimedTaskRequest request = new ServiceTimedTaskRequest();
        ServiceTimedTaskRequest sr = request;
        request.hashCode();
        assertTrue(request.equals(sr));
        assertFalse(request.equals(null));
        sr = new ServiceTimedTaskRequest();
        assertTrue(request.equals(sr));
        init(request);
        assertFalse(request.equals(sr));
        request.hashCode();
        request.toString();
        init(sr);
        assertTrue(request.equals(sr));
    }
}
