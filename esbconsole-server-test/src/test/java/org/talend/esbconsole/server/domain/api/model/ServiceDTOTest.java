package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceDTO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceDTOTest {

    public void init(ServiceDTO serviceDTO) {
        serviceDTO.setId("test-id");
        serviceDTO.setName("test-name");
        serviceDTO.setBusinessSystem("test-name");
        serviceDTO.setBusinessSystemId("test-name");
        serviceDTO.setServiceType("test-password");
        serviceDTO.setJob("job");
        serviceDTO.setFileName("test-number");
        serviceDTO.setFileType("test");
        serviceDTO.setBundleId(1L);
        serviceDTO.setFeatureName("test");
        serviceDTO.setBundleName("bundlename");
        serviceDTO.setServiceKey("test");
        serviceDTO.setEnabledSAM("test");
        serviceDTO.setDescription("test");
        serviceDTO.setResponsiblePerson("test");
        serviceDTO.setCreateTime(LocalDateTime.MIN);
        serviceDTO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        ServiceDTO serviceDTO = new ServiceDTO();
        ServiceDTO serviceDTO1 = new ServiceDTO();

        assertTrue(serviceDTO.equals(serviceDTO1));

        serviceDTO.hashCode();
        serviceDTO.toString();
        init(serviceDTO);
        serviceDTO.hashCode();

        ServiceDTO serviceDTO2 = serviceDTO;
        assertTrue(serviceDTO2.equals(serviceDTO));

        assertFalse(serviceDTO1.equals(serviceDTO));
        assertFalse(serviceDTO1.equals(null));

        init(serviceDTO1);
        assertTrue(serviceDTO1.equals(serviceDTO));

        serviceDTO1.setId("test-ids");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setName("test-systemnames");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setBusinessSystem("test-systemnames");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setBusinessSystemId("test-systemnamesASDAS");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setServiceType("test-systemnames");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setJob("job1");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setFileName("test-systemnames");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setFileType("test-systemnames");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setBundleId(11L);
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setFeatureName("test-systemnames");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setBundleName("bundlename1");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setServiceKey("test-systemnames");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setEnabledSAM("test-systemnames");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setDescription("test-systemnames");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setResponsiblePerson("test-systemnames");
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setCreateTime(LocalDateTime.now().plusDays(1));
        assertFalse(serviceDTO1.equals(serviceDTO));

        init(serviceDTO1);
        serviceDTO1.setUpdateTime(LocalDateTime.now().plusDays(1));
        assertFalse(serviceDTO1.equals(serviceDTO));
    }
}
