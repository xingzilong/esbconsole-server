package org.talend.esbconsole.server.web.api.controller.ac.flow;

import org.talend.esbconsole.server.domain.api.model.FlowControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FlowPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FlowStatusActionParam;
import org.talend.esbconsole.server.domain.api.service.FlowControlService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.ac.flow.converter.AC4FlowWebConverter;
import org.talend.esbconsole.server.web.api.controller.ac.flow.request.AC4FlowPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.flow.request.AC4FlowStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.flow.vo.FlowControlVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
 * 服务访问控制-流量控制相关功能控制器
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Validated
@Tag(name = "4-1-2、控制管理-访问控制-流量控制", description = "访问控制相关操作的接口")
@RestController
@RequestMapping("/api/control/ac/flow")
public class FlowController {

    @Autowired
    private FlowControlService flowControlService;

    @Autowired
    private AC4FlowWebConverter ac4FlowWebConverter;

    @Operation(summary = "分页流量获取控制规则的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<FlowControlVO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个流量控制规则信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:getFlowControls')")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<FlowControlVO>> getFlowControls(@RequestBody @Validated AC4FlowPageQueryRequest request) {
        AC4FlowPageQueryParam ac4FlowPageQueryParam = ac4FlowWebConverter.req2param(request);
        PageResult<FlowControlModel> flowControlsPageInfo = flowControlService.getFlowControls(ac4FlowPageQueryParam);
        List<FlowControlVO> flowControlVOS = ac4FlowWebConverter.dto2vo(flowControlsPageInfo.getList());
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, PageResult.of(flowControlVOS, flowControlsPageInfo.getTotal()));
    }

    @Operation(summary = "根据id获取一条流量规则信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "规则id")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<FlowControlVO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个流量控制规则信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:getFlowControlById')")
    @RequestMapping(path = "/getFlowControl", method = RequestMethod.GET)
    public ResponseResult<FlowControlVO> getFlowControlById(@RequestParam(name = "id") @Size(max = 36) String id) {
        FlowControlModel flowControl = flowControlService.getFlowControlById(id);
        FlowControlVO flowControlVO = ac4FlowWebConverter.dto2vo(flowControl);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, flowControlVO);
    }

    @Operation(summary = "获取未绑定规则的服务", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则添加成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:getServiceNBFlowC')")
    @RequestMapping(path = "/getService", method = RequestMethod.GET)
    public ResponseResult<List<ServiceDTO>> getServiceNoBindFlowControl() {
        List<ServiceDTO> serviceNoBindFlowControl = flowControlService.getServiceNoBindFlowControl();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, serviceNoBindFlowControl);
    }

    @Operation(summary = "添加流量规则信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则添加成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:addFlowControl')")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseResult<Object> addFlowControl(@RequestBody @Validated AC4FlowStatusActionRequest request) {
        AC4FlowStatusActionParam ac4FlowStatusActionParam = ac4FlowWebConverter.req2param(request);
        flowControlService.addFlowControl(ac4FlowStatusActionParam);
        return new ResponseResult<>(200, "规则添加成功");
    }

    @Operation(summary = "更改流量规则信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则信息更改成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:modifyFlowControl')")
    @RequestMapping(path = "/modify", method = RequestMethod.PUT)
    public ResponseResult<Object> modifyFlowControl(@RequestBody @Validated AC4FlowStatusActionRequest request) {
        AC4FlowStatusActionParam ac4FlowStatusActionParam = ac4FlowWebConverter.req2param(request);
        flowControlService.modifyFlowControls(ac4FlowStatusActionParam);
        return new ResponseResult<>(200, "规则更改成功");
    }

    @Operation(summary = "停用控制规则", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则已被停用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:disableFlowControl')")
    @RequestMapping(path = "/disable", method = RequestMethod.PUT)
    public ResponseResult<Object> disableFlowControl(@RequestBody @Validated AC4FlowStatusActionRequest request) {
        AC4FlowStatusActionParam ac4FlowStatusActionParam = ac4FlowWebConverter.req2param(request);
        flowControlService.disableFlowControl(ac4FlowStatusActionParam);
        return new ResponseResult<>(200, "规则停用成功");
    }

    @Operation(summary = "启用控制规则", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则已被启用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:enableFlowControl')")
    @RequestMapping(path = "/enable", method = RequestMethod.PUT)
    public ResponseResult<Object> enableFlowControl(@RequestBody @Validated AC4FlowStatusActionRequest request) {
        AC4FlowStatusActionParam ac4FlowStatusActionParam = ac4FlowWebConverter.req2param(request);
        flowControlService.enableFlowControl(ac4FlowStatusActionParam);
        return new ResponseResult<>(200, "规则启用成功");
    }

    @Operation(summary = "删除控制规则", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则删除成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:removeFlowControl')")
    @RequestMapping(path = "/remove", method = RequestMethod.DELETE)
    public ResponseResult<Object> removeFlowControl(@RequestBody @Validated List<AC4FlowStatusActionRequest> request) {
        for (AC4FlowStatusActionRequest ac4FlowStatusActionRequest : request) {
            AC4FlowStatusActionParam ac4FlowStatusActionParam = ac4FlowWebConverter.req2param(ac4FlowStatusActionRequest);
            flowControlService.removeFlowControl(ac4FlowStatusActionParam);
        }
        return new ResponseResult<>(200, "规则删除成功");
    }
}
