package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BusinessRemoveRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessRemoveRequestTest {

    public void init(BusinessRemoveRequest businessRemoveRequest) {
        businessRemoveRequest.setId("1024");
    }

    @Test
    public void test() {

        BusinessRemoveRequest businessRemoveRequest = new BusinessRemoveRequest();
        BusinessRemoveRequest businessRemoveRequest1 = new BusinessRemoveRequest();

        assertTrue(businessRemoveRequest.equals(businessRemoveRequest1));

        businessRemoveRequest.hashCode();
        businessRemoveRequest.toString();
        init(businessRemoveRequest);
        businessRemoveRequest.hashCode();

        BusinessRemoveRequest businessRemoveRequest2 = businessRemoveRequest;
        assertTrue(businessRemoveRequest2.equals(businessRemoveRequest));

        assertFalse(businessRemoveRequest1.equals(businessRemoveRequest));
        assertFalse(businessRemoveRequest1.equals(null));

        init(businessRemoveRequest1);
        assertTrue(businessRemoveRequest1.equals(businessRemoveRequest));

        businessRemoveRequest1.setId("2021-10-10");
        assertFalse(businessRemoveRequest1.equals(businessRemoveRequest));


    }
}
