package org.talend.esbconsole.server.web.api.controller.home;

import org.talend.esbconsole.server.domain.api.model.APICallSuccessFailureDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.ApplicationTotalDTO;
import org.talend.esbconsole.server.domain.api.model.TaskProgressReportDTO;
import org.talend.esbconsole.server.domain.api.model.TotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.service.HomeAnalysisService;
import org.talend.esbconsole.server.web.api.controller.home.converter.HomeWebConverter;
import org.talend.esbconsole.server.web.api.controller.home.vo.CallTotal4DateVO;
import org.talend.esbconsole.server.web.api.controller.home.vo.DataCallTotal4DateVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link HomeController} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class HomeControllerTest {

    private static final String PATH = "/api/home/analysis";

    private MockMvc mockMvc;

    @Mock
    private HomeAnalysisService homeAnalysisService;

    @Mock
    private HomeWebConverter homeWebConverter;

    @InjectMocks
    private HomeController homeController;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
        //构建mvc环境
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void getContentTopDataListTest() throws Exception {
        TotalAnalysisDTO data = Mockito.mock(TotalAnalysisDTO.class);
        Mockito.doReturn(data).when(homeAnalysisService).getContentTopDataList();
        mockMvc.perform(get(PATH + "/total")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(homeAnalysisService).getContentTopDataList();
    }

    @Test
    public void getApplitionAnalysisListTest() throws Exception {
        ApplicationTotalDTO applicationTotalDTO = Mockito.mock(ApplicationTotalDTO.class);
        Mockito.doReturn(Arrays.asList(applicationTotalDTO)).when(homeAnalysisService).getApplitionAnalysisDataList();
        mockMvc.perform(get(PATH + "/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(homeAnalysisService).getApplitionAnalysisDataList();
    }

    @Test
    public void getApiCallStatisticsListTest() throws Exception {
        APICallSuccessFailureDTO apiCallStatisticsData = Mockito.mock(APICallSuccessFailureDTO.class);
        Mockito.doReturn(apiCallStatisticsData).when(homeAnalysisService).getApiCallStatisticsData();
        mockMvc.perform(get(PATH + "/apiCallSuccessFail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(homeAnalysisService).getApiCallStatisticsData();
    }

    @Test
    public void getTaskProgressReportDataTest() throws Exception {
        TaskProgressReportDTO taskProgressReportDTO = Mockito.mock(TaskProgressReportDTO.class);
        Mockito.doReturn(Arrays.asList(taskProgressReportDTO)).when(homeAnalysisService).getTaskProgressReportData(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        mockMvc.perform(get(PATH + "/taskProgressReport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(homeAnalysisService).getTaskProgressReportData(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
    }

    @Test
    public void getDataCallTotal4DateTest() throws Exception {
        APICallTotalAnalysisDTO apiCallTotalAnalysisDTO = Mockito.mock(APICallTotalAnalysisDTO.class);
        DataCallTotal4DateVO dataCallTotal4DateVO = Mockito.mock(DataCallTotal4DateVO.class);
        Mockito.doReturn(Arrays.asList(apiCallTotalAnalysisDTO)).when(homeAnalysisService).getDataCallTotal4Date(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        Mockito.doReturn(Arrays.asList(dataCallTotal4DateVO)).when(homeWebConverter).dto2vo(Mockito.any(List.class));
        mockMvc.perform(get(PATH + "/dataCallTotal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(homeAnalysisService).getDataCallTotal4Date(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        Mockito.verify(homeWebConverter).dto2vo(Mockito.any(List.class));
    }

    @Test
    public void getCallTotal4DateTest() throws Exception {
        APICallTotalAnalysisDTO apiCallTotalAnalysisDTO = Mockito.mock(APICallTotalAnalysisDTO.class);
        CallTotal4DateVO callTotal4DateVO = Mockito.mock(CallTotal4DateVO.class);
        Mockito.doReturn(Arrays.asList(apiCallTotalAnalysisDTO)).when(homeAnalysisService).getCallTotal4Date(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        Mockito.doReturn(Arrays.asList(callTotal4DateVO)).when(homeWebConverter).dto2ct4dvo(Mockito.any(List.class));
        mockMvc.perform(get(PATH + "/callTotal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(homeAnalysisService).getCallTotal4Date(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        Mockito.verify(homeWebConverter).dto2ct4dvo(Mockito.any(List.class));
    }
}
