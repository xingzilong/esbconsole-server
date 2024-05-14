package org.talend.esbconsole.server.web.api.controller.joblog.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link StatCatcherVO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StatCatcherVOTest {

    public void init(StatCatcherVO statCatcherVO) {
        statCatcherVO.setMoment("2021-10-09");
        statCatcherVO.setUuid("test-id");
        statCatcherVO.setPid("test-id");
        statCatcherVO.setFatherPid("test-id");
        statCatcherVO.setRootPid("test-id");
        statCatcherVO.setSystemPid(1L);
        statCatcherVO.setProject("test-project");
        statCatcherVO.setJob("test-job");
        statCatcherVO.setJobRepositoryId("test-jobid");
        statCatcherVO.setJobVersion("test-jobversion");
        statCatcherVO.setContext("test-context");
        statCatcherVO.setOrigin("test-origin");
        statCatcherVO.setMessageType("test-messagetype");
        statCatcherVO.setMessage("test-message");
        statCatcherVO.setDuration(1L);
    }

    @Test
    public void test() {

        StatCatcherVO statCatcherVO = new StatCatcherVO();
        StatCatcherVO statCatcherVO1 = new StatCatcherVO();

        assertTrue(statCatcherVO.equals(statCatcherVO1));

        statCatcherVO.hashCode();
        statCatcherVO.toString();
        init(statCatcherVO);
        statCatcherVO.hashCode();

        StatCatcherVO statCatcherVO2 = statCatcherVO;
        assertTrue(statCatcherVO2.equals(statCatcherVO));

        assertFalse(statCatcherVO1.equals(statCatcherVO));
        assertFalse(statCatcherVO1.equals(null));

        init(statCatcherVO1);
        assertTrue(statCatcherVO1.equals(statCatcherVO));

        statCatcherVO1.setMoment("2021-10-10");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setUuid("test-ids");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setPid("test-ids");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setFatherPid("test-ids");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setRootPid("test-ids");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setSystemPid(10L);
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setProject("test-projects");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setJob("test-jobs");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setJobRepositoryId("test-jobids");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setJobVersion("test-jobversions");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setContext("test-contexts");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setOrigin("test-origins");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setMessageType("test-messagetypes");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setMessage("test-messages");
        assertFalse(statCatcherVO1.equals(statCatcherVO));

        init(statCatcherVO1);
        statCatcherVO1.setDuration(11L);
        assertFalse(statCatcherVO1.equals(statCatcherVO));
    }
}
