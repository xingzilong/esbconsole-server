package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link StatCatcherDO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/18
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StatCatcherDOTest {

    public void init(StatCatcherDO statCatcherDO) {
        statCatcherDO.setMoment(LocalDateTime.MIN);
        statCatcherDO.setUuid("test-uuid");
        statCatcherDO.setPid("test-pid");
        statCatcherDO.setFatherPid("test-fatherid");
        statCatcherDO.setRootPid("test-rootid");
        statCatcherDO.setSystemPid(1L);
        statCatcherDO.setProject("test");
        statCatcherDO.setJob("test");
        statCatcherDO.setJobRepositoryId("test");
        statCatcherDO.setJobVersion("test");
        statCatcherDO.setContext("test");
        statCatcherDO.setOrigin("test");
        statCatcherDO.setMessage("test");
        statCatcherDO.setDuration(1L);
    }

    @Test
    public void test() {

        StatCatcherDO statCatcherDO = new StatCatcherDO();
        StatCatcherDO statCatcherDO1 = new StatCatcherDO();

        assertTrue(statCatcherDO.equals(statCatcherDO1));

        statCatcherDO.hashCode();
        statCatcherDO.toString();
        init(statCatcherDO);
        statCatcherDO.hashCode();

        StatCatcherDO statCatcherDO2 = statCatcherDO;
        assertTrue(statCatcherDO2.equals(statCatcherDO));

        assertFalse(statCatcherDO1.equals(statCatcherDO));
        assertFalse(statCatcherDO1.equals(null));

        init(statCatcherDO1);
        assertTrue(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setMoment(LocalDateTime.MIN.plusDays(1L));
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        statCatcherDO1.setUuid("test-uuids");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setPid("test-pids");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setFatherPid("test-fatherids");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setRootPid("test-rootids");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setSystemPid(11L);
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setProject("tests");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setJob("tests");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setJobRepositoryId("tests");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setJobVersion("tests");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setContext("tests");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setOrigin("tests");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setMessage("tests");
        assertFalse(statCatcherDO1.equals(statCatcherDO));

        init(statCatcherDO1);
        statCatcherDO1.setDuration(11L);
        assertFalse(statCatcherDO1.equals(statCatcherDO));
    }
}
