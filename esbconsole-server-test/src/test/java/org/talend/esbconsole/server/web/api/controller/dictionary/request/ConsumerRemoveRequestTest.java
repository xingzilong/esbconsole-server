package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ConsumerRemoveRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerRemoveRequestTest {

    public void init(ConsumerRemoveRequest consumerRemoveRequest) {
        consumerRemoveRequest.setId("1024");
    }

    @Test
    public void test() {

        ConsumerRemoveRequest consumerRemoveRequest = new ConsumerRemoveRequest();
        ConsumerRemoveRequest consumerRemoveRequest1 = new ConsumerRemoveRequest();

        assertTrue(consumerRemoveRequest.equals(consumerRemoveRequest1));

        consumerRemoveRequest.hashCode();
        consumerRemoveRequest.toString();
        init(consumerRemoveRequest);
        consumerRemoveRequest.hashCode();

        ConsumerRemoveRequest consumerRemoveRequest2 = consumerRemoveRequest;
        assertTrue(consumerRemoveRequest2.equals(consumerRemoveRequest));

        assertFalse(consumerRemoveRequest1.equals(consumerRemoveRequest));
        assertFalse(consumerRemoveRequest1.equals(null));

        init(consumerRemoveRequest1);
        assertTrue(consumerRemoveRequest1.equals(consumerRemoveRequest));

        consumerRemoveRequest1.setId("2023-10-10");
        assertFalse(consumerRemoveRequest1.equals(consumerRemoveRequest));


    }
}
