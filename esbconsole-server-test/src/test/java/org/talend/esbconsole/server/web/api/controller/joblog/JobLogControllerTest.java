package org.talend.esbconsole.server.web.api.controller.joblog;

import com.alibaba.fastjson2.JSONObject;
import org.talend.esbconsole.server.domain.api.model.JobLogAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.JobLogDTO;
import org.talend.esbconsole.server.domain.api.param.JobLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.service.JobLogService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.joblog.converter.JobLogWebConverter;
import org.talend.esbconsole.server.web.api.controller.joblog.request.JobLogPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.JobLogAnalysisVO;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.JobLogVO;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link JobLogController} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class JobLogControllerTest {

    private static final String PATH = "/api/monitor/jobLog";

    private MockMvc mockMvc;

    @Mock
    private JobLogService jobLogService;

    @Mock
    private JobLogWebConverter jobLogWebConverter;

    @InjectMocks
    private JobLogController jobLogController;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
        //构建mvc环境
        mockMvc = MockMvcBuilders.standaloneSetup(jobLogController).build();
    }

    @Test
    public void getJobLogsTest() throws Exception {
        JobLogPageQueryRequest request = Mockito.mock(JobLogPageQueryRequest.class);
        JobLogPageQueryParam jobLogPageQueryParam = Mockito.mock(JobLogPageQueryParam.class);
        Mockito.doReturn(jobLogPageQueryParam).when(jobLogWebConverter).req2param(Mockito.any(JobLogPageQueryRequest.class));
        PageResult<JobLogDTO> result = Mockito.mock(PageResult.class);
        Mockito.doReturn(result).when(jobLogService).getJobLogs(Mockito.any(JobLogPageQueryParam.class));
        JobLogVO jobLogVO = Mockito.mock(JobLogVO.class);
        Mockito.doReturn(new ArrayList<JobLogDTO>()).when(result).getList();
        Mockito.doReturn(Arrays.asList(jobLogVO)).when(jobLogWebConverter).dto2vo(Mockito.anyList());
        mockMvc.perform(post(PATH + "/list")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(jobLogService).getJobLogs(Mockito.any(JobLogPageQueryParam.class));
        Mockito.verify(jobLogWebConverter).req2param(Mockito.any(JobLogPageQueryRequest.class));
    }

    @Test
    public void getJobLogAnalysisTest() throws Exception {
        JobLogAnalysisDTO jobLogAnalysisDTO = Mockito.mock(JobLogAnalysisDTO.class);
        Mockito.doReturn(jobLogAnalysisDTO).when(jobLogService).getJobLogAnalysis(Mockito.anyString());
        JobLogAnalysisVO jobLogAnalysisVO = Mockito.mock(JobLogAnalysisVO.class);
        Mockito.doReturn(jobLogAnalysisVO).when(jobLogWebConverter).dto2vo(jobLogAnalysisDTO);
        mockMvc.perform(get(PATH + "/jobLogAnalysis")
                        .param("uuid", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(jobLogService).getJobLogAnalysis(Mockito.anyString());
    }

}
