package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * {@link APICallAnalysisTableDTO}单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class APICallAnalysisTableDTOTest {

    public void init(APICallAnalysisTableDTO dto) {
        dto.setAvgResponseTime(10.0d);
        dto.setCallFailureTotal(100L);
        dto.setCallFailureTotal4Flow(50L);
        dto.setCallFailureTotal4Frequency(20L);
        dto.setCallFailureTotal4IP(15L);
        dto.setCallTotal(200L);
        dto.setConsumerIP("127.0.0.1");
        dto.setConsumerSystem("esb");
    }

    @Test
    public void test() {
        APICallAnalysisTableDTO dto = new APICallAnalysisTableDTO();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        APICallAnalysisTableDTO dtos = new APICallAnalysisTableDTO();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);

        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setConsumerSystem("ksp");
        assertFalse(dto.equals(dtos));
        dtos.setCallFailureTotal(1L);
        assertFalse(dto.equals(dtos));
    }

}
