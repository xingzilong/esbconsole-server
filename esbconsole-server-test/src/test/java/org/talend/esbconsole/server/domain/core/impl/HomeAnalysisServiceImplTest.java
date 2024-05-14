package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.TaskProgressReportDTO;
import org.talend.esbconsole.server.domain.core.util.datafilling.DataFillingUtil;
import org.talend.esbconsole.server.domain.repository.mapper.EventsDAO;
import org.talend.esbconsole.server.domain.repository.mapper.FlowMeterCatcherDAO;
import org.talend.esbconsole.server.domain.repository.mapper.ServiceDAO;
import org.talend.esbconsole.server.domain.repository.mapper.StatCatcherDAO;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * {@link HomeAnalysisServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/12
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class HomeAnalysisServiceImplTest {

    @Mock
    ServiceDAO serviceDAO;

    @Mock
    FlowMeterCatcherDAO flowMeterCatcherDAO;

    @Mock
    EventsDAO eventsDAO;

    @Mock
    StatCatcherDAO statCatcherDAO;

    @InjectMocks
    HomeAnalysisServiceImpl homeAnalysisService;

    @Test
    public void getContentTopDataList() {
        when(serviceDAO.getServiceTotal()).thenReturn(10L);
        when(serviceDAO.getServiceTotal4API()).thenReturn(15L);
        when(serviceDAO.getServiceTotal4TimedRoute()).thenReturn(20L);
        when(serviceDAO.getServiceTotal4ConventionalRoute()).thenReturn(25L);

        when(flowMeterCatcherDAO.getFlowMeterTotal()).thenReturn(15L);
        when(eventsDAO.getCallTotal()).thenReturn(20L);

        homeAnalysisService.getContentTopDataList();

        verify(serviceDAO).getServiceTotal();
        verify(serviceDAO).getServiceTotal4API();
        verify(serviceDAO).getServiceTotal4TimedRoute();
        verify(serviceDAO).getServiceTotal4ConventionalRoute();
        verify(flowMeterCatcherDAO).getFlowMeterTotal();
        verify(eventsDAO).getCallTotal();
    }

    @Test
    public void getApplitionAnalysisDataListTest() {
        when(serviceDAO.getServiceTotal4RestFulAPI()).thenReturn(10L);
        when(serviceDAO.getServiceTotal4SOAPAPI()).thenReturn(10L);
        when(serviceDAO.getServiceTotal4ProxyRoute()).thenReturn(10L);
        when(serviceDAO.getServiceTotal4TimedRoute()).thenReturn(10L);
        when(serviceDAO.getServiceTotal4ConventionalRoute()).thenReturn(10L);

        homeAnalysisService.getApplitionAnalysisDataList();

        verify(serviceDAO).getServiceTotal4RestFulAPI();
        verify(serviceDAO).getServiceTotal4SOAPAPI();
        verify(serviceDAO).getServiceTotal4ProxyRoute();
        verify(serviceDAO).getServiceTotal4TimedRoute();
        verify(serviceDAO).getServiceTotal4ConventionalRoute();

    }

    @Test
    public void getApiCallStatisticsDataTest() {
        when(eventsDAO.getCallTotal4Success()).thenReturn(10L);
        when(eventsDAO.getCallTotal4Failure()).thenReturn(10L);

        homeAnalysisService.getApiCallStatisticsData();

        verify(eventsDAO).getCallTotal4Success();
        verify(eventsDAO).getCallTotal4Failure();
    }


    @Test
    public void getTaskProgressReportDataTest() {
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        when(startDate.until(Mockito.any(), Mockito.any())).thenReturn(10L);

        LocalDate currentDate = mock(LocalDate.class);
        when(startDate.plus(Mockito.anyLong(), Mockito.eq(ChronoUnit.DAYS))).thenReturn(currentDate);

        List<TaskProgressReportDTO> taskProgressReportDTOS = new ArrayList<TaskProgressReportDTO>();
        // dto.getDate().equals(currentDate)  不相等
        TaskProgressReportDTO taskProgressReportDTO = new TaskProgressReportDTO();
        taskProgressReportDTO.setDate(startDate);
        taskProgressReportDTOS.add(taskProgressReportDTO);

        when(statCatcherDAO.listTaskProgressReportTotal(Mockito.any(), Mockito.any())).thenReturn(taskProgressReportDTOS);

        homeAnalysisService.getTaskProgressReportData(startDate, endDate);

        verify(statCatcherDAO).listTaskProgressReportTotal(Mockito.any(), Mockito.any());
        verify(startDate).until(Mockito.any(), Mockito.any());
    }

    @Test
    public void getDataCallTotal4DateTest() {
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        when(startDate.until(Mockito.any(), Mockito.any())).thenReturn(10L);

        List<APICallTotalAnalysisDTO> apiCallTotalAnalysisDTOS = new ArrayList<APICallTotalAnalysisDTO>();
        when(eventsDAO.listDataCallTotalByEiTimestamp(Mockito.any(), Mockito.any())).thenReturn(apiCallTotalAnalysisDTOS);

        MockedStatic<DataFillingUtil> dataFillingUtilStatic = mockStatic(DataFillingUtil.class);
        dataFillingUtilStatic.when(() -> DataFillingUtil.filling41024(Mockito.any(), Mockito.any(), Mockito.any())).thenAnswer((Answer<Void>) invocation -> null);

        homeAnalysisService.getDataCallTotal4Date(startDate, endDate);

        verify(eventsDAO).listDataCallTotalByEiTimestamp(Mockito.any(), Mockito.any());

        dataFillingUtilStatic.close();
    }

    @Test
    public void getCallTotal4DateTest() {
        LocalDate startDate = mock(LocalDate.class);
        LocalDate endDate = mock(LocalDate.class);
        when(startDate.until(Mockito.any(), Mockito.any())).thenReturn(10L);

        List<APICallTotalAnalysisDTO> apiCallTotalAnalysisDTOS = new ArrayList<APICallTotalAnalysisDTO>();
        when(eventsDAO.listCallTotalByEiTimestamp(Mockito.any(), Mockito.any())).thenReturn(apiCallTotalAnalysisDTOS);

        MockedStatic<DataFillingUtil> dataFillingUtilStatic = mockStatic(DataFillingUtil.class);
        dataFillingUtilStatic.when(() -> DataFillingUtil.filling410000(Mockito.any(), Mockito.any(), Mockito.any())).thenAnswer((Answer<Void>) invocation -> null);

        homeAnalysisService.getCallTotal4Date(startDate, endDate);
        verify(eventsDAO).listCallTotalByEiTimestamp(Mockito.any(), Mockito.any());

        dataFillingUtilStatic.close();
    }

}
