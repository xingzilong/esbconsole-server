package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link TaskTimeConsumptionDTO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/25
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class TaskTimeConsumptionDTOTest {

    private void init(TaskTimeConsumptionDTO dto) {
        dto.setExecutionTime(new ArrayList<String>());
        dto.setServiceName("test");
        dto.setTimeConsumed(new ArrayList<Long>());
        dto.setUnit("3000");
    }

    @Test
    public void test() {
        TaskTimeConsumptionDTO taskTimeConsumptionDTO = new TaskTimeConsumptionDTO();
        TaskTimeConsumptionDTO dto = taskTimeConsumptionDTO;
        assertTrue(taskTimeConsumptionDTO.equals(dto));

        dto = new TaskTimeConsumptionDTO();
        assertTrue(dto.equals(taskTimeConsumptionDTO));
        assertFalse(dto.equals(null));

        init(taskTimeConsumptionDTO);
        taskTimeConsumptionDTO.toString();
        taskTimeConsumptionDTO.hashCode();

        init(dto);
        assertTrue(dto.equals(taskTimeConsumptionDTO));

        dto.setExecutionTime(null);
        assertFalse(dto.equals(taskTimeConsumptionDTO));

        init(dto);
        dto.setServiceName("tt");
        assertFalse(dto.equals(taskTimeConsumptionDTO));

        init(dto);
        dto.setTimeConsumed(null);
        assertFalse(dto.equals(taskTimeConsumptionDTO));

        init(dto);
        dto.setUnit("500");
        assertFalse(dto.equals(taskTimeConsumptionDTO));

    }
}
