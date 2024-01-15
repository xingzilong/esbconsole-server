package org.talend.esbconsole.server.web.api.controller.bundle;

import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.param.BundlePageQueryParam;
import org.talend.esbconsole.server.domain.api.service.BundleService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.bundle.converter.BundleWebConverter;
import org.talend.esbconsole.server.web.api.controller.bundle.request.BundlePageQueryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 服务管理-构件相关功能控制器
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Slf4j
@Tag(name = "3-2、服务管理-构件", description = "Bundle相关操作的接口")
@RestController
@RequestMapping("/api/service/bundle")
public class BundleController {

    @Autowired
    private BundleService bundleService;

    @Autowired
    private BundleWebConverter bundleWebConverter;

    @Operation(summary = "获取所有的Bundle的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个对象数组，每个对象描述了一个Bundle的详细信息。", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:bundle:listAll')")
    @RequestMapping(path = "/listAll", method = RequestMethod.GET)
    public ResponseResult<List<BundleInfo>> getAllBundles() {
        // List<BundleInfo> bundleInfos = bundleService.getAllBundlesForOSGI();
        List<BundleInfo> bundleInfos = bundleService.getAllBundlesForKaraf();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, bundleInfos);
    }

    @Operation(summary = "分页获取Bundle的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个对象数组，每个对象描述了一个Bundle的详细信息。", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:bundle:list')")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<BundleInfo>> getBundles(@RequestBody BundlePageQueryRequest request) {
        BundlePageQueryParam bundlePageQueryParam = bundleWebConverter.req2param(request);
        PageResult<BundleInfo> bundlePageInfo = bundleService.getBundlesForKaraf(bundlePageQueryParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, bundlePageInfo);
    }

    @Operation(summary = "启动Bundle", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "启动成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:bundle:start')")
    @RequestMapping(path = "/start", method = RequestMethod.PUT)
    public ResponseResult<Object> startBundles(@RequestBody List<BundleInfo> listBundles) {
        for (BundleInfo bundle : listBundles) {
            bundleService.startBundleByID(bundle.getIdentifier());
        }
        return new ResponseResult<>(200, "启动成功");
    }

    @Operation(summary = "停止Bundle", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "停止成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:bundle:stop')")
    @RequestMapping(path = "/stop", method = RequestMethod.PUT)
    public ResponseResult<Object> stopBundle(@RequestBody List<BundleInfo> listBundles) {
        for (BundleInfo bundle : listBundles) {
            bundleService.stopBundleByID(bundle.getIdentifier());
        }
        return new ResponseResult<>(200, "停止成功");
    }
}
