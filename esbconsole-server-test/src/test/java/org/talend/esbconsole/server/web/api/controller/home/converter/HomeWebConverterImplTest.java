package org.talend.esbconsole.server.web.api.controller.home.converter;

import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.web.api.controller.home.vo.CallTotal4DateVO;
import org.talend.esbconsole.server.web.api.controller.home.vo.DataCallTotal4DateVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link HomeWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class HomeWebConverterImplTest {

    @InjectMocks
    private HomeWebConverterImpl homeWebConverterImpl;

    @Test
    public void dto2vo4APICallTotalAnalysisDTOTest() {
        APICallTotalAnalysisDTO apiCallTotalAnalysisDTO = new APICallTotalAnalysisDTO();
        apiCallTotalAnalysisDTO.setDate(LocalDate.now());
        DataCallTotal4DateVO dataCallTotal4DateVO = homeWebConverterImpl.dto2vo(apiCallTotalAnalysisDTO);
        assertNotNull(dataCallTotal4DateVO);
        apiCallTotalAnalysisDTO = null;
        DataCallTotal4DateVO dataCallTotal4DateVO1 = homeWebConverterImpl.dto2vo(apiCallTotalAnalysisDTO);
        assertNull(dataCallTotal4DateVO1);
    }

    @Test
    public void dto2vo4APICallTotalAnalysisDTOListTest() {
        List<APICallTotalAnalysisDTO> apiCallTotalAnalysisDTOS = new ArrayList<>();
        apiCallTotalAnalysisDTOS.add(new APICallTotalAnalysisDTO());
        List<DataCallTotal4DateVO> list = homeWebConverterImpl.dto2vo(apiCallTotalAnalysisDTOS);
        assertNotNull(list);
        apiCallTotalAnalysisDTOS = null;
        List<DataCallTotal4DateVO> list1 = homeWebConverterImpl.dto2vo(apiCallTotalAnalysisDTOS);
        assertNull(list1);
    }

    @Test
    public void dto2ct4dvo4APICallTotalAnalysisDTOTest() {
        APICallTotalAnalysisDTO apiCallTotalAnalysisDTO = new APICallTotalAnalysisDTO();
        apiCallTotalAnalysisDTO.setDate(LocalDate.now());
        apiCallTotalAnalysisDTO.setTotal(1024L);
        CallTotal4DateVO callTotal4DateVO = homeWebConverterImpl.dto2ct4dvo(apiCallTotalAnalysisDTO);
        assertNotNull(callTotal4DateVO);
        apiCallTotalAnalysisDTO = null;
        CallTotal4DateVO callTotal4DateVO1 = homeWebConverterImpl.dto2ct4dvo(apiCallTotalAnalysisDTO);
        assertNull(callTotal4DateVO1);
    }

    @Test
    public void dto2ct4dvo4APICallTotalAnalysisDTOListTest() {
        List<APICallTotalAnalysisDTO> apiCallTotalAnalysisDTOS = new ArrayList<>();
        apiCallTotalAnalysisDTOS.add(new APICallTotalAnalysisDTO());
        List<CallTotal4DateVO> list = homeWebConverterImpl.dto2ct4dvo(apiCallTotalAnalysisDTOS);
        assertNotNull(list);
        apiCallTotalAnalysisDTOS = null;
        List<CallTotal4DateVO> list1 = homeWebConverterImpl.dto2ct4dvo(apiCallTotalAnalysisDTOS);
        assertNull(list1);
    }

}
