package org.talend.esbconsole.server.web.api.controller.joblog;

import org.talend.esbconsole.server.domain.api.model.JobLogAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.JobLogDTO;
import org.talend.esbconsole.server.domain.api.param.JobLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.service.JobLogService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.joblog.converter.JobLogWebConverter;
import org.talend.esbconsole.server.web.api.controller.joblog.request.JobLogPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.JobLogAnalysisVO;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.JobLogVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 监控管理-任务日志相关功能控制器
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Validated
@Tag(name = "4-405、监控管理-任务日志", description = "任务日志的接口")
@RestController
@RequestMapping("/api/monitor/jobLog")
public class JobLogController {

    @Autowired
    private JobLogService jobLogService;

    @Autowired
    private JobLogWebConverter jobLogWebConverter;

    @Operation(summary = "分页获取服务的日志信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<JobLogDTO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个服务的详细信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('monitor:jobLog:getJobLogs')")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<JobLogVO>> getJobLogs(@RequestBody @Validated JobLogPageQueryRequest request) {
        JobLogPageQueryParam jobLogPageQueryParam = jobLogWebConverter.req2param(request);
        PageResult<JobLogDTO> jobLogPageInfo = jobLogService.getJobLogs(jobLogPageQueryParam);
        List<JobLogVO> jobLogVOS = jobLogWebConverter.dto2vo(jobLogPageInfo.getList());
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, PageResult.of(jobLogVOS, jobLogPageInfo.getTotal()));
    }

    @Operation(summary = "获取该次任务运行的分析数据", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个JobLogAnalysisResponse对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个服务的详细信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('monitor:jobLog:getJobLogAnalysis')")
    @RequestMapping(path = "/jobLogAnalysis", method = RequestMethod.GET)
    public ResponseResult<JobLogAnalysisVO> getJobLogAnalysis(@RequestParam(name = "uuid") @Size(max = 36) String uuid) {
        JobLogAnalysisDTO jobLogAnalysisDTO = jobLogService.getJobLogAnalysis(uuid);
        JobLogAnalysisVO jobLogAnalysisVO = jobLogWebConverter.dto2vo(jobLogAnalysisDTO);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, jobLogAnalysisVO);
    }

}
