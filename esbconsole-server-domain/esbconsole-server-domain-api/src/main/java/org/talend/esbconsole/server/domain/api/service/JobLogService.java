package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.JobLogAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.JobLogDTO;
import org.talend.esbconsole.server.domain.api.param.JobLogPageQueryParam;
import org.talend.esbconsole.server.tools.base.result.PageResult;

/**
 * 任务运行日志相关的功能服务接口
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public interface JobLogService {

    /**
     * 分页获取任务运行日志
     *
     * @param jobLogPageQueryParam
     * @return
     */
    PageResult<JobLogDTO> getJobLogs(JobLogPageQueryParam jobLogPageQueryParam);

    /**
     * 获取该次任务运行的分析数据
     *
     * @param uuid 该次任务运行的uuid
     * @return
     */
    JobLogAnalysisDTO getJobLogAnalysis(String uuid);
}
