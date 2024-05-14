package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link TaskExecutionDTO}单元测试
 *
 * @author xingzilong
 * @date 2021/10/25
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TaskExecutionDTOTest {

    public void init(TaskExecutionDTO dto) {

        dto.setFailureTotal(100);
        dto.setServiceName("service");
        dto.setSuccessTotal(20);
    }

    @Test
    public void test() {
        TaskExecutionDTO dto = new TaskExecutionDTO();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        TaskExecutionDTO dtos = new TaskExecutionDTO();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);

        //equals方法未完全覆盖
        TaskExecutionDTO td = new TaskExecutionDTO("service", 20, 100);

        assertTrue(dto.equals(dtos));
        dtos.setServiceName("test");
        assertFalse(dto.equals(dtos));

        td.setFailureTotal(10);
        assertFalse(dto.equals(td));

        td.setFailureTotal(100);
        td.setSuccessTotal(2);
        assertFalse(dto.equals(td));
    }
}
