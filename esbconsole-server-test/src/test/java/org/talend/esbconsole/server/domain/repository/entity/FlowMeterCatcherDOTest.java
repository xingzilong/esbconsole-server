package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FlowMeterCatcherDO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/18
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FlowMeterCatcherDOTest {

    public void init(FlowMeterCatcherDO flowMeterCatcherDO) {
        flowMeterCatcherDO.setMoment(LocalDateTime.MIN);
        flowMeterCatcherDO.setUuid("test-uuid");
        flowMeterCatcherDO.setPid("test-pid");
        flowMeterCatcherDO.setFatherPid("test-fatherid");
        flowMeterCatcherDO.setRootPid("test-rootid");
        flowMeterCatcherDO.setSystemPid(1L);
        flowMeterCatcherDO.setProject("test");
        flowMeterCatcherDO.setJob("test");
        flowMeterCatcherDO.setJobRepositoryId("test");
        flowMeterCatcherDO.setJobVersion("test");
        flowMeterCatcherDO.setContext("test");
        flowMeterCatcherDO.setOrigin("test");
        flowMeterCatcherDO.setLabel("test");
        flowMeterCatcherDO.setCount(1);
        flowMeterCatcherDO.setReference(1);
        flowMeterCatcherDO.setThresholds("test");
    }

    @Test
    public void test() {

        FlowMeterCatcherDO flowMeterCatcherDO = new FlowMeterCatcherDO();
        FlowMeterCatcherDO flowMeterCatcherDO1 = new FlowMeterCatcherDO();

        assertTrue(flowMeterCatcherDO.equals(flowMeterCatcherDO1));

        flowMeterCatcherDO.hashCode();
        flowMeterCatcherDO.toString();
        init(flowMeterCatcherDO);
        flowMeterCatcherDO.hashCode();

        FlowMeterCatcherDO flowMeterCatcherDO2 = flowMeterCatcherDO;
        assertTrue(flowMeterCatcherDO2.equals(flowMeterCatcherDO));

        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));
        assertFalse(flowMeterCatcherDO1.equals(null));

        init(flowMeterCatcherDO1);
        assertTrue(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setMoment(LocalDateTime.MIN.plusDays(1L));
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        flowMeterCatcherDO1.setUuid("test-uuids");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setPid("test-pids");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setFatherPid("test-fatherids");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setRootPid("test-rootids");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setSystemPid(11L);
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setProject("tests");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setJob("tests");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setJobRepositoryId("tests");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setJobVersion("tests");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setContext("tests");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setOrigin("tests");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setLabel("tests");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setCount(11);
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setReference(11);
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));

        init(flowMeterCatcherDO1);
        flowMeterCatcherDO1.setThresholds("tests");
        assertFalse(flowMeterCatcherDO1.equals(flowMeterCatcherDO));
    }
}
