package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JVMMemoryInfo}单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JVMMemoryInfoTest {

    public void init(JVMMemoryInfo dto) {
        dto.setCommitted(1L);
        dto.setInit(2L);
        dto.setMax(10L);
        dto.setName("test");
        dto.setType("type");
        dto.setUsed(156L);
    }

    @Test
    public void test() {
        JVMMemoryInfo dto = new JVMMemoryInfo();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        JVMMemoryInfo dtos = new JVMMemoryInfo();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);
        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setCommitted(8L);
        assertFalse(dto.equals(dtos));

        dtos.setCommitted(1L);
        dtos.setInit(6L);
        assertFalse(dto.equals(dtos));

        dtos.setInit(2L);
        dtos.setMax(45L);
        assertFalse(dto.equals(dtos));

        dtos.setMax(10L);
        dtos.setName("ppp");
        assertFalse(dto.equals(dtos));

        dtos.setName("test");
        dtos.setType("kfc");
        assertFalse(dto.equals(dtos));

        dtos.setType("type");
        dtos.setUsed(4553134L);
        assertFalse(dto.equals(dtos));

        JVMMemoryInfo info = new JVMMemoryInfo();
        info.setCommitted(null);
        info.setInit(null);
        info.setMax(null);
        info.setUsed(null);
        info.hashCode();
        assertFalse(info.equals(dtos));

    }
}
