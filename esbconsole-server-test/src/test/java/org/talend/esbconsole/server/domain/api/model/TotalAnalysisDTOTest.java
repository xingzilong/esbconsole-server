package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link TotalAnalysisDTO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class TotalAnalysisDTOTest {

    private void init(TotalAnalysisDTO dto) {
        dto.setApiCallTotal(20L);
        dto.setApiTotal(15L);
        dto.setConventionalRouteTotal(20L);
        dto.setDataTransmissionTotal(20L);
        dto.setServiceTotal(30L);
        dto.setTimedRouteTotal(15L);
    }

    @Test
    public void test() {
        TotalAnalysisDTO totalAnalysisDTO = new TotalAnalysisDTO();
        TotalAnalysisDTO dto = totalAnalysisDTO;
        assertTrue(totalAnalysisDTO.equals(dto));

        dto = new TotalAnalysisDTO();
        assertTrue(dto.equals(totalAnalysisDTO));
        assertFalse(dto.equals(null));

        init(totalAnalysisDTO);
        totalAnalysisDTO.toString();
        totalAnalysisDTO.hashCode();

        init(dto);
        assertTrue(dto.equals(totalAnalysisDTO));

        dto.setApiCallTotal(10L);
        assertFalse(dto.equals(totalAnalysisDTO));

        init(dto);
        dto.setApiTotal(10L);
        assertFalse(dto.equals(totalAnalysisDTO));

        init(dto);
        dto.setConventionalRouteTotal(10L);
        assertFalse(dto.equals(totalAnalysisDTO));

        init(dto);
        dto.setDataTransmissionTotal(10L);
        assertFalse(dto.equals(totalAnalysisDTO));

        init(dto);
        dto.setServiceTotal(10L);
        assertFalse(dto.equals(totalAnalysisDTO));

        init(dto);
        dto.setTimedRouteTotal(10L);
        assertFalse(dto.equals(totalAnalysisDTO));


    }
}
