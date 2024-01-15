package org.talend.esbconsole.server.domain.core.util.datafilling;

import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * {@link DataFillingUtil} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/12
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class DataFillingUtilTest {

    @InjectMocks
    DataFillingUtil dataFillingUtil;

    @Test
    public void fillingTest() {
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);

        List<APICallTotalAnalysisDTO> sourceDataList = new ArrayList<APICallTotalAnalysisDTO>();
        APICallTotalAnalysisDTO apiCallTotalAnalysisDTO = new APICallTotalAnalysisDTO();
        apiCallTotalAnalysisDTO.setDate(startDate);
        sourceDataList.add(apiCallTotalAnalysisDTO);

        when(startDate.until(Mockito.any(), Mockito.any())).thenReturn(10L);

        LocalDate currentDate = mock(LocalDate.class);
        when(startDate.plus(Mockito.anyLong(), Mockito.eq(ChronoUnit.DAYS))).thenReturn(currentDate);


        dataFillingUtil.filling(sourceDataList, startDate, endDate);
    }

    @Test
    public void filling410000Test() {
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);

        List<APICallTotalAnalysisDTO> sourceDataList = new ArrayList<APICallTotalAnalysisDTO>();
        APICallTotalAnalysisDTO apiCallTotalDTO = new APICallTotalAnalysisDTO();
        apiCallTotalDTO.setDate(startDate);
        apiCallTotalDTO.setTotal(123L);
        sourceDataList.add(apiCallTotalDTO);


        when(startDate.until(Mockito.any(), Mockito.any())).thenReturn(10L);

        LocalDate currentDate = mock(LocalDate.class);
        when(startDate.plus(Mockito.anyLong(), Mockito.eq(ChronoUnit.DAYS))).thenReturn(currentDate);

        dataFillingUtil.filling410000(sourceDataList, startDate, endDate);

        APICallTotalAnalysisDTO apiDTO = new APICallTotalAnalysisDTO();
        apiDTO.setDate(startDate);
        apiDTO.setTotal(10000L);
        sourceDataList.add(apiDTO);

        dataFillingUtil.filling410000(sourceDataList, startDate, endDate);


        APICallTotalAnalysisDTO apiCallTotalAnalysisDTO = new APICallTotalAnalysisDTO();
        apiCallTotalAnalysisDTO.setDate(startDate);
        apiCallTotalAnalysisDTO.setTotal((10000L * 10000) + 1);
        sourceDataList.add(apiCallTotalAnalysisDTO);

        dataFillingUtil.filling410000(sourceDataList, startDate, endDate);

    }

    @Test
    public void filling41024Test() {
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        when(startDate.until(Mockito.any(), Mockito.any())).thenReturn(10L);

        LocalDate currentDate = mock(LocalDate.class);
        when(startDate.plus(Mockito.anyLong(), Mockito.eq(ChronoUnit.DAYS))).thenReturn(currentDate);

        List<APICallTotalAnalysisDTO> sourceDataList = new ArrayList<APICallTotalAnalysisDTO>();

        APICallTotalAnalysisDTO aPICallDTO = new APICallTotalAnalysisDTO();
        aPICallDTO.setDate(startDate);
        aPICallDTO.setTotal(1023L);

        sourceDataList.add(aPICallDTO);
        dataFillingUtil.filling41024(sourceDataList, startDate, endDate);


        APICallTotalAnalysisDTO apiCallTotalAnalysisDTO = new APICallTotalAnalysisDTO();
        apiCallTotalAnalysisDTO.setDate(startDate);
        apiCallTotalAnalysisDTO.setTotal(1024L);

        sourceDataList.add(apiCallTotalAnalysisDTO);
        dataFillingUtil.filling41024(sourceDataList, startDate, endDate);

        APICallTotalAnalysisDTO apiCallTotalDTO = new APICallTotalAnalysisDTO();
        apiCallTotalDTO.setDate(startDate);
        apiCallTotalDTO.setTotal(1024 * 1024L);

        sourceDataList.add(apiCallTotalDTO);
        dataFillingUtil.filling41024(sourceDataList, startDate, endDate);

        APICallTotalAnalysisDTO apiDTO = new APICallTotalAnalysisDTO();
        apiDTO.setDate(startDate);
        apiDTO.setTotal(1024 * 1024 * 1024L + 1);

        sourceDataList.add(apiDTO);
        dataFillingUtil.filling41024(sourceDataList, startDate, endDate);
    }

}
