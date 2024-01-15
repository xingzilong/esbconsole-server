package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link APICallTotalAnalysisDTO}单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class APICallTotalAnalysisDTOTest {

    public void init(APICallTotalAnalysisDTO dto) {
        dto.setBestData(10.0d);
        dto.setDate(LocalDate.MIN);
        dto.setTotal(100L);
        dto.setUnit("ss");
    }

    @Test
    public void test() {
        APICallTotalAnalysisDTO dto = new APICallTotalAnalysisDTO();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        APICallTotalAnalysisDTO dtos = new APICallTotalAnalysisDTO();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);
        APICallTotalAnalysisDTO tas = new APICallTotalAnalysisDTO(LocalDate.now(), 100L);
        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setBestData(20.0d);
        assertFalse(dto.equals(dtos));
        dtos.setUnit("mm");
        assertFalse(dto.equals(dtos));
    }
}
