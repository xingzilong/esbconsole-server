package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link LogCatcherDTO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LogCatcherDTOTest {

    public void init(LogCatcherDTO logCatcherDTO) {
        logCatcherDTO.setMoment(LocalDateTime.MIN);
        logCatcherDTO.setUuid("test-uuid");
        logCatcherDTO.setPid("test-pid");
        logCatcherDTO.setFatherPid("test-fatherid");
        logCatcherDTO.setRootPid("test-rootid");
        logCatcherDTO.setProject("test");
        logCatcherDTO.setJob("test");
        logCatcherDTO.setContext("test");
        logCatcherDTO.setPriority(1);
        logCatcherDTO.setType("test");
        logCatcherDTO.setOrigin("test");
        logCatcherDTO.setMessage("test");
        logCatcherDTO.setCode(1);
    }

    @Test
    public void test() {

        LogCatcherDTO logCatcherDTO = new LogCatcherDTO();
        LogCatcherDTO logCatcherDTO1 = new LogCatcherDTO();

        assertTrue(logCatcherDTO.equals(logCatcherDTO1));

        logCatcherDTO.hashCode();
        logCatcherDTO.toString();
        init(logCatcherDTO);
        logCatcherDTO.hashCode();

        LogCatcherDTO logCatcherDTO2 = logCatcherDTO;
        assertTrue(logCatcherDTO2.equals(logCatcherDTO));

        assertFalse(logCatcherDTO1.equals(logCatcherDTO));
        assertFalse(logCatcherDTO1.equals(null));

        init(logCatcherDTO1);
        assertTrue(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setMoment(LocalDateTime.MIN.plusDays(1L));
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        logCatcherDTO1.setUuid("test-uuids");
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setPid("test-pids");
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setFatherPid("test-fatherids");
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setRootPid("test-rootids");
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setProject("tests");
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setJob("tests");
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setContext("tests");
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setPriority(11);
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setType("tests");
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setOrigin("tests");
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setMessage("tests");
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));

        init(logCatcherDTO1);
        logCatcherDTO1.setCode(11);
        assertFalse(logCatcherDTO1.equals(logCatcherDTO));
    }
}
