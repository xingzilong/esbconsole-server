package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ConsumerCreateRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerCreateRequestTest {

    private void init(ConsumerCreateRequest request) {
        request.setIp("127.0.0.1");
        request.setRemark("");
        request.setResponsiblePerson("admin");
        request.setSystemName("test");
    }

    @Test
    public void test() {
        ConsumerCreateRequest request = new ConsumerCreateRequest();
        ConsumerCreateRequest consumerCreateRequest = request;
        assertTrue(request.equals(consumerCreateRequest));

        consumerCreateRequest = new ConsumerCreateRequest();
        assertTrue(request.equals(consumerCreateRequest));
        assertFalse(request.equals(null));

        init(consumerCreateRequest);
        assertFalse(request.equals(consumerCreateRequest));
        consumerCreateRequest.toString();
        consumerCreateRequest.hashCode();

        init(request);
        request.setIp("");
        assertFalse(request.equals(consumerCreateRequest));

        init(request);
        request.setRemark("test");
        assertFalse(request.equals(consumerCreateRequest));

        init(request);
        request.setResponsiblePerson("test");
        assertFalse(request.equals(consumerCreateRequest));

        init(request);
        request.setSystemName("tt");
        assertFalse(request.equals(consumerCreateRequest));

    }


}
