package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FlowMeterCatcherDTO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FlowMeterCatcherDTOTest {

    public void init(FlowMeterCatcherDTO flowMeterCatcherDTO) {
        flowMeterCatcherDTO.setMoment(LocalDateTime.MIN);
        flowMeterCatcherDTO.setUuid("test-uuid");
        flowMeterCatcherDTO.setPid("test-pid");
        flowMeterCatcherDTO.setFatherPid("test-fatherid");
        flowMeterCatcherDTO.setRootPid("test-rootid");
        flowMeterCatcherDTO.setSystemPid(1L);
        flowMeterCatcherDTO.setProject("test");
        flowMeterCatcherDTO.setJob("test");
        flowMeterCatcherDTO.setJobRepositoryId("test");
        flowMeterCatcherDTO.setJobVersion("test");
        flowMeterCatcherDTO.setContext("test");
        flowMeterCatcherDTO.setOrigin("test");
        flowMeterCatcherDTO.setLabel("test");
        flowMeterCatcherDTO.setCount(1);
        flowMeterCatcherDTO.setReference(1);
        flowMeterCatcherDTO.setThresholds("test");
    }

    @Test
    public void test() {

        FlowMeterCatcherDTO flowMeterCatcherDTO = new FlowMeterCatcherDTO();
        FlowMeterCatcherDTO flowMeterCatcherDTO1 = new FlowMeterCatcherDTO();

        assertTrue(flowMeterCatcherDTO.equals(flowMeterCatcherDTO1));

        flowMeterCatcherDTO.hashCode();
        flowMeterCatcherDTO.toString();
        init(flowMeterCatcherDTO);
        flowMeterCatcherDTO.hashCode();

        FlowMeterCatcherDTO flowMeterCatcherDTO2 = flowMeterCatcherDTO;
        assertTrue(flowMeterCatcherDTO2.equals(flowMeterCatcherDTO));

        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));
        assertFalse(flowMeterCatcherDTO1.equals(null));

        init(flowMeterCatcherDTO1);
        assertTrue(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setMoment(LocalDateTime.MIN.plusDays(1L));
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        flowMeterCatcherDTO1.setUuid("test-uuids");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setPid("test-pids");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setFatherPid("test-fatherids");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setRootPid("test-rootids");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setSystemPid(11L);
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setProject("tests");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setJob("tests");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setJobRepositoryId("tests");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setJobVersion("tests");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setContext("tests");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setOrigin("tests");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setLabel("tests");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setCount(11);
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setReference(11);
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));

        init(flowMeterCatcherDTO1);
        flowMeterCatcherDTO1.setThresholds("tests");
        assertFalse(flowMeterCatcherDTO1.equals(flowMeterCatcherDTO));
    }
}
