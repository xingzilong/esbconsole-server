package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BusinessCreateRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessCreateRequestTest {


    private void init(BusinessCreateRequest request) {
        request.setResponsiblePerson("admin");
        request.setRemark("");
        request.setSystemName("test");

    }

    @Test
    public void test() {
        BusinessCreateRequest request = new BusinessCreateRequest();
        BusinessCreateRequest businessCreateRequest = request;
        assertTrue(request.equals(businessCreateRequest));

        businessCreateRequest = new BusinessCreateRequest();
        assertTrue(request.equals(businessCreateRequest));
        assertFalse(request.equals(null));

        init(businessCreateRequest);
        businessCreateRequest.toString();
        businessCreateRequest.hashCode();

        init(request);
        assertTrue(request.equals(businessCreateRequest));

        request.setResponsiblePerson("test");
        assertFalse(request.equals(businessCreateRequest));

        init(request);
        request.setRemark("tt");
        assertFalse(request.equals(businessCreateRequest));

        init(request);
        request.setSystemName("tt");
        assertFalse(request.equals(businessCreateRequest));
    }

}
