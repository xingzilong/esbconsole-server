package org.talend.esbconsole.server.web.api.controller.feature;

import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.model.FeatureInfo;
import org.talend.esbconsole.server.domain.api.param.FeaturePageQueryParam;
import org.talend.esbconsole.server.domain.api.service.BundleService;
import org.talend.esbconsole.server.domain.api.service.FeatureService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.web.api.controller.feature.converter.FeatureWebConverter;
import org.talend.esbconsole.server.web.api.controller.feature.request.FeaturePageQueryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务管理-特性（feature）相关功能控制器
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Validated
@Slf4j
@Tag(name = "3-3、服务管理-特性", description = "Feature相关操作的接口")
@RestController
@RequestMapping("/api/service/feature")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @Autowired
    private BundleService bundleService;

    @Autowired
    private FeatureWebConverter featureWebConverter;

    @Operation(summary = "获取所有的Feature的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个对象数组，每个对象描述了一个Feature的详细信息。", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:feature:listAll')")
    @RequestMapping(path = "/listAll", method = RequestMethod.GET)
    public ResponseResult<List<FeatureInfo>> getAllFeatures() {
        List<FeatureInfo> allFeatureList = featureService.getAllFeaturesForKaraf();
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, allFeatureList);
    }

    @Operation(summary = "分页获取Feature的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个对象数组，每个对象描述了一个Bundle的详细信息。", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:feature:list')")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public ResponseResult<PageResult<FeatureInfo>> getFeatures(@RequestBody @Validated FeaturePageQueryRequest request) {
        FeaturePageQueryParam featurePageQueryParam = featureWebConverter.req2param(request);
        PageResult<FeatureInfo> featurePageInfo = featureService.getFeaturesForKaraf(featurePageQueryParam);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, featurePageInfo);
    }

    @Operation(summary = "根据某个Feature下的mvnBundleName获取某个Feature下的Bundle的详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "返回一个对象数组，每个对象描述了一个Bundle的详细信息。", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:feature:listBundles')")
    @RequestMapping(path = "/listBundles", method = RequestMethod.POST)
    public ResponseResult<List<BundleInfo>> getBundlesForFeature(@RequestBody List<String> mvnBundleNameList) {
        ArrayList<BundleInfo> listBundles = featureService.getBundlesForFeature(mvnBundleNameList);
        return new ResponseResult<>(200, Constants.DATA_ACQUISITION_SUCCESSFUL, listBundles);
    }

    @Operation(summary = "安装Feature", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "安装成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:feature:install')")
    @RequestMapping(path = "/install", method = RequestMethod.PUT)
    public ResponseResult<Object> installFeatures(@RequestBody List<FeatureInfo> listFeatures) {
        for (FeatureInfo feature : listFeatures) {
            featureService.installFeature(feature.getName());
        }
        return new ResponseResult<>(200, "安装成功");
    }

    @Operation(summary = "卸载Feature", security = {@SecurityRequirement(name = "Authorization")})
    @ApiResponses({@ApiResponse(description = "卸载成功", responseCode = "200"),
            @ApiResponse(description = "JMX异常", responseCode = "500")})
    @PreAuthorize("@AE.hasAuthority('service:feature:uninstall')")
    @RequestMapping(path = "/uninstall", method = RequestMethod.PUT)
    public ResponseResult<Object> uninstallFeatures(@RequestBody List<FeatureInfo> listFeatures) {
        for (FeatureInfo feature : listFeatures) {
            featureService.uninstallFeature(feature.getName());
        }
        return new ResponseResult<>(200, "卸载成功");
    }
}
