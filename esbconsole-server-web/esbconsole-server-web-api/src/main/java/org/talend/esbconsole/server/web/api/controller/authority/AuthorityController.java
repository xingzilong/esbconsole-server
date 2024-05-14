package org.talend.esbconsole.server.web.api.controller.authority;

import org.talend.esbconsole.server.domain.api.model.RouteAndAuthorityModel;
import org.talend.esbconsole.server.domain.api.model.SystemAuthorityDTO;
import org.talend.esbconsole.server.domain.api.service.SystemAuthorityService;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户管理-用户相关功能控制器
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Validated
@Tag(name = "2-3、用户管理-权限", description = "权限相关操作的接口")
@RestController
@RequestMapping("/api/user/authority")
public class AuthorityController {

    @Autowired
    private SystemAuthorityService systemAuthorityService;

    @Operation(summary = "根据权限ID获取权限的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(@Parameter(name = "id", description = "权限ID"))
    @ApiResponses({@ApiResponse(description = "返回权限的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:authority:getAuthority')")
    @RequestMapping(path = "/getAuthority", method = RequestMethod.GET)
    public ResponseResult<SystemAuthorityDTO> getAuthority(@RequestParam(name = "id") @NotNull Long id) {
        SystemAuthorityDTO authority = systemAuthorityService.getAuthority(id);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, authority);
    }

    @Operation(summary = "获取所有权限的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "是一个对象数组，每个对象描述了一个权限的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:authority:getAllAuthorities')")
    @RequestMapping(path = "/listAll", method = RequestMethod.GET)
    public ResponseResult<List<SystemAuthorityDTO>> getAllAuthorities() {
        List<SystemAuthorityDTO> allAuthorities = systemAuthorityService.getAllAuthorities();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, allAuthorities);
    }

    @Operation(summary = "获取所有权限（和路由）的详细信息，树形结构", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "是一个对象数组，每个对象描述了一个权限（或路由）的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:authority:getAllAuthoritiesTree')")
    @RequestMapping(path = "/listAllTree", method = RequestMethod.GET)
    public ResponseResult<List<RouteAndAuthorityModel>> getAllAuthoritiesTree() {
        List<RouteAndAuthorityModel> allAuthoritiesTree = systemAuthorityService.getAllAuthoritiesTree();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, allAuthoritiesTree);
    }

}
