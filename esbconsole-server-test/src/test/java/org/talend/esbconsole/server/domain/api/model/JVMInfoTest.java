package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JVMInfo}单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JVMInfoTest {

    public void init(JVMInfo dto) {
        dto.setHeapMemory(new JVMMemoryInfo());
        dto.setMemoryPool(Arrays.asList(new JVMMemoryInfo()));
        dto.setNoHeapMemory(new JVMMemoryInfo());
    }

    @Test
    public void test() {
        JVMInfo dto = new JVMInfo();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        JVMInfo dtos = new JVMInfo();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);
        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setHeapMemory(null);
        assertFalse(dto.equals(dtos));

        dtos.setHeapMemory(new JVMMemoryInfo());
        dtos.setNoHeapMemory(null);
        assertFalse(dto.equals(dtos));

        dtos.setNoHeapMemory(new JVMMemoryInfo());
        dtos.setMemoryPool(null);
        assertFalse(dto.equals(dtos));
    }
}
