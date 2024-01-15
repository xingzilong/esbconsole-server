package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BusinessModifyRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessModifyRequestTest {


    private void init(BusinessModifyRequest request) {
        request.setId("1531");
        request.setRemark("");
        request.setResponsiblePerson("admin");
        request.setSystemName("test");
    }

    @Test
    public void test() {
        BusinessModifyRequest request = new BusinessModifyRequest();
        BusinessModifyRequest businessCreateRequest = request;
        assertTrue(request.equals(businessCreateRequest));

        businessCreateRequest = new BusinessModifyRequest();
        assertTrue(request.equals(businessCreateRequest));
        assertFalse(request.equals(null));

        init(businessCreateRequest);
        assertFalse(request.equals(businessCreateRequest));
        businessCreateRequest.toString();
        businessCreateRequest.hashCode();

        init(request);
        request.setId("123");
        assertFalse(request.equals(businessCreateRequest));

        init(request);
        request.setRemark("tt");
        assertFalse(request.equals(businessCreateRequest));

        init(request);
        request.setResponsiblePerson("tt");
        assertFalse(request.equals(businessCreateRequest));

        init(request);
        request.setSystemName("tt");
        assertFalse(request.equals(businessCreateRequest));
    }


}
