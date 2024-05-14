package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link LogCatcherDO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/18
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LogCatcherDOTest {

    public void init(LogCatcherDO logCatcherDO) {
        logCatcherDO.setMoment(LocalDateTime.MIN);
        logCatcherDO.setUuid("test-uuid");
        logCatcherDO.setPid("test-pid");
        logCatcherDO.setFatherPid("test-fatherid");
        logCatcherDO.setRootPid("test-rootid");
        logCatcherDO.setProject("test");
        logCatcherDO.setJob("test");
        logCatcherDO.setContext("test");
        logCatcherDO.setPriority(1);
        logCatcherDO.setType("test");
        logCatcherDO.setOrigin("test");
        logCatcherDO.setMessage("test");
        logCatcherDO.setCode(1);
    }

    @Test
    public void test() {

        LogCatcherDO logCatcherDO = new LogCatcherDO();
        LogCatcherDO logCatcherDO1 = new LogCatcherDO();

        assertTrue(logCatcherDO.equals(logCatcherDO1));

        logCatcherDO.hashCode();
        logCatcherDO.toString();
        init(logCatcherDO);
        logCatcherDO.hashCode();

        LogCatcherDO logCatcherDO2 = logCatcherDO;
        assertTrue(logCatcherDO2.equals(logCatcherDO));

        assertFalse(logCatcherDO1.equals(logCatcherDO));
        assertFalse(logCatcherDO1.equals(null));

        init(logCatcherDO1);
        assertTrue(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setMoment(LocalDateTime.MIN.plusDays(1L));
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        logCatcherDO1.setUuid("test-uuids");
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setPid("test-pids");
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setFatherPid("test-fatherids");
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setRootPid("test-rootids");
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setProject("tests");
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setJob("tests");
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setContext("tests");
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setPriority(11);
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setType("tests");
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setOrigin("tests");
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setMessage("tests");
        assertFalse(logCatcherDO1.equals(logCatcherDO));

        init(logCatcherDO1);
        logCatcherDO1.setCode(11);
        assertFalse(logCatcherDO1.equals(logCatcherDO));
    }
}
