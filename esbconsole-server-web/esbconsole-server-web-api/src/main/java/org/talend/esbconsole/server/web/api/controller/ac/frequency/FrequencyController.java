package org.talend.esbconsole.server.web.api.controller.ac.frequency;

import org.talend.esbconsole.server.domain.api.model.FrequencyControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyStatusActionParam;
import org.talend.esbconsole.server.domain.api.service.FrequencyControlService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.converter.AC4FrequencyWebConverter;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.request.AC4FrequencyPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.request.AC4FrequencyStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.vo.FrequencyControlVO;
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
 * 服务访问控制-频次控制相关功能控制器
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Validated
@Tag(name = "4-1-3、控制管理-访问控制-频次控制", description = "访问控制相关操作的接口")
@RestController
@RequestMapping("/api/control/ac/frequency")
public class FrequencyController {

    @Autowired
    private FrequencyControlService frequencyControlService;

    @Autowired
    private AC4FrequencyWebConverter ac4FrequencyWebConverter;

    @Operation(summary = "分页获取控制规则的的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<FrequencyControlVO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个频次控制规则信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:getFrequencyControls')")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<FrequencyControlVO>> getFrequencyControls(@RequestBody @Validated AC4FrequencyPageQueryRequest request) {
        AC4FrequencyPageQueryParam ac4FrequencyPageQueryParam = ac4FrequencyWebConverter.req2param(request);
        PageResult<FrequencyControlModel> frequencyControlsPageInfo = frequencyControlService.getFrequencyControls(ac4FrequencyPageQueryParam);
        List<FrequencyControlVO> frequencyControlVOS = ac4FrequencyWebConverter.dto2vo(frequencyControlsPageInfo.getList());
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, PageResult.of(frequencyControlVOS, frequencyControlsPageInfo.getTotal()));
    }

    @Operation(summary = "根据id获取一条规则信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "规则id")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<FrequencyControlVO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个频次控制规则信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:getFrequencyControlById')")
    @RequestMapping(path = "/getFrequencyControl", method = RequestMethod.GET)
    public ResponseResult<FrequencyControlVO> getFrequencyControlById(@RequestParam(name = "id") @Size(max = 36) String id) {
        FrequencyControlModel frequencyControl = frequencyControlService.getFrequencyControlById(id);
        FrequencyControlVO frequencyControlVO = ac4FrequencyWebConverter.dto2vo(frequencyControl);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, frequencyControlVO);
    }

    @Operation(summary = "获取未绑定规则的服务", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则添加成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:getServiceNoBFC')")
    @RequestMapping(path = "/getService", method = RequestMethod.GET)
    public ResponseResult<List<ServiceDTO>> getServiceNoBindFrequencyControl() {
        List<ServiceDTO> serviceNoBindFrequencyControl = frequencyControlService.getServiceNoBindFrequencyControl();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, serviceNoBindFrequencyControl);
    }

    @Operation(summary = "添加规则信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则添加成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:addFrequencyControl')")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseResult<Object> addFrequencyControl(@RequestBody @Validated AC4FrequencyStatusActionRequest request) {
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = ac4FrequencyWebConverter.req2param(request);
        frequencyControlService.addFrequencyControl(ac4FrequencyStatusActionParam);
        return new ResponseResult<>(200, "规则添加成功");
    }

    @Operation(summary = "更改规则信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则信息更改成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:modifyFrequencyControl')")
    @RequestMapping(path = "/modify", method = RequestMethod.PUT)
    public ResponseResult<Object> modifyFrequencyControl(@RequestBody @Validated AC4FrequencyStatusActionRequest request) {
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = ac4FrequencyWebConverter.req2param(request);
        frequencyControlService.modifyFrequencyControls(ac4FrequencyStatusActionParam);
        return new ResponseResult<>(200, "规则更改成功");
    }

    @Operation(summary = "停用控制规则", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则已被停用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:disableFrequencyControl')")
    @RequestMapping(path = "/disable", method = RequestMethod.PUT)
    public ResponseResult<Object> disableFrequencyControl(@RequestBody @Validated AC4FrequencyStatusActionRequest request) {
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = ac4FrequencyWebConverter.req2param(request);
        frequencyControlService.disableFrequencyControl(ac4FrequencyStatusActionParam);
        return new ResponseResult<>(200, "规则停用成功");
    }

    @Operation(summary = "启用控制规则", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则已被启用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:enableFrequencyControl')")
    @RequestMapping(path = "/enable", method = RequestMethod.PUT)
    public ResponseResult<Object> enableFrequencyControl(@RequestBody @Validated AC4FrequencyStatusActionRequest request) {
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = ac4FrequencyWebConverter.req2param(request);
        frequencyControlService.enableFrequencyControl(ac4FrequencyStatusActionParam);
        return new ResponseResult<>(200, "规则启用成功");
    }

    @Operation(summary = "删除控制规则", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "规则删除成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('control:ac:removeFrequencyControl')")
    @RequestMapping(path = "/remove", method = RequestMethod.DELETE)
    public ResponseResult<Object> removeFrequencyControl(@RequestBody @Validated List<AC4FrequencyStatusActionRequest> request) {
        for (AC4FrequencyStatusActionRequest ac4FrequencyStatusActionRequest : request) {
            AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = ac4FrequencyWebConverter.req2param(ac4FrequencyStatusActionRequest);
            frequencyControlService.removeFrequencyControl(ac4FrequencyStatusActionParam);
        }
        return new ResponseResult<>(200, "规则删除成功");
    }

}
