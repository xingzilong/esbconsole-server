package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link StatCatcherDTO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class StatCatcherDTOTest {

    public void init(StatCatcherDTO statCatcherDTO) {
        statCatcherDTO.setMoment(LocalDateTime.MIN);
        statCatcherDTO.setUuid("test-uuid");
        statCatcherDTO.setPid("test-pid");
        statCatcherDTO.setFatherPid("test-fatherid");
        statCatcherDTO.setRootPid("test-rootid");
        statCatcherDTO.setSystemPid(1L);
        statCatcherDTO.setProject("test");
        statCatcherDTO.setJob("test");
        statCatcherDTO.setJobRepositoryId("test");
        statCatcherDTO.setJobVersion("test");
        statCatcherDTO.setContext("test");
        statCatcherDTO.setOrigin("test");
        statCatcherDTO.setMessage("test");
        statCatcherDTO.setDuration(1L);
    }

    @Test
    public void test() {

        StatCatcherDTO statCatcherDTO = new StatCatcherDTO();
        StatCatcherDTO statCatcherDTO1 = new StatCatcherDTO();

        assertTrue(statCatcherDTO.equals(statCatcherDTO1));

        statCatcherDTO.hashCode();
        statCatcherDTO.toString();
        init(statCatcherDTO);
        statCatcherDTO.hashCode();

        StatCatcherDTO statCatcherDTO2 = statCatcherDTO;
        assertTrue(statCatcherDTO2.equals(statCatcherDTO));

        assertFalse(statCatcherDTO1.equals(statCatcherDTO));
        assertFalse(statCatcherDTO1.equals(null));

        init(statCatcherDTO1);
        assertTrue(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setMoment(LocalDateTime.MIN.plusDays(1L));
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        statCatcherDTO1.setUuid("test-uuids");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setPid("test-pids");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setFatherPid("test-fatherids");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setRootPid("test-rootids");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setSystemPid(11L);
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setProject("tests");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setJob("tests");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setJobRepositoryId("tests");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setJobVersion("tests");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setContext("tests");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setOrigin("tests");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setMessage("tests");
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));

        init(statCatcherDTO1);
        statCatcherDTO1.setDuration(11L);
        assertFalse(statCatcherDTO1.equals(statCatcherDTO));
    }
}
