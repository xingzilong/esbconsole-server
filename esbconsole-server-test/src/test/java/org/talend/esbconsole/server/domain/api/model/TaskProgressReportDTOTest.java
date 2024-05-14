package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link TaskProgressReportDTO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/25
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class TaskProgressReportDTOTest {

    private void init(TaskProgressReportDTO dto) {
        dto.setDate(LocalDate.now());
        dto.setSuccessTotal(100L);
        dto.setFailureTotal(100L);
    }

    @Test
    public void test() {
        TaskProgressReportDTO taskProgressReportDTO = new TaskProgressReportDTO();
        TaskProgressReportDTO dto = taskProgressReportDTO;
        assertTrue(taskProgressReportDTO.equals(dto));

        dto = new TaskProgressReportDTO();
        assertTrue(dto.equals(taskProgressReportDTO));
        assertFalse(dto.equals(null));

        init(taskProgressReportDTO);
        taskProgressReportDTO.toString();
        taskProgressReportDTO.hashCode();

        init(dto);
        assertTrue(dto.equals(taskProgressReportDTO));

        dto.setDate(null);
        assertFalse(dto.equals(taskProgressReportDTO));

        init(dto);
        dto.setSuccessTotal(1000L);
        assertFalse(dto.equals(taskProgressReportDTO));

        init(dto);
        dto.setFailureTotal(1000L);
        assertFalse(dto.equals(taskProgressReportDTO));

    }
}
