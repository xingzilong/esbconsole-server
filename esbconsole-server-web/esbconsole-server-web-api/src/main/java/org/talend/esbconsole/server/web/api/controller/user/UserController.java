package org.talend.esbconsole.server.web.api.controller.user;

import org.talend.esbconsole.server.domain.api.model.SystemUserDTO;
import org.talend.esbconsole.server.domain.api.model.UserRolesDTO;
import org.talend.esbconsole.server.domain.api.param.AssignRoleParam;
import org.talend.esbconsole.server.domain.api.param.ResetPasswordParam;
import org.talend.esbconsole.server.domain.api.param.UserCreateParam;
import org.talend.esbconsole.server.domain.api.param.UserModifyParam;
import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;
import org.talend.esbconsole.server.domain.api.service.SystemUserService;
import org.talend.esbconsole.server.domain.core.util.LoginUserDetails;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.user.converter.UserWebConverter;
import org.talend.esbconsole.server.web.api.controller.user.request.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.talend.esbconsole.server.web.api.controller.user.request.AssignRoleRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.ResetPasswordRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserCreateRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserModifyRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserRemoveRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.VerifyAndChangePasswordRequest;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * 用户管理-用户相关功能控制器
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Validated
@Tag(name = "2-1、用户管理-用户", description = "用户相关操作的接口")
@RestController
@RequestMapping("/api/user/user")
public class UserController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private UserWebConverter userWebConverter;

    @Operation(summary = "根据用户id获取用户的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(@Parameter(name = "id", description = "用户id"))
    @ApiResponses({@ApiResponse(description = "返回用户的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:getUser')")
    @RequestMapping(path = "/getUser", method = RequestMethod.GET)
    public ResponseResult<SystemUserDTO> getUser(@RequestParam(name = "id") @NotNull String id) {
        SystemUserDTO user = systemUserService.getUser(id);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, user);
    }

    @Operation(summary = "根据用户名称获取系统中是否已经包含该用户的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(@Parameter(name = "userName", description = "用户名称"))
    @ApiResponses({@ApiResponse(description = "是否已经包含该用户的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:verifyUserName')")
    @RequestMapping(path = "/verify", method = RequestMethod.GET)
    public ResponseResult<Boolean> verifyUserName(@RequestParam(name = "userName") @NotNull String userName) {
        SystemUserDTO systemUserDTO = systemUserService.getUserByUserName(userName);
        if (Objects.isNull(systemUserDTO)) {
            return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, false);
        } else {
            return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, true);
        }
    }

    @Operation(summary = "获取所有用户的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "是一个对象数组，每个对象描述了一个用户的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:listAll')")
    @RequestMapping(path = "/listAll", method = RequestMethod.GET)
    public ResponseResult<List<SystemUserDTO>> getAllUsers() {
        List<SystemUserDTO> allUsers = systemUserService.getAllUsers();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, allUsers);
    }

    @Operation(summary = "分页获取用户的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个PageInfo<SystemUserDO>对象,该对象有一个list属性，是一个对象数组，每个对象描述了一个用户的详细信息。",
            responseCode = "200"), @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:list')")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<SystemUserDTO>> getUsers(@RequestBody @Validated UserPageQueryRequest request) {
        UserPageQueryParam userPageQueryParam = userWebConverter.req2param(request);
        PageResult<SystemUserDTO> userPageResult = systemUserService.getUsers(userPageQueryParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, userPageResult);
    }

    @Operation(summary = "添加用户", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "用户添加成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:add')")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseResult<Object> addUser(@RequestBody @Validated UserCreateRequest request) {
        UserCreateParam userCreateParam = userWebConverter.req2param(request);
        systemUserService.addUser(userCreateParam);
        return new ResponseResult<>(200, "用户添加成功");
    }

    @Operation(summary = "删除用户", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "用户id")})
    @ApiResponses({@ApiResponse(description = "用户删除成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:remove')")
    @RequestMapping(path = "/remove", method = RequestMethod.DELETE)
    public ResponseResult<Object> removeUser(@RequestBody @Validated List<UserRemoveRequest> request) {
        for (UserRemoveRequest user : request) {
            if (systemUserService.getUser(user.getId()) != null && systemUserService.getUser(user.getId()).getUserName().equals("admin")) {
                return new ResponseResult<>(200, "admin用户，不可被删除");
            }
            systemUserService.removeUser(user.getId());
        }
        return new ResponseResult<>(200, "用户删除成功");
    }

    @Operation(summary = "更改用户信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "用户信息更改成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:modify')")
    @RequestMapping(path = "/modify", method = RequestMethod.PUT)
    public ResponseResult<Object> modifyUser(@RequestBody @Validated UserModifyRequest request) {
        UserModifyParam userModifyParam = userWebConverter.req2param(request);
        systemUserService.modifyUser(userModifyParam);
        return new ResponseResult<>(200, "用户更改成功");
    }

    @Operation(summary = "停用用户", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "用户id")})
    @ApiResponses({@ApiResponse(description = "用户已被停用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:disable')")
    @RequestMapping(path = "/disable", method = RequestMethod.PUT)
    public ResponseResult<Object> disableUser(@RequestParam(name = "id") @NotNull String id) {
        systemUserService.disableUser(id);
        return new ResponseResult<>(200, "用户停用成功");
    }

    @Operation(summary = "启用用户", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "用户id")})
    @ApiResponses({@ApiResponse(description = "用户已被启用。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:enable')")
    @RequestMapping(path = "/enable", method = RequestMethod.PUT)
    public ResponseResult<Object> enableUser(@RequestParam(name = "id") @NotNull String id) {
        systemUserService.enableUser(id);
        return new ResponseResult<>(200, "用户启用成功");
    }

    @Operation(summary = "重置用户密码", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "密码重置成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:resetPassword')")
    @RequestMapping(path = "/resetPassword", method = RequestMethod.PUT)
    public ResponseResult<Object> resetPassword(@RequestBody @Validated ResetPasswordRequest request) {
        ResetPasswordParam resetPasswordParam = userWebConverter.req2param(request);
        systemUserService.resetPassword(resetPasswordParam);
        return new ResponseResult<>(200, "密码重置成功");
    }

    @Operation(summary = "获取所有用户列表以及用户拥有的用户", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters({@Parameter(name = "id", description = "用户id")})
    @ApiResponses({@ApiResponse(description = "获取成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:getUserRoles')")
    @RequestMapping(path = "/getUserRoles", method = RequestMethod.GET)
    public ResponseResult<UserRolesDTO> getRole(@RequestParam(name = "id") @NotNull String id) {
        UserRolesDTO userRolesDTO = systemUserService.getAllRolesAndUserRoles(id);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, userRolesDTO);
    }

    @Operation(summary = "为用户分配角色", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "密码重置成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('user:user:assignRole')")
    @RequestMapping(path = "/assignRole", method = RequestMethod.POST)
    public ResponseResult<Object> assignRoleForUser(@RequestBody @Validated AssignRoleRequest request) {
        AssignRoleParam assignRoleParam = userWebConverter.req2param(request);
        systemUserService.assignRoleForUser(assignRoleParam);
        return new ResponseResult<>(200, "用户分配角色成功");
    }

    @Operation(summary = "验证当前密码", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "密码正确。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
//    @PreAuthorize("@AE.hasAuthority('user:user:verifyPassword')")
    @RequestMapping(path = "/verifyPassword", method = RequestMethod.POST)
    public ResponseResult<Object> verifyPassword(@RequestBody @Validated VerifyAndChangePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        String id = loginUserDetails.getSystemUser().getId();
        systemUserService.verifyPassword(id, request.getPassword());
        return new ResponseResult<>(200, "密码正确");
    }

    @Operation(summary = "修改密码", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "密码修改成功。", responseCode = "200"),
            @ApiResponse(description = "服务端异常", responseCode = "500")})
//    @PreAuthorize("@AE.hasAuthority('user:user:changePassword')")
    @RequestMapping(path = "/changePassword", method = RequestMethod.POST)
    public ResponseResult<Object> changePassword(@RequestBody @Validated VerifyAndChangePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        String id = loginUserDetails.getSystemUser().getId();
        systemUserService.changePassword(id, request.getPassword());
        return new ResponseResult<>(200, "密码修改成功");
    }

}
