package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.*;
import org.springframework.stereotype.Service;
import org.talend.esbconsole.server.domain.api.model.APICallSuccessFailureDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.ApplicationTotalDTO;
import org.talend.esbconsole.server.domain.api.model.TaskProgressReportDTO;
import org.talend.esbconsole.server.domain.api.model.TotalAnalysisDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * 首页 相关的功能服务接口
 *
 * @author xingzilong
 * @date 2021/08/22
 */
@Service
public interface HomeAnalysisService {
    /**
     * 查询首页顶部分析所需要的数据
     *
     * @return
     */
    TotalAnalysisDTO getContentTopDataList();

    /**
     * 查询首页应用分析图表所需要的数据
     *
     * @return
     */
    List<ApplicationTotalDTO> getApplitionAnalysisDataList();

    /**
     * 查询首页接口调用成功&失败所需数据
     *
     * @return
     */
    APICallSuccessFailureDTO getApiCallStatisticsData();

    /**
     * 查询近七天任务执行分析数据
     *
     * @return
     */
    List<TaskProgressReportDTO> getTaskProgressReportData(LocalDate startDate, LocalDate endDate);

    /**
     * 查询近七天API数据传输量
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<APICallTotalAnalysisDTO> getDataCallTotal4Date(LocalDate startDate, LocalDate endDate);

    /**
     * 查询七天API调用次数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<APICallTotalAnalysisDTO> getCallTotal4Date(LocalDate startDate, LocalDate endDate);
}
