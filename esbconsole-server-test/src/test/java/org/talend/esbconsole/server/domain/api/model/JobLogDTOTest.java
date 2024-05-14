package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JobLogDTO}单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JobLogDTOTest {
    public void init(JobLogDTO dto) {
        dto.setExecutTime(LocalDateTime.MIN);
        dto.setJob("job");
        dto.setServiceName("esb");
        dto.setTime(123456L);
        dto.setUuid("6789");
        dto.setStatus("ok");
    }

    @Test
    public void test() {
        JobLogDTO dto = new JobLogDTO();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        JobLogDTO dtos = new JobLogDTO();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);
        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setStatus("no");
        assertFalse(dto.equals(dtos));

        dtos.setStatus("ok");
        dtos.setJob("test");
        assertFalse(dto.equals(dtos));

        dtos.setJob("job");
        dtos.setServiceName("test");
        assertFalse(dto.equals(dtos));

        dtos.setServiceName("esb");
        dtos.setTime(12L);
        assertFalse(dto.equals(dtos));

        dtos.setTime(123456L);
        dtos.setUuid("789");
        assertFalse(dto.equals(dtos));

    }
}
