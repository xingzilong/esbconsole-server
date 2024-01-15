package org.talend.esbconsole.server.web.api.controller.service.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceInstallRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceInstallRequestTest {

    public void init(ServiceInstallRequest request) {
        request.setName("test");
        request.setBusinessSystemId("esb");
        request.setDescription("skt");
        request.setProxyAddress("test");
        request.setResponsiblePerson("esb");
        request.setServiceType("soap");
    }

    @Test
    public void test() {

        ServiceInstallRequest request = new ServiceInstallRequest();
        ServiceInstallRequest sr = request;
        request.hashCode();
        assertTrue(request.equals(sr));
        assertFalse(request.equals(null));
        sr = new ServiceInstallRequest();
        assertTrue(request.equals(sr));
        init(request);
        assertFalse(request.equals(sr));
        request.hashCode();
        request.toString();
        init(sr);
        assertTrue(request.equals(sr));
    }
}
