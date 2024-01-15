package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ConsumerModifyRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerModifyRequestTest {

    private void init(ConsumerModifyRequest request) {
        request.setId("13213");
        request.setIp("127.0.0.1");
        request.setRemark("");
        request.setResponsiblePerson("admin");
        request.setSystemName("test");
    }

    @Test
    public void test() {
        ConsumerModifyRequest request = new ConsumerModifyRequest();
        ConsumerModifyRequest consumerModifyRequest = request;
        assertTrue(request.equals(consumerModifyRequest));

        consumerModifyRequest = new ConsumerModifyRequest();
        assertTrue(request.equals(consumerModifyRequest));
        assertFalse(request.equals(null));

        init(consumerModifyRequest);
        consumerModifyRequest.toString();
        consumerModifyRequest.hashCode();
        assertFalse(request.equals(consumerModifyRequest));

        init(request);
        request.setId("12");
        assertFalse(request.equals(consumerModifyRequest));

        init(request);
        request.setIp("");
        assertFalse(request.equals(consumerModifyRequest));

        init(request);
        request.setRemark("test");
        assertFalse(request.equals(consumerModifyRequest));

        init(request);
        request.setResponsiblePerson("test");
        assertFalse(request.equals(consumerModifyRequest));

        init(request);
        request.setSystemName("tt");
        assertFalse(request.equals(consumerModifyRequest));

    }
}
