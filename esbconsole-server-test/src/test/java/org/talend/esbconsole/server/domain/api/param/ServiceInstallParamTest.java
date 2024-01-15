package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceInstallParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceInstallParamTest {

    public void init(ServiceInstallParam param) {
        param.setName("test");
        param.setBusinessSystemId("esb");
        param.setDescription("skt");
        param.setProxyAddress("test");
        param.setResponsiblePerson("esb");
        param.setServiceType("soap");
    }

    @Test
    public void test() {

        ServiceInstallParam param = new ServiceInstallParam();
        ServiceInstallParam sr = param;
        param.hashCode();
        assertTrue(param.equals(sr));
        assertFalse(param.equals(null));
        sr = new ServiceInstallParam();
        assertTrue(param.equals(sr));
        init(param);
        assertFalse(param.equals(sr));
        param.hashCode();
        param.toString();
        init(sr);
        assertTrue(param.equals(sr));
    }
}
