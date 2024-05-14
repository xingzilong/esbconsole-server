package org.talend.esbconsole.server.web.api.controller.ac.ip;

import org.talend.esbconsole.server.domain.api.model.IPControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4IPPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4IPStatusActionParam;
import org.talend.esbconsole.server.domain.api.service.IPControlService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.ac.ip.converter.AC4IPWebConverter;
import org.talend.esbconsole.server.web.api.controller.ac.ip.request.AC4IPPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.ip.request.AC4IPStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.ip.vo.IPControlVO;
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
 * 服务访问控制-IP控制相关功能控制器
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Validated
@Tag(name = "3-1-1、控制管理-访问控制-IP控制", description = "访问控制相关操作的接口")
@Tag(name = "4-1-1、控制管理-访问控制-IP控制", description = "访问控制相关操作的接口")
@RestController
@RequestMapping("/api/control/ac/ip")
public class IPController {

    @Autowired
    private IPControlService ipControlService;

    @Autowired
    private AC4IPWebConverter ac4IPWebConverter;

    @Operation(summary = "分页获取IP控制规则的的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<IPControlVO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个IP控制规则信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:getIPControls')")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<IPControlVO>> getIPControls(@RequestBody @Validated AC4IPPageQueryRequest request) {
        AC4IPPageQueryParam ac4IPPageQueryParam = ac4IPWebConverter.req2param(request);
        PageResult<IPControlModel> ipControlsPageInfo = ipControlService.getIPControls(ac4IPPageQueryParam);
        List<IPControlVO> ipControlVOS = ac4IPWebConverter.dto2vo(ipControlsPageInfo.getList());
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, PageResult.of(ipControlVOS, ipControlsPageInfo.getTotal()));
    }

    @Operation(summary = "根据id获取一条规则信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "规则id")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<IPControlVO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个IP控制规则信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:getIPControlById')")
    @RequestMapping(path = "/getIPControl", method = RequestMethod.GET)
    public ResponseResult<IPControlVO> getIPControlById(@RequestParam(name = "id") @Size(max = 36) String id) {
        IPControlModel ipControl = ipControlService.getIPControlById(id);
        IPControlVO ipControlVO = ac4IPWebConverter.dto2vo(ipControl);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, ipControlVO);
    }

    @Operation(summary = "获取未绑定规则的服务", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则添加成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:getServiceNoBIPC')")
    @RequestMapping(path = "/getService", method = RequestMethod.GET)
    public ResponseResult<List<ServiceDTO>> getServiceNoBindIPControl() {
        List<ServiceDTO> serviceNoBindIPControl = ipControlService.getServiceNoBindIPControl();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, serviceNoBindIPControl);
    }

    @Operation(summary = "添加规则信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则添加成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:addIPControl')")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseResult<Object> addIPControl(@RequestBody @Validated AC4IPStatusActionRequest request) {
        AC4IPStatusActionParam ac4IPStatusActionParam = ac4IPWebConverter.req2param(request);
        ipControlService.addIPControl(ac4IPStatusActionParam);
        return new ResponseResult<>(200, "规则添加成功");
    }

    @Operation(summary = "更改规则信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则信息更改成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:modifyIPControl')")
    @RequestMapping(path = "/modify", method = RequestMethod.PUT)
    public ResponseResult<Object> modifyIPControl(@RequestBody @Validated AC4IPStatusActionRequest request) {
        AC4IPStatusActionParam ac4IPStatusActionParam = ac4IPWebConverter.req2param(request);
        ipControlService.modifyIPControls(ac4IPStatusActionParam);
        return new ResponseResult<>(200, "规则更改成功");
    }

    @Operation(summary = "停用控制规则", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则已被停用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:disableIPControl')")
    @RequestMapping(path = "/disable", method = RequestMethod.PUT)
    public ResponseResult<Object> disableIPControl(@RequestBody @Validated AC4IPStatusActionRequest request) {
        AC4IPStatusActionParam ac4IPStatusActionParam = ac4IPWebConverter.req2param(request);
        ipControlService.disableIPControl(ac4IPStatusActionParam);
        return new ResponseResult<>(200, "规则停用成功");
    }

    @Operation(summary = "启用控制规则", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则已被启用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:enableIPControl')")
    @RequestMapping(path = "/enable", method = RequestMethod.PUT)
    public ResponseResult<Object> enableIPControl(@RequestBody @Validated AC4IPStatusActionRequest request) {
        AC4IPStatusActionParam ac4IPStatusActionParam = ac4IPWebConverter.req2param(request);
        ipControlService.enableIPControl(ac4IPStatusActionParam);
        return new ResponseResult<>(200, "规则启用成功");
    }

    @Operation(summary = "删除控制规则", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则删除成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:removeIPControl')")
    @RequestMapping(path = "/remove", method = RequestMethod.DELETE)
    public ResponseResult<Object> removeIPControl(@RequestBody @Validated List<AC4IPStatusActionRequest> request) {
        for (AC4IPStatusActionRequest ac4IPStatusActionRequest : request) {
            AC4IPStatusActionParam ac4IPStatusActionParam = ac4IPWebConverter.req2param(ac4IPStatusActionRequest);
            ipControlService.removeIPControl(ac4IPStatusActionParam);
        }
        return new ResponseResult<>(200, "规则删除成功");
    }
}
