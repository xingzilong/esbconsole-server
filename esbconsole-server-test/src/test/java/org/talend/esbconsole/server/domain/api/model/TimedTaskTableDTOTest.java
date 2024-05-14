package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link TimedTaskTableDTO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class TimedTaskTableDTOTest {

    private void init(TimedTaskTableDTO dto) {
        dto.setUuid("564984163");
        dto.setExecutTime(LocalDateTime.MIN);
        dto.setJob("test");
        dto.setStatus("1");
        dto.setTime(123L);
    }

    @Test
    public void test() {
        TimedTaskTableDTO timedTaskTableDTO = new TimedTaskTableDTO();
        TimedTaskTableDTO dto = timedTaskTableDTO;
        assertTrue(timedTaskTableDTO.equals(dto));

        dto = new TimedTaskTableDTO();
        assertTrue(dto.equals(timedTaskTableDTO));
        assertFalse(dto.equals(null));

        init(timedTaskTableDTO);
        timedTaskTableDTO.toString();
        timedTaskTableDTO.hashCode();

        init(dto);
        assertTrue(dto.equals(timedTaskTableDTO));

        dto.setUuid("711689");
        assertFalse(dto.equals(timedTaskTableDTO));

        init(dto);
        dto.setExecutTime(LocalDateTime.now());
        assertFalse(dto.equals(timedTaskTableDTO));

        init(dto);
        dto.setJob("tt");
        assertFalse(dto.equals(timedTaskTableDTO));

        init(dto);
        dto.setStatus("0");
        assertFalse(dto.equals(timedTaskTableDTO));

        init(dto);
        dto.setTime(65L);
        assertFalse(dto.equals(timedTaskTableDTO));

    }
}
