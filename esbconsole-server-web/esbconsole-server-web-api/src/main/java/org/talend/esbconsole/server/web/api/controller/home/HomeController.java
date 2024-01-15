package org.talend.esbconsole.server.web.api.controller.home;

import org.talend.esbconsole.server.domain.api.model.APICallSuccessFailureDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.ApplicationTotalDTO;
import org.talend.esbconsole.server.domain.api.model.TaskProgressReportDTO;
import org.talend.esbconsole.server.domain.api.model.TotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.service.HomeAnalysisService;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.home.converter.HomeWebConverter;
import org.talend.esbconsole.server.web.api.controller.home.vo.CallTotal4DateVO;
import org.talend.esbconsole.server.web.api.controller.home.vo.DataCallTotal4DateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author xingzilong
 * @program: esbconsole-server
 * @description: 主页控制层
 * @create: 2023-08-14 13:56
 **/
@Tag(name = "1-1、首页", description = "首页相关操作的接口")
@Slf4j
@RestController
@RequestMapping("api/home/analysis")
public class HomeController {

    @Autowired
    private HomeAnalysisService homeAnalysisService;

    @Autowired
    private HomeWebConverter homeWebConverter;

    @Operation(summary = "首页顶部数据栏数据获取", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功。", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('home:analysis:getContentTD')")
    @RequestMapping(path = "/total", method = RequestMethod.GET)
    public ResponseResult<TotalAnalysisDTO> getContentTopDataList() {
        TotalAnalysisDTO data = homeAnalysisService.getContentTopDataList();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, data);
    }

    @Operation(summary = "首页应用程序分析图表数据获取", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功。", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('home:analysis:getAAL')")
    @RequestMapping(path = "/application", method = RequestMethod.GET)
    public ResponseResult<List<ApplicationTotalDTO>> getApplitionAnalysisList() {
        List<ApplicationTotalDTO> arrayData = homeAnalysisService.getApplitionAnalysisDataList();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, arrayData);
    }

    @Operation(summary = "首页成功&失败图表数据", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功。", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('home:analysis:getApiCSL')")
    @RequestMapping(path = "/apiCallSuccessFail", method = RequestMethod.GET)
    public ResponseResult<APICallSuccessFailureDTO> getApiCallStatisticsList() {
        APICallSuccessFailureDTO apiCallStatisticsData = homeAnalysisService.getApiCallStatisticsData();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, apiCallStatisticsData);
    }

    @Operation(summary = "首页近七天任务执行情况图表数据获取", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功。", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('home:analysis:getTaskPRD')")
    @RequestMapping(path = "/taskProgressReport", method = RequestMethod.GET)
    public ResponseResult<List<TaskProgressReportDTO>> getTaskProgressReportData() {
        //获取当前时间
        LocalDate endDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
        LocalDate startDate = endDate.minus(7, ChronoUnit.DAYS);
        List<TaskProgressReportDTO> data = homeAnalysisService.getTaskProgressReportData(startDate, endDate);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, data);

    }

    @Operation(summary = "首页近七天API调数据量统计", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功。", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('home:analysis:getDataCT4D')")
    @RequestMapping(path = "/dataCallTotal", method = RequestMethod.GET)
    public ResponseResult<List<DataCallTotal4DateVO>> getDataCallTotal4Date() {
        //获取当前时间
        LocalDate endDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
        LocalDate startDate = endDate.minus(7, ChronoUnit.DAYS);
        List<APICallTotalAnalysisDTO> apiCallTotalAnalysisDTOS = homeAnalysisService.getDataCallTotal4Date(startDate, endDate);
        List<DataCallTotal4DateVO> dataCallTotal4DateVOS = homeWebConverter.dto2vo(apiCallTotalAnalysisDTOS);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, dataCallTotal4DateVOS);

    }

    @Operation(summary = "首页近七天API次数统计", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功。", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('home:analysis:getCT4D')")
    @RequestMapping(path = "/callTotal", method = RequestMethod.GET)
    public ResponseResult<List<CallTotal4DateVO>> getCallTotal4Date() {
        //获取当前时间
        LocalDate endDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
        LocalDate startDate = endDate.minus(7, ChronoUnit.DAYS);
        List<APICallTotalAnalysisDTO> apiCallTotalAnalysisDTOS = homeAnalysisService.getCallTotal4Date(startDate, endDate);
        List<CallTotal4DateVO> callTotal4DateVOS = homeWebConverter.dto2ct4dvo(apiCallTotalAnalysisDTOS);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, callTotal4DateVOS);

    }
}
