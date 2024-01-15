package org.talend.esbconsole.server.web.api.controller.server;

import org.talend.esbconsole.server.domain.api.model.JVMDetails;
import org.talend.esbconsole.server.domain.api.model.JVMInfo;
import org.talend.esbconsole.server.domain.api.service.JVMService;
import org.talend.esbconsole.server.tools.base.exception.ServerInfoReadException;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.server.converter.ServerWebConverter;
import org.talend.esbconsole.server.web.api.controller.server.service.Server;
import org.talend.esbconsole.server.web.api.controller.server.vo.JVMInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统管理-资源监控相关功能控制器
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Tag(name = "6-1、系统管理-资源监控", description = "资源监控相关接口")
@RestController
@RequestMapping("/api/system/server")
public class ServerController {

    @Autowired
    private JVMService jvmService;

    @Autowired
    private ServerWebConverter serverWebConverter;

    @Operation(summary = "获取服务器资源信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功。", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('system:server:getServerInfo')")
    @RequestMapping(path = "/getServerInfo", method = RequestMethod.GET)
    public ResponseResult<Server> getServerInfo() {
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {
            throw new ServerInfoReadException(e.getMessage());
        }
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, server);
    }

    @Operation(summary = "获取ESBServer的JVM内存信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功。", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('system:server:getESBServerJVMMemoryInfo')")
    @RequestMapping(path = "/getJVMMemoryInfo", method = RequestMethod.GET)
    public ResponseResult<JVMInfoVO> getESBServerJVMMemoryInfo() {
        JVMInfo esbServerJVMMemory = jvmService.getESBServerJVMMemory();
        JVMInfoVO jvmInfoVO = serverWebConverter.dto2vo(esbServerJVMMemory);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, jvmInfoVO);
    }

    @Operation(summary = "获取ESBServer的JVM信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "数据获取成功。", responseCode = "200"),
            @ApiResponse(description = "数据获取失败", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('system:server:getESBServerJVMInfo')")
    @RequestMapping(path = "/getJVMDetails", method = RequestMethod.GET)
    public ResponseResult<JVMDetails> getESBServerJVMInfo() {
        JVMDetails jvmDetails = jvmService.getESBServerJVMInfo();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, jvmDetails);
    }
}
