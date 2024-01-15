package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.*;
import org.talend.esbconsole.server.domain.api.param.*;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.springframework.web.multipart.MultipartFile;
import org.talend.esbconsole.server.domain.api.model.APICallAnalysisTableDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTop5DataDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.model.JobLogDTO;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.model.TaskExecutionDTO;
import org.talend.esbconsole.server.domain.api.model.TaskTimeConsumptionDTO;
import org.talend.esbconsole.server.domain.api.param.APICallAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.APIRunAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.ServiceInstallParam;
import org.talend.esbconsole.server.domain.api.param.ServiceModifyParam;
import org.talend.esbconsole.server.domain.api.param.ServicePageQueryParam;
import org.talend.esbconsole.server.domain.api.param.ServiceStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.ServiceTimedTaskParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service相关功能服务接口
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public interface ServiceService {
    /**
     * 获取所有service的详细信息
     *
     * @return
     */
    List<ServiceDTO> getAllServices();

    /**
     * 根据ID获取service的详细信息
     *
     * @param id
     * @return
     */
    ServiceDTO getServiceById(String id);

    /**
     * 分页获取用户
     *
     * @param servicePageQueryParam
     * @return
     */
    PageResult<ServiceDTO> getServices(ServicePageQueryParam servicePageQueryParam);

    /**
     * 卸载service
     *
     * @param serviceStatusActionParam service详细信息
     * @return
     */
    void unInstallService(ServiceStatusActionParam serviceStatusActionParam);

    /**
     * 启动service
     *
     * @param serviceStatusActionParam service详细信息
     * @return
     */
    void startService(ServiceStatusActionParam serviceStatusActionParam);

    /**
     * 停止service
     *
     * @param serviceStatusActionParam service详细信息
     * @return
     */
    void stopService(ServiceStatusActionParam serviceStatusActionParam);

    /**
     * 安装service
     *
     * @param file                service文件
     * @param serviceInstallParam service详细信息
     */
    void installService(MultipartFile file, ServiceInstallParam serviceInstallParam);

    /**
     * 获取某个服务下的Bundle的详细信息
     *
     * @param serviceStatusActionParam serviceParam
     */
    ArrayList<BundleInfo> getBundlesForService(ServiceStatusActionParam serviceStatusActionParam);

    /**
     * 修改服务的详细信息
     *
     * @param serviceModifyParam
     */
    void modifyService(ServiceModifyParam serviceModifyParam);

    /**
     * 获取API调用总数的Top5
     *
     * @param apiCallAnalysisParam
     * @return
     */
    List<APICallTop5DataDTO> getCallTotalTop5Chart(APICallAnalysisParam apiCallAnalysisParam);

    /**
     * 获取API调用失败总数的Top5
     *
     * @param apiCallAnalysisParam
     * @return
     */
    List<APICallTop5DataDTO> getFailureTotalTop5Chart(APICallAnalysisParam apiCallAnalysisParam);

    /**
     * 获取服务api分析表格个
     *
     * @param apiCallAnalysisParam
     * @return
     */
    PageResult<APICallAnalysisTableDTO> getAPICallAnalysis(APICallAnalysisParam apiCallAnalysisParam);

    /**
     * 获取api接口响应次数趋势数据
     *
     * @param apiRunAnalysisParam
     * @return
     */
    Map<String, List<APICallTotalAnalysisDTO>> getAPICallTotaleAnalysis(APIRunAnalysisParam apiRunAnalysisParam);

    /**
     * 获取api接口响应耗时趋势数据
     *
     * @param apiRunAnalysisParam
     * @return
     */
    List<APICallTotalAnalysisDTO> getAPIResponseTimeAnalysis(APIRunAnalysisParam apiRunAnalysisParam);

    /**
     * 获取api接口报文大小趋势数据
     *
     * @param apiRunAnalysisParam
     * @return
     */
    List<APICallTotalAnalysisDTO> getAPIMessageSizeAnalysis(APIRunAnalysisParam apiRunAnalysisParam);

    /**
     * 查询定时任务执行情况(成功/失败次数)
     *
     * @return
     */
    List<TaskExecutionDTO> getTaskExecution(ServiceTimedTaskParam serviceTimedTaskParam);

    /**
     * 查询定时任务执行时间分析数据
     *
     * @param serviceTimedTaskParam
     * @return
     */
    List<TaskTimeConsumptionDTO> getTaskConsumption(ServiceTimedTaskParam serviceTimedTaskParam);

    /**
     * 查询定时任务表格分析数据
     *
     * @param serviceTimedTaskParam
     * @return
     */
    PageResult<JobLogDTO> getTimedTaskTable(ServiceTimedTaskParam serviceTimedTaskParam);
}

