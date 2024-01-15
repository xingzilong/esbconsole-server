package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.ComponentTimeData;
import org.talend.esbconsole.server.domain.api.model.JobLogAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.JobLogDTO;
import org.talend.esbconsole.server.domain.api.model.StatCatcherDTO;
import org.talend.esbconsole.server.domain.api.param.JobLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.JobLogPageQuery;
import org.talend.esbconsole.server.domain.api.service.JobLogService;
import org.talend.esbconsole.server.domain.core.converter.FlowMeterCatcherConverter;
import org.talend.esbconsole.server.domain.core.converter.LogCatcherConverter;
import org.talend.esbconsole.server.domain.core.converter.StatCatcherConverter;
import org.talend.esbconsole.server.domain.core.converter.query.JobLogQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.FlowMeterCatcherDO;
import org.talend.esbconsole.server.domain.repository.entity.LogCatcherDO;
import org.talend.esbconsole.server.domain.repository.entity.StatCatcherDO;
import org.talend.esbconsole.server.domain.repository.mapper.FlowMeterCatcherDAO;
import org.talend.esbconsole.server.domain.repository.mapper.LogCatcherDAO;
import org.talend.esbconsole.server.domain.repository.mapper.StatCatcherDAO;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 任务运行日志日志功能服务接口 {@link JobLogService} 的实现类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Service
public class JobLogServiceImpl implements JobLogService {

    @Autowired
    private StatCatcherDAO statCatcherDAO;

    @Autowired
    private LogCatcherDAO logCatcherDAO;

    @Autowired
    private FlowMeterCatcherDAO flowMeterCatcherDAO;

    @Autowired
    private JobLogQueryConverter jobLogQueryConverter;

    @Autowired
    private StatCatcherConverter statCatcherConverter;

    @Autowired
    private LogCatcherConverter logCatcherConverter;

    @Autowired
    private FlowMeterCatcherConverter flowMeterCatcherConverter;

    @Override
    public PageResult<JobLogDTO> getJobLogs(JobLogPageQueryParam jobLogPageQueryParam) {
        JobLogPageQuery jobLogPageQuery = jobLogQueryConverter.param2query(jobLogPageQueryParam);
        PageHelper.startPage(jobLogPageQueryParam.getPageNum(), jobLogPageQueryParam.getPageSize());
        List<String> uuIdList = statCatcherDAO.listUUIdByConditions(jobLogPageQuery);
        if (uuIdList == null || uuIdList.isEmpty()) {
            return PageResult.of(null, 0L);
        }
        // 获取数据总数
        PageInfo<String> uuIdListPageInfo = new PageInfo<String>(uuIdList);
        // 根据miFlowIdList查询相应的请求和响应的日志结果集
        List<StatCatcherDO> statCatcherDOList = statCatcherDAO.listStatCatchersByUUIdList(uuIdList);
        // 将查询的结果集进行聚合转换，即将同属于一次的请求和响应日志聚合为一条记录
        List<JobLogDTO> jobLogDTOList = convertToJobLogDTOList(statCatcherDOList, jobLogPageQuery);
        return PageResult.of(jobLogDTOList, uuIdListPageInfo.getTotal());
    }

    @Override
    public JobLogAnalysisDTO getJobLogAnalysis(String uuid) {
        JobLogAnalysisDTO jobLogAnalysisDTO = new JobLogAnalysisDTO();
        // 一、获取stat数据
        List<StatCatcherDO> statCatcherList = statCatcherDAO.listStatCatcherByUUId(uuid);
        jobLogAnalysisDTO.setStatCatcherRecord(statCatcherConverter.do2dto(statCatcherList));
        convert(statCatcherList, jobLogAnalysisDTO);

        // 二、获取log数据
        List<LogCatcherDO> logCatcherList = logCatcherDAO.listLogCatcherByUUId(uuid);
        jobLogAnalysisDTO.setLogCatcherRecord(logCatcherConverter.do2dto(logCatcherList));

        // 三、获取flowmeter数据
        Long dataVolume = flowMeterCatcherDAO.getCountSUMByUUId(uuid);
        jobLogAnalysisDTO.setDataVolume(dataVolume);

        List<FlowMeterCatcherDO> flowMeterCatcherList = flowMeterCatcherDAO.listFlowMeterCatcherByUUId(uuid);
        jobLogAnalysisDTO.setFlowMeterCatcherRecord(flowMeterCatcherConverter.do2dto(flowMeterCatcherList));

        return jobLogAnalysisDTO;
    }

    /**
     * 将statCatcherList转换为前台南丁格尔玫瑰图的源数据
     *
     * @param statCatcherList
     * @param jobLogAnalysisDTO
     */
    private void convert(List<StatCatcherDO> statCatcherList, JobLogAnalysisDTO jobLogAnalysisDTO) {
        // 按origin对statCatcherList对象进行分组，会打乱顺序
        Map<String, List<StatCatcherDO>> groupedLogs = statCatcherList.stream()
                .collect(Collectors.groupingBy(statCatcher -> {
                    String origin = statCatcher.getOrigin();
                    return origin != null ? origin : "-null-";
                }));

        // 将每组ServiceLog对象转换为ServiceLogDTO
        List<ComponentTimeData> componentTimeDataList = new ArrayList<>();

        boolean hasFailure = false;
        String job = null;
        for (List<StatCatcherDO> statCatcheLog : groupedLogs.values()) {
            ComponentTimeData componentTimeData = new ComponentTimeData();
            for (StatCatcherDO statCatcher : statCatcheLog) {
                // 只取结束的日志
                if (statCatcher.getDuration() != null) {
                    componentTimeData.setName(statCatcher.getOrigin() == null ? "-" : statCatcher.getOrigin());
                    componentTimeData.setValue(statCatcher.getDuration());
                    job = setJob(statCatcher, job);
                    hasFailure = setHasFailure(statCatcher);
                    componentTimeDataList.add(componentTimeData);
                }
            }
        }
        jobLogAnalysisDTO.setJob(job);
        jobLogAnalysisDTO.setStatus(hasFailure ? Constants.FAILURE : Constants.SUCCESS);
        jobLogAnalysisDTO.setComponentTimeDataList(componentTimeDataList);
    }

    /**
     * 设置job
     *
     * @param statCatcher
     * @param job
     * @return
     */
    private String setJob(StatCatcherDO statCatcher, String job) {
        return job == null && isSamePid(statCatcher) ? statCatcher.getJob() : job;
    }

    /**
     * 设置是否为失败状态
     *
     * @param statCatcher
     * @return
     */
    private boolean setHasFailure(StatCatcherDO statCatcher) {
        return Constants.FAILURE.equals(statCatcher.getMessage());
    }


    /**
     * 将 statCatcherDOList 转换为 jobLogDTOList
     *
     * @param statCatcherDOList 原始数据
     * @param jobLogPageQuery   查询条件
     * @return
     */
    public List<JobLogDTO> convertToJobLogDTOList(List<StatCatcherDO> statCatcherDOList, JobLogPageQuery jobLogPageQuery) {
        Map<String, List<StatCatcherDTO>> groupedData = new HashMap<>();

        // 按照uuid对statCatcherDOList，分组
        for (StatCatcherDO statCatcherDO : statCatcherDOList) {
            groupedData.computeIfAbsent(statCatcherDO.getUuid(), k -> new ArrayList<>()).add(statCatcherConverter.do2dto(statCatcherDO));
        }

        List<JobLogDTO> jobLogDTOList = new ArrayList<>();
        // 将分组数据转换为JobLogDTO
        for (Map.Entry<String, List<StatCatcherDTO>> entry : groupedData.entrySet()) {
            List<StatCatcherDTO> group = entry.getValue();
            JobLogDTO jobLogDTO = new JobLogDTO();
            jobLogDTO.setUuid(entry.getKey());

            long maxDuration = 0;
            boolean hasFailure = false;
            LocalDateTime minMoment = null;
            String job = null;

            for (StatCatcherDTO statCatcherDTO : group) {
                maxDuration = Math.max(maxDuration, statCatcherDTO.getDuration() == null ? 0 : statCatcherDTO.getDuration());
                hasFailure = setHasFailure(statCatcherDTO);
                minMoment = setMinMoment(statCatcherDTO, minMoment);
                job = setJob(statCatcherDTO, job);
            }

            jobLogDTO.setTime(maxDuration);
            jobLogDTO.setStatus(hasFailure ? Constants.FAILURE : Constants.SUCCESS);
            jobLogDTO.setExecutTime(minMoment);
            jobLogDTO.setJob(job);

            jobLogDTOList.add(jobLogDTO);
        }

        // 重新排序，在分组时会将此页内的顺序打乱，所以要重新按时间进行排序
        // 页内升序
        if (Constants.ASC.equals(jobLogPageQuery.getExecutTimeSort())) {
            Collections.sort(jobLogDTOList, Comparator.comparing(JobLogDTO::getExecutTime, (s1, s2) -> s1.compareTo(s2)));
        }
        // 页内降序
        if (Constants.DESC.equals(jobLogPageQuery.getExecutTimeSort())) {
            Collections.sort(jobLogDTOList, Comparator.comparing(JobLogDTO::getExecutTime, (s1, s2) -> s2.compareTo(s1)));
        }
        return jobLogDTOList;
    }

    /**
     * 设置是否为失败状态
     *
     * @param statCatcherDTO
     * @return
     */
    private boolean setHasFailure(StatCatcherDTO statCatcherDTO) {
        return Constants.FAILURE.equals(statCatcherDTO.getMessage());
    }

    /**
     * 设置最小执行时间
     *
     * @param statCatcherDTO
     * @param minMoment
     * @return
     */
    private LocalDateTime setMinMoment(StatCatcherDTO statCatcherDTO, LocalDateTime minMoment) {
        return minMoment == null || statCatcherDTO.getMoment().isBefore(minMoment) ? statCatcherDTO.getMoment() : minMoment;
    }

    /**
     * 设置job
     *
     * @param statCatcherDTO
     * @param job
     * @return
     */
    private String setJob(StatCatcherDTO statCatcherDTO, String job) {
        return job == null && isSamePid(statCatcherDTO) ? statCatcherDTO.getJob() : job;
    }

    /**
     * 比较当前记录 pid fatherPid rootPid 三个值是否相等，
     * 如果相等则使用此条记录的job赋值为当前分组JobLogDTO的job
     *
     * @param statCatcherDTO 一条基本记录信息
     * @return
     */
    private boolean isSamePid(StatCatcherDTO statCatcherDTO) {
        return statCatcherDTO.getPid().equals(statCatcherDTO.getFatherPid()) && statCatcherDTO.getFatherPid().equals(statCatcherDTO.getRootPid());
    }

    /**
     * 比较当前记录 pid fatherPid rootPid 三个值是否相等，
     * 如果相等则使用此条记录的job赋值为当前分组JobLogDTO的job
     *
     * @param statCatcherDO 一条基本记录信息
     * @return
     */
    private boolean isSamePid(StatCatcherDO statCatcherDO) {
        return statCatcherDO.getPid().equals(statCatcherDO.getFatherPid()) && statCatcherDO.getFatherPid().equals(statCatcherDO.getRootPid());
    }
}
