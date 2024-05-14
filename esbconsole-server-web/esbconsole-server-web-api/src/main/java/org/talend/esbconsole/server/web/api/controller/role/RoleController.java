package org.talend.esbconsole.server.web.api.controller.role;

import org.talend.esbconsole.server.domain.api.model.AuthorityOtherInfoDTO;
import org.talend.esbconsole.server.domain.api.model.SystemRoleDTO;
import org.talend.esbconsole.server.domain.api.model.SystemUserModel;
import org.talend.esbconsole.server.domain.api.param.AssignAuthorityParam;
import org.talend.esbconsole.server.domain.api.param.RoleCreateParam;
import org.talend.esbconsole.server.domain.api.param.RoleModifyParam;
import org.talend.esbconsole.server.domain.api.param.RolePageQueryParam;
import org.talend.esbconsole.server.domain.api.service.SystemRoleService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.role.converter.RoleWebConverter;
import org.talend.esbconsole.server.web.api.controller.role.request.*;
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
import org.talend.esbconsole.server.web.api.controller.role.request.AssignAuthorityRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RoleCreateRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RoleModifyRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RolePageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RoleRemoveRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * 用户管理-角色相关功能控制器
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Validated
@Tag(name = "2-2、用户管理-角色", description = "角色相关操作的接口")
@RestController
@RequestMapping("/api/user/role")
public class RoleController {

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private RoleWebConverter roleWebConverter;

    @Operation(summary = "根据角色ID获取角色的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(@Parameter(name = "id", description = "角色ID"))
    @ApiResponses({@ApiResponse(description = "返回角色的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:getRole')")
    @RequestMapping(path = "/getRole", method = RequestMethod.GET)
    public ResponseResult<SystemRoleDTO> getRole(@RequestParam(name = "id") @NotNull String id) {
        SystemRoleDTO systemRoleDTO = systemRoleService.getRole(id);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, systemRoleDTO);
    }

    @Operation(summary = "根据角色名称获取系统中是否已经包含该角色的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(@Parameter(name = "roleName", description = "角色名称"))
    @ApiResponses({@ApiResponse(description = "是否已经包含该角色的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:verifyRoleName')")
    @RequestMapping(path = "/verify", method = RequestMethod.GET)
    public ResponseResult<Boolean> verifyRoleName(@RequestParam(name = "roleName") @NotNull String roleName) {
        SystemRoleDTO systemRoleDTO = systemRoleService.getRoleByRoleName(roleName);
        if (Objects.isNull(systemRoleDTO)) {
            return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, false);
        } else {
            return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, true);
        }
    }

    @Operation(summary = "获取所有角色的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "是一个对象数组，每个对象描述了一个角色的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:getAllRoles')")
    @RequestMapping(path = "/listAll", method = RequestMethod.GET)
    public ResponseResult<List<SystemRoleDTO>> getAllRoles() {
        List<SystemRoleDTO> allRoles = systemRoleService.getAllRoles();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, allRoles);
    }

    @Operation(summary = "分页获取角色的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<SystemRoleDO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个角色的详细信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:getRoles')")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<SystemRoleDTO>> getRoles(@RequestBody @Validated RolePageQueryRequest request) {
        RolePageQueryParam rolePageQueryParam = roleWebConverter.req2param(request);
        PageResult<SystemRoleDTO> rolesPageInfo = systemRoleService.getRoles(rolePageQueryParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, rolesPageInfo);
    }

    @Operation(summary = "添加角色", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "角色添加成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:addRole')")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseResult<Object> addRole(@RequestBody @Validated RoleCreateRequest request) {
        RoleCreateParam roleCreateParam = roleWebConverter.req2param(request);
        systemRoleService.addRole(roleCreateParam);
        return new ResponseResult<>(200, "角色添加成功");
    }

    @Operation(summary = "删除角色", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "角色id")})
    @ApiResponses({@ApiResponse(description = "角色删除成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:removeRole')")
    @RequestMapping(path = "/remove", method = RequestMethod.DELETE)
    public ResponseResult<Object> removeRole(@RequestBody @Validated List<RoleRemoveRequest> request) {
        for (RoleRemoveRequest role : request) {
            if (systemRoleService.getRole(role.getId()) != null && systemRoleService.getRole(role.getId()).getRoleName().equals("admin")) {
                return new ResponseResult<>(200, "admin角色，不可被删除");
            }
            systemRoleService.removeRole(role.getId());
        }
        return new ResponseResult<>(200, "角色删除成功");
    }

    @Operation(summary = "更改角色信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "角色信息更改成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:modifyRole')")
    @RequestMapping(path = "/modify", method = RequestMethod.PUT)
    public ResponseResult<Object> modifyRole(@RequestBody @Valid RoleModifyRequest request) {
        RoleModifyParam roleModifyParam = roleWebConverter.req2param(request);
        systemRoleService.modifyRole(roleModifyParam);
        return new ResponseResult<>(200, "角色更改成功");
    }

    @Operation(summary = "停用角色", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "角色id")})
    @ApiResponses({@ApiResponse(description = "角色已被停用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:disableRole')")
    @RequestMapping(path = "/disable", method = RequestMethod.PUT)
    public ResponseResult<Object> disableRole(@RequestParam(name = "id") @NotNull String id) {
        systemRoleService.disableRole(id);
        return new ResponseResult<>(200, "角色停用成功");
    }

    @Operation(summary = "启用角色", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "角色id")})
    @ApiResponses({@ApiResponse(description = "角色已被启用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:enableRole')")
    @RequestMapping(path = "/enable", method = RequestMethod.PUT)
    public ResponseResult<Object> enableRole(@RequestParam(name = "id") @NotNull String id) {
        systemRoleService.enableRole(id);
        return new ResponseResult<>(200, "角色启用成功");
    }

    @Operation(summary = "分配权限功能的前置接口，1、获取所有权限和路由的树形结构数据。 2、获取所有的路由的ID。 3、获取该角色的权限的ID。", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(@Parameter(name = "id", description = "角色ID"))
    @ApiResponses({@ApiResponse(description = "返回角色的详细信息和角色拥有的所有权限信息", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:getAuthorityOtherInfo')")
    @RequestMapping(path = "/getAuthorityOtherInfo", method = RequestMethod.GET)
    public ResponseResult<AuthorityOtherInfoDTO> getAuthorityOtherInfo(@RequestParam(name = "id") @NotNull String id) {
        AuthorityOtherInfoDTO authorityOtherInfoDTO = systemRoleService.getAuthorityOtherInfo(id);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, authorityOtherInfoDTO);
    }

    @Operation(summary = "为角色分配权限", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "权限分配成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:assignAuthorityForRole')")
    @RequestMapping(path = "/assignAuthority", method = RequestMethod.POST)
    public ResponseResult<Object> assignAuthorityForRole(@RequestBody @Validated AssignAuthorityRequest request) {
        AssignAuthorityParam assignAuthorityParam = roleWebConverter.req2param(request);
        systemRoleService.assignAuthorityForRole(assignAuthorityParam);
        return new ResponseResult<>(200, "角色权限分配成功");
    }

    @Operation(summary = "获取该角色的使用者详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "获取信息成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:getInUsing')")
    @RequestMapping(path = "/getUserByRoleId", method = RequestMethod.GET)
    public ResponseResult<List<SystemUserModel>> getInUsing(@RequestParam(name = "id") @NotNull String id) {
        List<SystemUserModel> systemUserModels = systemRoleService.listUserRoleByRoleId(id);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, systemUserModels);
    }

    @Operation(summary = "取消用户的某个角色授权", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "取消授权成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:role:cancelAuthority')")
    @RequestMapping(path = "/cancelAuthority", method = RequestMethod.DELETE)
    public ResponseResult<Object> cancelAuthority(@RequestParam(name = "userId") @NotNull String userId, @RequestParam(name = "roleId") @NotNull
            String roleId) {
        systemRoleService.removeSystemUserRoleByUserIdAndRoleId(userId, roleId);
        return new ResponseResult<>(200, "取消授权成功");
    }
}
