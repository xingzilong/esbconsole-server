package org.talend.esbconsole.server.start.authentication;

import org.talend.esbconsole.server.domain.api.model.RouterDTO;
import org.talend.esbconsole.server.domain.api.model.UserInfoDTO;
import org.talend.esbconsole.server.domain.api.param.LoginUserParam;
import org.talend.esbconsole.server.domain.api.service.SystemLoginService;
import org.talend.esbconsole.server.domain.core.util.LoginUserDetails;
import org.talend.esbconsole.server.start.authentication.converter.AuthenticationWebConverter;
import org.talend.esbconsole.server.start.authentication.request.LoginUserRequest;
import org.talend.esbconsole.server.tools.base.exception.authentication.LoginException;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统登录相关功能控制器
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Tag(name = "0、认证鉴权", description = "认证鉴权相关操作的接口")
@Slf4j
@RestController
@RequestMapping("/api/authentication")
public class SystemLoginController {

    @Autowired
    private SystemLoginService systemLoginService;

    @Autowired
    private AuthenticationWebConverter authenticationWebConverter;


    @Operation(summary = "登录")
    @ApiResponses({@ApiResponse(description = "登录成功。", responseCode = "200"),
            @ApiResponse(description = "登录失败", responseCode = "500")})
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseResult<Object> login(@RequestBody LoginUserRequest request) {
        LoginUserParam loginUserParam = authenticationWebConverter.req2param(request);
        String jwt = "";
        try {
            jwt = systemLoginService.login(loginUserParam);
        } catch (AuthenticationException ex) {
            log.warn("登录异常：" + ex.getMessage());
            throw new LoginException(ex.getMessage());
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseResult<>(200, "登录成功", map);
    }

    @Operation(summary = "登出", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "登出成功。", responseCode = "200"),
            @ApiResponse(description = "登出失败", responseCode = "500")})
    @RequestMapping(path = "/logout", method = RequestMethod.DELETE)
    public ResponseResult<Object> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        String id = loginUserDetails.getSystemUser().getId();
        systemLoginService.logout(id);
        return new ResponseResult<>(200, "登出成功");
    }

    @Operation(summary = "获取当前登录用户的详细信息（包含权限信息）", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回当前登陆用户的详细信息。", responseCode = "200"),
            @ApiResponse(description = "服务器异常", responseCode = "500")})
    @RequestMapping(path = "/getUserInfo", method = RequestMethod.GET)
    public ResponseResult<UserInfoDTO> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        String id = loginUserDetails.getSystemUser().getId();
        UserInfoDTO userInfoDTO = systemLoginService.getUserInfo(id);
        return new ResponseResult<>(200, "获取用户信息成功", userInfoDTO);
    }

    @Operation(summary = "获取当前登录用户的路由表", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回当前用户的路由表。", responseCode = "200"),
            @ApiResponse(description = "服务器异常", responseCode = "500")})
    @RequestMapping(path = "/getRoutes", method = RequestMethod.GET)
    public ResponseResult<List<RouterDTO>> getRoutes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        String id = loginUserDetails.getSystemUser().getId();
        List<RouterDTO> routers = systemLoginService.getRouter(id);
        return new ResponseResult<>(200, "获取路由表成功", routers);
    }
}
