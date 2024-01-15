package org.talend.esbconsole.server.web.api.controller.service;

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
import org.talend.esbconsole.server.domain.api.service.ServiceService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.service.converter.ServiceWebConverter;
import org.talend.esbconsole.server.web.api.controller.service.request.*;
import org.talend.esbconsole.server.web.api.controller.service.request.APICallAnalysisRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.APIRunAnalysisRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceInstallRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceModifyRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServicePageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceTimedTaskRequest;
import org.talend.esbconsole.server.web.api.controller.service.vo.ServiceVO;
import org.talend.esbconsole.server.web.api.controller.service.vo.TaskTimeConsumptionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务管理-服务（services）相关功能控制器
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Validated
@Slf4j
@Tag(name = "3-1、服务管理-服务", description = "服务（servcie）相关操作的接口")
@RestController
@RequestMapping("/api/service/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceWebConverter serviceWebConverter;

    @Operation(summary = "获取所有的服务的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个对象数组，每个对象描述了一个服务的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:listAll')")
    @RequestMapping(path = "/listAll", method = RequestMethod.GET)
    public ResponseResult<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> listAllServiceDTO = serviceService.getAllServices();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, listAllServiceDTO);
    }

    @Operation(summary = "根据ID获取某个服务的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个对象数组，对象描述了一个服务的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:getService')")
    @RequestMapping(path = "/getServiceById", method = RequestMethod.GET)
    public ResponseResult<ServiceDTO> getServiceById(@RequestParam(name = "id") @NotNull @Size(max = 36) String id) {
        ServiceDTO ServiceDTO = serviceService.getServiceById(id);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, ServiceDTO);
    }

    @Operation(summary = "分页获取服务的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<ServiceDO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个服务的详细信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:list')")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<ServiceVO>> getServices(@RequestBody @Validated ServicePageQueryRequest request) {
        ServicePageQueryParam servicePageQueryParam = serviceWebConverter.req2param(request);
        PageResult<ServiceDTO> servicesPageInfo = serviceService.getServices(servicePageQueryParam);
        List<ServiceDTO> serviceDTOS = servicesPageInfo.getList();
        List<ServiceVO> serviceVOS = serviceWebConverter.dto2vo4service(serviceDTOS);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, PageResult.of(serviceVOS, servicesPageInfo.getTotal()));
    }

    @Operation(summary = "获取某个服务下的Bundle的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个对象数组，每个对象描述了一个Bundle的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    // 我认为此处的应为 RequestMethod.GET 更加合理，但是浏览器发送Method为GET的请求会自动丢弃body。
    // HTTP协议的规范中对GET Method携带body并没有明确的语义说明。
    // 此处目前暂时使用 RequestMethod.POST。 xingzilong
    @PreAuthorize("@AE.hasAuthority('service:service:listBundles')")
    @RequestMapping(path = "/listBundles", method = RequestMethod.POST)
    public ResponseResult<List<BundleInfo>> getBundlesForService(@RequestBody @Validated ServiceStatusActionRequest request) {
        ServiceStatusActionParam serviceStatusActionParam = serviceWebConverter.req2param(request);
        ArrayList<BundleInfo> listBundles = serviceService.getBundlesForService(serviceStatusActionParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, listBundles);
    }

    /**
     * 部署服务api
     *
     * @param file    服务的代码文件
     * @param request 参数信息
     * @return
     */
    @Operation(summary = "部署服务", security = {@SecurityRequirement(name = "Authorization")})
    // @Parameters({@Parameter(name = "file", description = "将要部署的服务的文件，只能时XXX.jar或XXX.kar."),
    // @Parameter(name = "service", description = "服务的描述信息")})
    @ApiResponses({@ApiResponse(description = "服务部署成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:install')")
    @RequestMapping(path = "/install", method = RequestMethod.POST)
    public ResponseResult<Object> installService(@RequestParam("file") @NotNull MultipartFile file,
                                            @RequestPart(name = "service") @Validated ServiceInstallRequest request) {
        ServiceInstallParam serviceInstallParam = serviceWebConverter.req2param(request);
        serviceService.installService(file, serviceInstallParam);
        return new ResponseResult<>(200, "服务部署成功");
    }

    @Operation(summary = "修改服务信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "服务修改成功", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:modify')")
    @RequestMapping(path = "/modify", method = RequestMethod.POST)
    public ResponseResult<Object> modifyService(@RequestBody @Validated ServiceModifyRequest request) {
        ServiceModifyParam serviceModifyParam = serviceWebConverter.req2param(request);
        serviceService.modifyService(serviceModifyParam);
        return new ResponseResult<>(200, "服务修改成功");
    }

    @Operation(summary = "卸载服务", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "服务卸载成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:uninstall')")
    @RequestMapping(path = "/uninstall", method = RequestMethod.DELETE)
    public ResponseResult<Object> uninstallServices(@RequestBody @Validated List<ServiceStatusActionRequest> request) {
        List<ServiceStatusActionParam> serviceStatusActionParams = serviceWebConverter.req2param(request);
        for (ServiceStatusActionParam serviceStatusActionParam : serviceStatusActionParams) {
            serviceService.unInstallService(serviceStatusActionParam);
        }
        return new ResponseResult<>(200, "服务卸载成功");
    }

    @Operation(summary = "启动服务", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "服务启动成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:start')")
    @RequestMapping(path = "/start", method = RequestMethod.PUT)
    public ResponseResult<Object> startServices(@RequestBody @Validated List<ServiceStatusActionRequest> request) {
        List<ServiceStatusActionParam> serviceStatusActionParams = serviceWebConverter.req2param(request);
        for (ServiceStatusActionParam serviceStatusActionParam : serviceStatusActionParams) {
            serviceService.startService(serviceStatusActionParam);
        }
        return new ResponseResult<>(200, "服务启动成功");
    }

    @Operation(summary = "停止服务", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "服务停止成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:stop')")
    @RequestMapping(path = "/stop", method = RequestMethod.PUT)
    public ResponseResult<Object> stopServices(@RequestBody @Validated List<ServiceStatusActionRequest> request) {
        List<ServiceStatusActionParam> serviceStatusActionParams = serviceWebConverter.req2param(request);
        for (ServiceStatusActionParam serviceStatusActionParam : serviceStatusActionParams) {
            serviceService.stopService(serviceStatusActionParam);
        }
        return new ResponseResult<>(200, "服务停止成功");
    }

    @Operation(summary = "获取api调用top5数据", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:apiTop5')")
    @RequestMapping(path = "/callAnalysis/getCallTotalTop5Chart", method = RequestMethod.POST)
    public ResponseResult<List<APICallTop5DataDTO>> getCallTotalTop5Chart(@RequestBody @Validated APICallAnalysisRequest request) {
        APICallAnalysisParam apiCallAnalysisParam = serviceWebConverter.req2param(request);
        List<APICallTop5DataDTO> callTotalTop5Chart = serviceService.getCallTotalTop5Chart(apiCallAnalysisParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, callTotalTop5Chart);
    }

    @Operation(summary = "获取api失败调用top5数据", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:getFTTop5')")
    @RequestMapping(path = "/callAnalysis/getFailureTotalTop5Chart", method = RequestMethod.POST)
    public ResponseResult<List<APICallTop5DataDTO>> getFailureTotalTop5Chart(@RequestBody @Validated APICallAnalysisRequest request) {
        APICallAnalysisParam apiCallAnalysisParam = serviceWebConverter.req2param(request);
        List<APICallTop5DataDTO> callTotalTop5Chart = serviceService.getFailureTotalTop5Chart(apiCallAnalysisParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, callTotalTop5Chart);
    }

    @Operation(summary = "获取api调用分析表格", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:getApiCA')")
    @RequestMapping(path = "/callAnalysis/getAPICallAnalysis", method = RequestMethod.POST)
    public ResponseResult<PageResult<APICallAnalysisTableDTO>> getAPICallAnalysis(@RequestBody @Validated APICallAnalysisRequest request) {
        APICallAnalysisParam apiCallAnalysisParam = serviceWebConverter.req2param(request);
        PageResult<APICallAnalysisTableDTO> apiCallAnalysisTable = serviceService.getAPICallAnalysis(apiCallAnalysisParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, apiCallAnalysisTable);
    }

    @Operation(summary = "获取api接口响应次数分析", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:getApiTA')")
    @RequestMapping(path = "/runAnalysis/getAPICallTotaleAnalysis", method = RequestMethod.POST)
    public ResponseResult<Map<String, List<APICallTotalAnalysisDTO>>> getAPICallTotaleAnalysis(@RequestBody @Validated APIRunAnalysisRequest request) {
        APIRunAnalysisParam apiRunAnalysisParam = serviceWebConverter.req2param(request);
        Map<String, List<APICallTotalAnalysisDTO>> apiCallAnalysisTable = serviceService.getAPICallTotaleAnalysis(apiRunAnalysisParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, apiCallAnalysisTable);
    }

    @Operation(summary = "获取api接口响应耗时分析", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:getApiRTA')")
    @RequestMapping(path = "/runAnalysis/getAPIResponseTimeAnalysis", method = RequestMethod.POST)
    public ResponseResult<List<APICallTotalAnalysisDTO>> getAPIResponseTimeAnalysis(@RequestBody @Validated APIRunAnalysisRequest request) {
        APIRunAnalysisParam apiRunAnalysisParam = serviceWebConverter.req2param(request);
        List<APICallTotalAnalysisDTO> apiCallAnalysisTable = serviceService.getAPIResponseTimeAnalysis(apiRunAnalysisParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, apiCallAnalysisTable);
    }

    @Operation(summary = "获取api接口报文大小分析", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:getApiMSA')")
    @RequestMapping(path = "/runAnalysis/getAPIMessageSizeAnalysis", method = RequestMethod.POST)
    public ResponseResult<List<APICallTotalAnalysisDTO>> getAPIMessageSizeAnalysis(@RequestBody @Validated APIRunAnalysisRequest request) {
        APIRunAnalysisParam apiRunAnalysisParam = serviceWebConverter.req2param(request);
        List<APICallTotalAnalysisDTO> apiCallAnalysisTable = serviceService.getAPIMessageSizeAnalysis(apiRunAnalysisParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, apiCallAnalysisTable);
    }

    @Operation(summary = "获取定时任务分析-任务执行成功失败", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:getTaskEC')")
    @RequestMapping(path = "/timedAnalysis/getTaskExectutionChart", method = RequestMethod.POST)
    public ResponseResult<List<TaskExecutionDTO>> getTaskConsumption(@RequestBody @Validated ServiceTimedTaskRequest timedTaskRequest) {
        ServiceTimedTaskParam serviceTimedTaskParam = serviceWebConverter.req2param(timedTaskRequest);
        List<TaskExecutionDTO> data = serviceService.getTaskExecution(serviceTimedTaskParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, data);
    }

    @Operation(summary = "获取定时任务分析-执行时间分析", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:getTaskTCC')")
    @RequestMapping(path = "/timedAnalysis/getTaskTimeConsumptionChart", method = RequestMethod.POST)
    public ResponseResult<List<TaskTimeConsumptionVO>> getTaskTimeConsumption(@RequestBody @Validated ServiceTimedTaskRequest timedTaskRequest) {
        ServiceTimedTaskParam serviceTimedTaskParam = serviceWebConverter.req2param(timedTaskRequest);
        List<TaskTimeConsumptionDTO> data = serviceService.getTaskConsumption(serviceTimedTaskParam);
        List<TaskTimeConsumptionVO> taskTimeConsumptionVOS = serviceWebConverter.dto2vo(data);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, taskTimeConsumptionVOS);
    }

    @Operation(summary = "获取定时任务分析-定时任务分析表格分页查询", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:service:getTaskTT')")
    @RequestMapping(path = "/timedAnalysis/getTable", method = RequestMethod.POST)
    public ResponseResult<PageResult<JobLogDTO>> getTaskTimeTable(@RequestBody @Validated ServiceTimedTaskRequest timedTaskRequest) {
        ServiceTimedTaskParam serviceTimedTaskParam = serviceWebConverter.req2param(timedTaskRequest);
        PageResult<JobLogDTO> timedTaskPageInfo = serviceService.getTimedTaskTable(serviceTimedTaskParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, timedTaskPageInfo);
    }
}
