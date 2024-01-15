package org.talend.esbconsole.server.web.api.controller.servicelog;

import org.talend.esbconsole.server.domain.api.model.ServiceLogDTO;
import org.talend.esbconsole.server.domain.api.model.message.HttpMessage;
import org.talend.esbconsole.server.domain.api.param.ServiceLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.service.ServiceLogService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.servicelog.converter.ServiceLogWebConverter;
import org.talend.esbconsole.server.web.api.controller.servicelog.request.ServiceLogPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.servicelog.vo.ServiceLogVO;
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
 * 监控管理-服务日志（调用记录）相关功能控制器
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Validated
@Tag(name = "4-404、监控管理-服务日志（调用记录）", description = "服务日志的接口")
@RestController
@RequestMapping("/api/monitor/serviceLog")
public class ServiceLogController {

    @Autowired
    private ServiceLogService serviceLogService;

    @Autowired
    private ServiceLogWebConverter serviceLogWebConverter;

    @Operation(summary = "分页获取服务的日志信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<ServiceDO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个服务的详细信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('monitor:serviceLog:getServiceLogs')")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<ServiceLogVO>> getServiceLogs(@RequestBody @Validated ServiceLogPageQueryRequest request) {
        ServiceLogPageQueryParam serviceLogPageQueryParam = serviceLogWebConverter.req2param(request);
        PageResult<ServiceLogDTO> serviceLogPageInfo = serviceLogService.getServiceLogs(serviceLogPageQueryParam);
        List<ServiceLogVO> serviceLogVOS = serviceLogWebConverter.dto2vo(serviceLogPageInfo.getList());
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, PageResult.of(serviceLogVOS, serviceLogPageInfo.getTotal()));
    }

    @Operation(summary = "获取请求报文", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个HttpMessage对象,该对象描述了请求报文的详细信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('monitor:serviceLog:getRequestMessage')")
    @RequestMapping(path = "/getRequestMessage", method = RequestMethod.GET)
    public ResponseResult<HttpMessage> getRequestMessage(@RequestParam(name = "flowId") @Size(max = 45) String request) {
        HttpMessage requestMessage = serviceLogService.getRequestMessage(request);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, requestMessage);
    }

    @Operation(summary = "获取响应报文", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个HttpMessage对象,该对象描述了响应报文的详细信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('monitor:serviceLog:getResponseMessage')")
    @RequestMapping(path = "/getResponseMessage", method = RequestMethod.GET)
    public ResponseResult<HttpMessage> getResponseMessage(@RequestParam(name = "flowId") @Size(max = 45) String request) {
        HttpMessage responseMessage = serviceLogService.getResponseMessage(request);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, responseMessage);
    }
}
