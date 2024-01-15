package org.talend.esbconsole.server.domain.core.impl;

import com.alibaba.fastjson2.JSONArray;
import org.talend.esbconsole.server.domain.api.model.JobLogAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.JobLogDTO;
import org.talend.esbconsole.server.domain.api.model.StatCatcherDTO;
import org.talend.esbconsole.server.domain.api.param.JobLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.JobLogPageQuery;
import org.talend.esbconsole.server.domain.core.converter.FlowMeterCatcherConverter;
import org.talend.esbconsole.server.domain.core.converter.LogCatcherConverter;
import org.talend.esbconsole.server.domain.core.converter.StatCatcherConverter;
import org.talend.esbconsole.server.domain.core.converter.query.JobLogQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.StatCatcherDO;
import org.talend.esbconsole.server.domain.repository.mapper.FlowMeterCatcherDAO;
import org.talend.esbconsole.server.domain.repository.mapper.LogCatcherDAO;
import org.talend.esbconsole.server.domain.repository.mapper.StatCatcherDAO;
import org.talend.esbconsole.server.util.ReadFileUtil;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@link JobLogServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JobLogServiceImplTest {

    @Mock
    private StatCatcherDAO statCatcherDAO;

    @Mock
    private LogCatcherDAO logCatcherDAO;

    @Mock
    private FlowMeterCatcherDAO flowMeterCatcherDAO;

    @Mock
    private JobLogQueryConverter jobLogQueryConverter;

    @Mock
    private StatCatcherConverter statCatcherConverter;

    @Mock
    private LogCatcherConverter logCatcherConverter;

    @Mock
    private FlowMeterCatcherConverter flowMeterCatcherConverter;

    @InjectMocks
    private JobLogServiceImpl jobLogService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getJobLogsTest() {
        JobLogPageQueryParam jobLogPageQueryParam = Mockito.mock(JobLogPageQueryParam.class);
        JobLogPageQuery jobLogPageQuery = new JobLogPageQuery();
        jobLogPageQuery.setExecutTimeSort("asc");
        List<String> uuIdList = Arrays.asList("123");
        String rs = ReadFileUtil.readJson("mockdata/joblog4group.json");
        List<StatCatcherDO> statCatcherDOList = JSONArray.parseArray(rs, StatCatcherDO.class);
        List<StatCatcherDTO> statCatcherDTOList = JSONArray.parseArray(rs, StatCatcherDTO.class);
        Mockito.doReturn(jobLogPageQuery).when(jobLogQueryConverter).param2query(jobLogPageQueryParam);
        Mockito.doReturn(uuIdList).when(statCatcherDAO).listUUIdByConditions(jobLogPageQuery);
        Mockito.doReturn(statCatcherDOList).when(statCatcherDAO).listStatCatchersByUUIdList(uuIdList);
        Mockito.doReturn(statCatcherDTOList.get(0))
                .doReturn(statCatcherDTOList.get(1))
                .doReturn(statCatcherDTOList.get(2))
                .doReturn(statCatcherDTOList.get(3))
                .doReturn(statCatcherDTOList.get(4))
                .when(statCatcherConverter).do2dto(Mockito.any(StatCatcherDO.class));

        PageResult<JobLogDTO> jobLogs = jobLogService.getJobLogs(jobLogPageQueryParam);

        Mockito.verify(jobLogQueryConverter).param2query(jobLogPageQueryParam);
        Mockito.verify(statCatcherDAO).listUUIdByConditions(jobLogPageQuery);
        Mockito.verify(statCatcherDAO).listStatCatchersByUUIdList(uuIdList);

        assertNotNull(jobLogs);

        // 排序覆盖
        jobLogPageQuery.setExecutTimeSort("desc");

        Mockito.doReturn(jobLogPageQuery).when(jobLogQueryConverter).param2query(jobLogPageQueryParam);
        Mockito.doReturn(uuIdList).when(statCatcherDAO).listUUIdByConditions(jobLogPageQuery);
        Mockito.doReturn(statCatcherDOList).when(statCatcherDAO).listStatCatchersByUUIdList(uuIdList);
        Mockito.doReturn(statCatcherDTOList.get(0))
                .doReturn(statCatcherDTOList.get(1))
                .doReturn(statCatcherDTOList.get(2))
                .doReturn(statCatcherDTOList.get(3))
                .doReturn(statCatcherDTOList.get(4))
                .when(statCatcherConverter).do2dto(Mockito.any(StatCatcherDO.class));

        PageResult<JobLogDTO> jobLogs1 = jobLogService.getJobLogs(jobLogPageQueryParam);


        assertNotNull(jobLogs1);

        // 返回0条数据
        Mockito.doReturn(jobLogPageQuery).when(jobLogQueryConverter).param2query(jobLogPageQueryParam);
        Mockito.doReturn(Arrays.asList()).when(statCatcherDAO).listUUIdByConditions(jobLogPageQuery);
        PageResult<JobLogDTO> jobLogs2 = jobLogService.getJobLogs(jobLogPageQueryParam);

        assertNotNull(jobLogs2);

    }

    @Test
    public void getJobLogAnalysisTest() {

        String rs = ReadFileUtil.readJson("mockdata/joblog4statcatcherlist.json");
        List<StatCatcherDO> statCatcherDOList = JSONArray.parseArray(rs, StatCatcherDO.class);
        Mockito.doReturn(statCatcherDOList).when(statCatcherDAO).listStatCatcherByUUId(Mockito.anyString());
        Mockito.doReturn(Arrays.asList()).when(statCatcherConverter).do2dto(statCatcherDOList);

        Mockito.doReturn(Arrays.asList()).when(logCatcherDAO).listLogCatcherByUUId(Mockito.anyString());
        Mockito.doReturn(Arrays.asList()).when(logCatcherConverter).do2dto(Arrays.asList());

        Mockito.doReturn(Arrays.asList()).when(flowMeterCatcherDAO).listFlowMeterCatcherByUUId(Mockito.anyString());
        Mockito.doReturn(Arrays.asList()).when(flowMeterCatcherConverter).do2dto(Arrays.asList());

        JobLogAnalysisDTO jobLogAnalysisDTO = jobLogService.getJobLogAnalysis("a");

        Mockito.verify(statCatcherDAO).listStatCatcherByUUId(Mockito.anyString());
        Mockito.verify(statCatcherConverter).do2dto(statCatcherDOList);

        Mockito.verify(logCatcherDAO).listLogCatcherByUUId(Mockito.anyString());
        Mockito.verify(logCatcherConverter).do2dto(Arrays.asList());

        Mockito.verify(flowMeterCatcherDAO).listFlowMeterCatcherByUUId(Mockito.anyString());
        Mockito.verify(flowMeterCatcherConverter).do2dto(Arrays.asList());

        assertNotNull(jobLogAnalysisDTO);
    }

}
