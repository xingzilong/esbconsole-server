package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ApplicationTotalDTO}单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ApplicationTotalDTOTest {

    public void init(ApplicationTotalDTO dto) {

        dto.setName("test");
        dto.setValue(10L);
    }

    @Test
    public void test() {
        ApplicationTotalDTO dto = new ApplicationTotalDTO();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        ApplicationTotalDTO dtos = new ApplicationTotalDTO();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);
        ApplicationTotalDTO tas = new ApplicationTotalDTO("test", 10L);
        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setName("sss");
        assertFalse(dto.equals(dtos));
        tas.setValue(20L);
        assertFalse(dto.equals(tas));
    }
}
