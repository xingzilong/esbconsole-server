package org.talend.esbconsole.server.web.api.controller.joblog.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FlowMeterCatcherVO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FlowMeterCatcherVOTest {

    public void init(FlowMeterCatcherVO flowMeterCatcherVO) {
        flowMeterCatcherVO.setMoment("2023-10-09");
        flowMeterCatcherVO.setUuid("test-id");
        flowMeterCatcherVO.setPid("test-id");
        flowMeterCatcherVO.setFatherPid("test-id");
        flowMeterCatcherVO.setRootPid("test-id");
        flowMeterCatcherVO.setSystemPid(1L);
        flowMeterCatcherVO.setProject("test-project");
        flowMeterCatcherVO.setJob("test-job");
        flowMeterCatcherVO.setJobRepositoryId("test-jobid");
        flowMeterCatcherVO.setJobVersion("test-jobversion");
        flowMeterCatcherVO.setContext("test-context");
        flowMeterCatcherVO.setOrigin("test-origin");
        flowMeterCatcherVO.setLabel("test-lable");
        flowMeterCatcherVO.setCount(1);
        flowMeterCatcherVO.setReference(1);
        flowMeterCatcherVO.setThresholds("10");
    }

    @Test
    public void test() {

        FlowMeterCatcherVO flowMeterCatcherVO = new FlowMeterCatcherVO();
        FlowMeterCatcherVO flowMeterCatcherVO1 = new FlowMeterCatcherVO();

        assertTrue(flowMeterCatcherVO.equals(flowMeterCatcherVO1));

        flowMeterCatcherVO.hashCode();
        flowMeterCatcherVO.toString();
        init(flowMeterCatcherVO);
        flowMeterCatcherVO.hashCode();

        FlowMeterCatcherVO flowMeterCatcherVO2 = flowMeterCatcherVO;
        assertTrue(flowMeterCatcherVO2.equals(flowMeterCatcherVO));

        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));
        assertFalse(flowMeterCatcherVO1.equals(null));

        init(flowMeterCatcherVO1);
        assertTrue(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        flowMeterCatcherVO1.setMoment("2023-10-10");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setUuid("test-ids");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setPid("test-ids");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setFatherPid("test-ids");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setRootPid("test-ids");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setSystemPid(10L);
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setProject("test-projects");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setJob("test-jobs");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setJobRepositoryId("test-jobids");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setJobVersion("test-jobversions");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setContext("test-contexts");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setOrigin("test-origins");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setLabel("test-lables");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setCount(11);
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setReference(11);
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));

        init(flowMeterCatcherVO1);
        flowMeterCatcherVO1.setThresholds("101");
        assertFalse(flowMeterCatcherVO1.equals(flowMeterCatcherVO));
    }

}
