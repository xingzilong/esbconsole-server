package org.talend.esbconsole.server.web.api.controller.service.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceStatusActionRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceStatusActionRequestTest {

    public void init(ServiceStatusActionRequest request) {
        request.setBundleId(1L);
        request.setBundleName(Arrays.asList("test"));
        request.setFeatureName("test");
        request.setId("1");
        request.setServiceKey("key");
        request.setServiceType("pom");
        request.setFileName("test");
        request.setFileType(".xml");

    }

    @Test
    public void test() {
        ServiceStatusActionRequest request = new ServiceStatusActionRequest();
        ServiceStatusActionRequest sr = request;
        request.hashCode();
        assertTrue(request.equals(sr));
        assertFalse(request.equals(null));
        sr = new ServiceStatusActionRequest();
        assertTrue(request.equals(sr));
        init(request);
        assertFalse(request.equals(sr));
        request.hashCode();
        request.toString();
        init(sr);
        assertTrue(request.equals(sr));
    }
}
