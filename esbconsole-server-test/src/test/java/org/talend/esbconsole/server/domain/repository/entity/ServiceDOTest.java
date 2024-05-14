package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceDO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/18
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceDOTest {

    public void init(ServiceDO serviceDO) {
        serviceDO.setId("test-id");
        serviceDO.setName("test-name");
        serviceDO.setBusinessSystemId("test-username");
        serviceDO.setServiceType("test-password");
        serviceDO.setJob("test-email");
        serviceDO.setFileName("test-number");
        serviceDO.setFileType("test");
        serviceDO.setBundleId(1L);
        serviceDO.setFeatureName("test");
        serviceDO.setBundleName("test");
        serviceDO.setServiceKey("test");
        serviceDO.setEnabledSAM("test");
        serviceDO.setDescription("test");
        serviceDO.setStatus("test");
        serviceDO.setResponsiblePerson("test");
        serviceDO.setCreateTime(LocalDateTime.MIN);
        serviceDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        ServiceDO serviceDO = new ServiceDO();
        ServiceDO serviceDO1 = new ServiceDO();

        assertTrue(serviceDO.equals(serviceDO1));

        serviceDO.hashCode();
        serviceDO.toString();
        init(serviceDO);
        serviceDO.hashCode();

        ServiceDO serviceDO2 = serviceDO;
        assertTrue(serviceDO2.equals(serviceDO));

        assertFalse(serviceDO1.equals(serviceDO));
        assertFalse(serviceDO1.equals(null));

        init(serviceDO1);
        assertTrue(serviceDO1.equals(serviceDO));

        serviceDO1.setId("test-ids");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setName("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setBusinessSystemId("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setServiceType("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setJob("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setFileName("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setFileType("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setBundleId(11L);
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setFeatureName("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setBundleName("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setServiceKey("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setEnabledSAM("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setDescription("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setStatus("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setResponsiblePerson("test-systemnames");
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(serviceDO1.equals(serviceDO));

        init(serviceDO1);
        serviceDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(serviceDO1.equals(serviceDO));
    }
}
