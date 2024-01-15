package org.talend.esbconsole.server.web.api.controller.joblog.converter;

import org.talend.esbconsole.server.domain.api.model.FlowMeterCatcherDTO;
import org.talend.esbconsole.server.domain.api.model.JobLogAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.JobLogDTO;
import org.talend.esbconsole.server.domain.api.model.LogCatcherDTO;
import org.talend.esbconsole.server.domain.api.model.StatCatcherDTO;
import org.talend.esbconsole.server.domain.api.param.JobLogPageQueryParam;
import org.talend.esbconsole.server.web.api.controller.joblog.request.JobLogPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.FlowMeterCatcherVO;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.JobLogAnalysisVO;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.JobLogVO;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.LogCatcherVO;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.StatCatcherVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link JobLogWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JobLogWebConverterImplTest {

    @InjectMocks
    private JobLogWebConverterImpl jobLogWebConverterImpl;

    @Test
    public void req2param4JobLogPageQueryRequestTest() {
        JobLogPageQueryRequest request = new JobLogPageQueryRequest();
        JobLogPageQueryParam jobLogPageQueryParam = jobLogWebConverterImpl.req2param(request);
        assertNotNull(jobLogPageQueryParam);
        request = null;
        JobLogPageQueryParam jobLogPageQueryParam1 = jobLogWebConverterImpl.req2param(request);
        assertNull(jobLogPageQueryParam1);
    }

    @Test
    public void dto2vo4JobLogAnalysisDTOTest() {
        JobLogAnalysisDTO jobLogAnalysisDTO = new JobLogAnalysisDTO();
        jobLogAnalysisDTO.setComponentTimeDataList(new ArrayList<>());
        JobLogAnalysisVO jobLogAnalysisVO = jobLogWebConverterImpl.dto2vo(jobLogAnalysisDTO);
        assertNotNull(jobLogAnalysisVO);
        jobLogAnalysisDTO.setComponentTimeDataList(null);
        jobLogAnalysisDTO.setLogCatcherRecord(Arrays.asList(new LogCatcherDTO()));
        jobLogAnalysisDTO.setFlowMeterCatcherRecord(Arrays.asList(new FlowMeterCatcherDTO()));
        jobLogAnalysisDTO.setStatCatcherRecord(Arrays.asList(new StatCatcherDTO()));
        JobLogAnalysisVO jobLogAnalysisVO1 = jobLogWebConverterImpl.dto2vo(jobLogAnalysisDTO);
        assertNotNull(jobLogAnalysisVO1);
        jobLogAnalysisDTO = null;
        JobLogAnalysisVO jobLogAnalysisVO2 = jobLogWebConverterImpl.dto2vo(jobLogAnalysisDTO);
        assertNull(jobLogAnalysisVO2);
    }

    @Test
    public void dto2vo4JobLogDTOTest() {
        JobLogDTO jobLogDTO = new JobLogDTO();
        jobLogDTO.setExecutTime(LocalDateTime.now());
        JobLogVO jobLogVO = jobLogWebConverterImpl.dto2vo(jobLogDTO);
        assertNotNull(jobLogVO);
        jobLogDTO = null;
        JobLogVO jobLogVO1 = jobLogWebConverterImpl.dto2vo(jobLogDTO);
        assertNull(jobLogVO1);
    }

    @Test
    public void dto2vo4JobLogDTOListTest() {
        List<JobLogDTO> jobLogDTOS = new ArrayList<>();
        jobLogDTOS.add(new JobLogDTO());
        List<JobLogVO> list = jobLogWebConverterImpl.dto2vo(jobLogDTOS);
        assertNotNull(list);
        jobLogDTOS = null;
        List<JobLogVO> list1 = jobLogWebConverterImpl.dto2vo(jobLogDTOS);
        assertNull(list1);
    }

    @Test
    public void dto2vo4FlowMeterCatcherDTOTest() {
        FlowMeterCatcherDTO flowMeterCatcherDTO = new FlowMeterCatcherDTO();
        flowMeterCatcherDTO.setMoment(LocalDateTime.now());
        FlowMeterCatcherVO flowMeterCatcherVO = jobLogWebConverterImpl.dto2vo(flowMeterCatcherDTO);
        assertNotNull(flowMeterCatcherVO);
        flowMeterCatcherDTO = null;
        FlowMeterCatcherVO flowMeterCatcherVO1 = jobLogWebConverterImpl.dto2vo(flowMeterCatcherDTO);
        assertNull(flowMeterCatcherVO1);
    }

    @Test
    public void dto2vo4StatCatcherDTOTest() {
        StatCatcherDTO statCatcherDTO = new StatCatcherDTO();
        statCatcherDTO.setMoment(LocalDateTime.now());
        StatCatcherVO statCatcherVO = jobLogWebConverterImpl.dto2vo(statCatcherDTO);
        assertNotNull(statCatcherVO);
        statCatcherDTO = null;
        StatCatcherVO statCatcherVO1 = jobLogWebConverterImpl.dto2vo(statCatcherDTO);
        assertNull(statCatcherVO1);
    }

    @Test
    public void dto2vo4LogCatcherDTOTest() {
        LogCatcherDTO logCatcherDTO = new LogCatcherDTO();
        logCatcherDTO.setMoment(LocalDateTime.now());
        LogCatcherVO logCatcherVO = jobLogWebConverterImpl.dto2vo(logCatcherDTO);
        assertNotNull(logCatcherVO);
        logCatcherDTO = null;
        LogCatcherVO logCatcherVO1 = jobLogWebConverterImpl.dto2vo(logCatcherDTO);
        assertNull(logCatcherVO1);
    }


}
