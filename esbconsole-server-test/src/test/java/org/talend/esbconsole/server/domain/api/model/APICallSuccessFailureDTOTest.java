package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link  APICallSuccessFailureDTO}单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class APICallSuccessFailureDTOTest {

    @Test
    public void test() {
        APICallSuccessFailureDTO dto = new APICallSuccessFailureDTO();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        dto.setFailureTotal(100L);
        dto.setSuccessTotal(20L);
        APICallSuccessFailureDTO dtos = new APICallSuccessFailureDTO();
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        APICallSuccessFailureDTO sf = new APICallSuccessFailureDTO(100L, 20L);
        sf.toString();

        //equals未完全覆盖
        dtos.setFailureTotal(100L);
        assertFalse(dto.equals(dtos));
        dtos.setSuccessTotal(20L);
        assertTrue(dto.equals(dtos));
    }

}
