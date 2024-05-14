package org.talend.esbconsole.server.web.api.controller.dictionary;

import org.talend.esbconsole.server.domain.api.model.BusinessSystemDTO;
import org.talend.esbconsole.server.domain.api.model.ConsumerSystemDTO;
import org.talend.esbconsole.server.domain.api.param.BusinessCreateParam;
import org.talend.esbconsole.server.domain.api.param.BusinessModifyParam;
import org.talend.esbconsole.server.domain.api.param.BusinessPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerCreateParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerModifyParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerPageQueryParam;
import org.talend.esbconsole.server.domain.api.service.BusinessSystemService;
import org.talend.esbconsole.server.domain.api.service.ConsumerSystemService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.dictionary.converter.DictionaryWebConverter;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessCreateRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessModifyRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessRemoveRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerCreateRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerModifyRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerRemoveRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 服务管理-字典相关功能控制器
 *
 * @author xingzilong
 * @date 2021/08/22
 */
@Validated
@Slf4j
@Tag(name = "3-4、服务管理-字典", description = "字典相关操作的接口")
@RestController
@RequestMapping("/api/service/dictionary")
public class DictionaryController {

    @Autowired
    private ConsumerSystemService consumerSystemService;

    @Autowired
    private BusinessSystemService businessSystemService;

    @Autowired
    private DictionaryWebConverter dictionaryWebConverter;

    @Operation(summary = "根据消费方系统ID获取消费方系统的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(@Parameter(name = "id", description = "消费方系统ID"))
    @ApiResponses({@ApiResponse(description = "返回消费方系统的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:getConsumer')")
    @RequestMapping(path = "/consumer/getConsumer", method = RequestMethod.GET)
    public ResponseResult<ConsumerSystemDTO> getConsumer(@RequestParam(name = "id") @Size(max = 36) String id) {
        ConsumerSystemDTO consumerSystemDTO = consumerSystemService.getConsumer(id);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, consumerSystemDTO);
    }

    @Operation(summary = "根据消费方系统IP地址获取是否包含该消费方系统的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(@Parameter(name = "ip", description = "消费方系统ip地址"))
    @ApiResponses({@ApiResponse(description = "返回是否包含该消费方系统的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:verifyConsumerIp')")
    @RequestMapping(path = "/consumer/verify", method = RequestMethod.GET)
    public ResponseResult<Boolean> verifyConsumerIp(@RequestParam(name = "ip") @Size(max = 36) String ip) {
        ConsumerSystemDTO consumerSystemDTO = consumerSystemService.getConsumerByIp(ip);
        if (consumerSystemDTO == null) {
            return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, false);
        } else {
            return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, true);
        }
    }

    @Operation(summary = "分页获取消费方系统的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<ConsumerSystemDO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个消费方系统的详细信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:getConsumers')")
    @RequestMapping(path = "/consumer/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<ConsumerSystemDTO>> getConsumers(@RequestBody @Validated ConsumerPageQueryRequest request) {
        ConsumerPageQueryParam consumerPageQueryParam = dictionaryWebConverter.req2param(request);
        PageResult<ConsumerSystemDTO> consumersPageInfo = consumerSystemService.getConsumers(consumerPageQueryParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, consumersPageInfo);
    }

    @Operation(summary = "添加消费方系统", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "消费方系统添加成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:addConsumer')")
    @RequestMapping(path = "/consumer/add", method = RequestMethod.POST)
    public ResponseResult<Object> addConsumer(@RequestBody @Validated ConsumerCreateRequest request) {
        ConsumerCreateParam consumerCreateParam = dictionaryWebConverter.req2param(request);
        consumerSystemService.addConsumer(consumerCreateParam);
        return new ResponseResult<>(200, "消费方系统添加成功");
    }

    @Operation(summary = "删除消费方系统", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "消费方系统id")})
    @ApiResponses({@ApiResponse(description = "消费方系统删除成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:removeConsumer')")
    @RequestMapping(path = "/consumer/remove", method = RequestMethod.DELETE)
    public ResponseResult<Object> removeConsumer(@RequestBody @Validated List<ConsumerRemoveRequest> request) {
        for (ConsumerRemoveRequest consumerRemoveRequest : request) {
            consumerSystemService.removeConsumer(consumerRemoveRequest.getId());
        }
        return new ResponseResult<>(200, "消费方系统删除成功");
    }

    @Operation(summary = "更改消费方系统信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "消费方系统信息更改成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:modifyConsumer')")
    @RequestMapping(path = "/consumer/modify", method = RequestMethod.PUT)
    public ResponseResult<Object> modifyConsumer(@RequestBody @Validated ConsumerModifyRequest request) {
        ConsumerModifyParam consumerModifyParam = dictionaryWebConverter.req2param(request);
        consumerSystemService.modifyConsumer(consumerModifyParam);
        return new ResponseResult<>(200, "消费方系统更改成功");
    }

    @Operation(summary = "停用消费方系统", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "消费方系统id")})
    @ApiResponses({@ApiResponse(description = "消费方系统已被停用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:disableConsumer')")
    @RequestMapping(path = "/consumer/disable", method = RequestMethod.PUT)
    public ResponseResult<Object> disableConsumer(@RequestParam(name = "id") @Size(max = 36) String id) {
        consumerSystemService.disableConsumer(id);
        return new ResponseResult<>(200, "消费方系统停用成功");
    }

    @Operation(summary = "启用消费方系统", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "消费方系统id")})
    @ApiResponses({@ApiResponse(description = "消费方系统已被启用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:enableConsumer')")
    @RequestMapping(path = "/consumer/enable", method = RequestMethod.PUT)
    public ResponseResult<Object> enableConsumer(@RequestParam(name = "id") @Size(max = 36) String id) {
        consumerSystemService.enableConsumer(id);
        return new ResponseResult<>(200, "消费方系统启用成功");
    }

    @Operation(summary = "根据业务系统ID获取业务系统的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(@Parameter(name = "id", description = "业务系统ID"))
    @ApiResponses({@ApiResponse(description = "返回业务系统的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:getBusinessById')")
    @RequestMapping(path = "/business/getBusinessById", method = RequestMethod.GET)
    public ResponseResult<BusinessSystemDTO> getBusinessById(@RequestParam(name = "id") @Size(max = 36) String id) {
        BusinessSystemDTO businessSystemDTO = businessSystemService.getBusiness(id);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, businessSystemDTO);
    }

    @Operation(summary = "根据业务系统名称获取是否包含该业务系统名称的信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(@Parameter(name = "systemName", description = "业务系统名称"))
    @ApiResponses({@ApiResponse(description = "返回是否包含该业务系统名称的信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:verifyBusinessSN')")
    @RequestMapping(path = "/business/verify", method = RequestMethod.GET)
    public ResponseResult<Boolean> verifyBusinessSystemName(@RequestParam(name = "systemName") @NotNull String systemName) {
        BusinessSystemDTO businessSystemDTO = businessSystemService.getBusinessByBusinessName(systemName);
        if (businessSystemDTO == null) {
            return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, false);
        } else {
            return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, true);
        }
    }

    @Operation(summary = "分页获取业务系统的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<BusinessSystemDO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个业务系统的详细信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:getBusinesss')")
    @RequestMapping(path = "/business/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<BusinessSystemDTO>> getBusinesss(@RequestBody @Validated BusinessPageQueryRequest request) {
        BusinessPageQueryParam businessPageQueryParam = dictionaryWebConverter.req2param(request);
        PageResult<BusinessSystemDTO> businesssPageInfo = businessSystemService.getBusinesss(businessPageQueryParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, businesssPageInfo);
    }

    @Operation(summary = "获取业务系统的所有系统名称", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个List<String>>对象",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:getAllBusinessSN')")
    @RequestMapping(path = "/business/listAllBSN", method = RequestMethod.GET)
    public ResponseResult<List<BusinessSystemDTO>> getAllBusinessSystemName() {
        List<BusinessSystemDTO> allBusinessSystemName = businessSystemService.getAllBusinessSystemName();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, allBusinessSystemName);
    }

    @Operation(summary = "添加业务系统", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "业务系统添加成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:addBusiness')")
    @RequestMapping(path = "/business/add", method = RequestMethod.POST)
    public ResponseResult<Object> addBusiness(@RequestBody @Validated BusinessCreateRequest request) {
        BusinessCreateParam businessCreateParam = dictionaryWebConverter.req2param(request);
        businessSystemService.addBusiness(businessCreateParam);
        return new ResponseResult<>(200, "业务系统添加成功");
    }

    @Operation(summary = "删除业务系统", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "业务系统id")})
    @ApiResponses({@ApiResponse(description = "业务系统删除成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:removeBusiness')")
    @RequestMapping(path = "/business/remove", method = RequestMethod.DELETE)
    public ResponseResult<Object> removeBusiness(@RequestBody @Validated List<BusinessRemoveRequest> request) {
        for (BusinessRemoveRequest businessRemoveRequest : request) {
            businessSystemService.removeBusiness(businessRemoveRequest.getId());
        }
        return new ResponseResult<>(200, "业务系统删除成功");
    }

    @Operation(summary = "更改业务系统信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "业务系统信息更改成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:modifyBusiness')")
    @RequestMapping(path = "/business/modify", method = RequestMethod.PUT)
    public ResponseResult<Object> modifyBusiness(@RequestBody @Validated BusinessModifyRequest request) {
        BusinessModifyParam businessModifyParam = dictionaryWebConverter.req2param(request);
        businessSystemService.modifyBusiness(businessModifyParam);
        return new ResponseResult<>(200, "业务系统更改成功");
    }

    @Operation(summary = "停用业务系统", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "业务系统id")})
    @ApiResponses({@ApiResponse(description = "业务系统已被停用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:disableBusiness')")
    @RequestMapping(path = "/business/disable", method = RequestMethod.PUT)
    public ResponseResult<Object> disableBusiness(@RequestParam(name = "id") @Size(max = 36) String id) {
        businessSystemService.disableBusiness(id);
        return new ResponseResult<>(200, "业务系统停用成功");
    }

    @Operation(summary = "启用业务系统", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "业务系统id")})
    @ApiResponses({@ApiResponse(description = "业务系统已被启用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:dictionary:enableBusiness')")
    @RequestMapping(path = "/business/enable", method = RequestMethod.PUT)
    public ResponseResult<Object> enableBusiness(@RequestParam(name = "id") @Size(max = 36) String id) {
        businessSystemService.enableBusiness(id);
        return new ResponseResult<>(200, "业务系统已被启用成功");
    }
}
