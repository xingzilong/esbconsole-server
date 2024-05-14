package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceStatusActionParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceStatusActionParamTest {

    public void init(ServiceStatusActionParam param) {
        param.setBundleId(1L);
        param.setBundleName(Arrays.asList("test"));
        param.setFeatureName("test");
        param.setId("1");
        param.setServiceKey("key");
        param.setServiceType("pom");
        param.setFileName("test");
        param.setFileType(".xml");

    }

    @Test
    public void test() {
        ServiceStatusActionParam param = new ServiceStatusActionParam();
        ServiceStatusActionParam sr = param;
        param.hashCode();
        assertTrue(param.equals(sr));
        assertFalse(param.equals(null));
        sr = new ServiceStatusActionParam();
        assertTrue(param.equals(sr));
        init(param);
        assertFalse(param.equals(sr));
        param.hashCode();
        param.toString();
        init(sr);
        assertTrue(param.equals(sr));
    }
}
