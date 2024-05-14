package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceLogDTO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceLogDTOTest {

    public void init(ServiceLogDTO serviceLogDTO) {
        serviceLogDTO.setId(10L);
        serviceLogDTO.setServiceName("test");
        serviceLogDTO.setBusinessSystem("test-pid");
        serviceLogDTO.setServiceKey("test-fatherid");
        serviceLogDTO.setType("test-rootid");
        serviceLogDTO.setConsumerIp("test-rootid");
        serviceLogDTO.setHttpStatus(200);
        serviceLogDTO.setFailureCause("test");
        serviceLogDTO.setStartTime(LocalDateTime.MIN);
        serviceLogDTO.setResponseTime(10L);
        serviceLogDTO.setMiMessageId("test");
        serviceLogDTO.setMiFlowId("test");
    }

    @Test
    public void test() {

        ServiceLogDTO serviceLogDTO = new ServiceLogDTO();
        ServiceLogDTO serviceLogDTO1 = new ServiceLogDTO();

        assertTrue(serviceLogDTO.equals(serviceLogDTO1));

        serviceLogDTO.hashCode();
        serviceLogDTO.toString();
        init(serviceLogDTO);
        serviceLogDTO.hashCode();

        ServiceLogDTO serviceLogDTO2 = serviceLogDTO;
        assertTrue(serviceLogDTO2.equals(serviceLogDTO));

        assertFalse(serviceLogDTO1.equals(serviceLogDTO));
        assertFalse(serviceLogDTO1.equals(null));

        init(serviceLogDTO1);
        assertTrue(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setId(100L);
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        serviceLogDTO1.setServiceName("tests");
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setBusinessSystem("test-pids");
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setServiceKey("test-fatherids");
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setType("test-rootids");
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setConsumerIp("test-rootids");
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setHttpStatus(500);
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setFailureCause("tests");
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setStartTime(LocalDateTime.now().plusDays(1));
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setResponseTime(100L);
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setMiMessageId("tests");
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));

        init(serviceLogDTO1);
        serviceLogDTO1.setMiFlowId("tests");
        assertFalse(serviceLogDTO1.equals(serviceLogDTO));
    }
}
