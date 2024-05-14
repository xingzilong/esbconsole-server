package org.talend.esbconsole.server.web.api.controller.joblog.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link LogCatcherVO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LogCatcherVOTest {

    public void init(LogCatcherVO logCatcherVO) {
        logCatcherVO.setMoment("2021-10-09");
        logCatcherVO.setUuid("test-id");
        logCatcherVO.setPid("test-id");
        logCatcherVO.setFatherPid("test-id");
        logCatcherVO.setRootPid("test-id");
        logCatcherVO.setProject("test-project");
        logCatcherVO.setJob("test-job");
        logCatcherVO.setContext("test-context");
        logCatcherVO.setPriority(1);
        logCatcherVO.setType("test-type");
        logCatcherVO.setOrigin("test-origin");
        logCatcherVO.setMessage("test-message");
        logCatcherVO.setCode(1);
    }

    @Test
    public void test() {

        LogCatcherVO logCatcherVO = new LogCatcherVO();
        LogCatcherVO logCatcherVO1 = new LogCatcherVO();

        assertTrue(logCatcherVO.equals(logCatcherVO1));

        logCatcherVO.hashCode();
        logCatcherVO.toString();
        init(logCatcherVO);
        logCatcherVO.hashCode();

        LogCatcherVO logCatcherVO2 = logCatcherVO;
        assertTrue(logCatcherVO2.equals(logCatcherVO));

        assertFalse(logCatcherVO1.equals(logCatcherVO));
        assertFalse(logCatcherVO1.equals(null));

        init(logCatcherVO1);
        assertTrue(logCatcherVO1.equals(logCatcherVO));

        logCatcherVO1.setMoment("2021-10-10");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setUuid("test-ids");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setPid("test-ids");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setFatherPid("test-ids");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setRootPid("test-ids");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setProject("test-projects");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setJob("test-jobs");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setContext("test-contexts");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setPriority(11);
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setType("test-types");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setOrigin("test-origins");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setMessage("test-messages");
        assertFalse(logCatcherVO1.equals(logCatcherVO));

        init(logCatcherVO1);
        logCatcherVO1.setCode(11);
        assertFalse(logCatcherVO1.equals(logCatcherVO));


    }

}
