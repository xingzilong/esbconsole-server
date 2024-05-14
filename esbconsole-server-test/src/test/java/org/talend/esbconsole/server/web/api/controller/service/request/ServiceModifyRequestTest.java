package org.talend.esbconsole.server.web.api.controller.service.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceModifyRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceModifyRequestTest {

    public void init(ServiceModifyRequest request) {
        request.setBusinessSystemId("test");
        request.setDescription("test");
        request.setId("1");
        request.setName("mp");
        request.setResponsiblePerson("penson");
    }

    @Test
    public void test() {
        ServiceModifyRequest request = new ServiceModifyRequest();
        ServiceModifyRequest sr = request;
        request.hashCode();
        assertTrue(request.equals(sr));
        assertFalse(request.equals(null));
        sr = new ServiceModifyRequest();
        assertTrue(request.equals(sr));
        init(request);
        assertFalse(request.equals(sr));
        request.hashCode();
        request.toString();
        init(sr);
        assertTrue(request.equals(sr));
    }

}
