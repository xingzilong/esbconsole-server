package org.talend.esbconsole.server.web.api.controller.feature;

import com.alibaba.fastjson2.JSON;
import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.model.FeatureInfo;
import org.talend.esbconsole.server.domain.api.param.FeaturePageQueryParam;
import org.talend.esbconsole.server.domain.api.service.BundleService;
import org.talend.esbconsole.server.domain.api.service.FeatureService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.feature.converter.FeatureWebConverter;
import org.talend.esbconsole.server.web.api.controller.feature.request.FeaturePageQueryRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link FeatureController} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class FeatureControllerTest {

    private static final String URL = "/api/service/feature";
    @Mock
    FeatureService featureService;
    @Mock
    BundleService bundleService;
    @Mock
    FeatureWebConverter featureWebConverter;
    @InjectMocks
    FeatureController featureController;
    MockMvc mvc;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);

        //构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(featureController).build();
    }

    @Test
    public void getAllFeaturesTest() throws Exception {
        List<FeatureInfo> allFeatureList = new ArrayList<FeatureInfo>();
        FeatureInfo featureInfo = new FeatureInfo();
        featureInfo.setBlacklisted(false);
        featureInfo.setInstalled(false);
        allFeatureList.add(featureInfo);

        when(featureService.getAllFeaturesForKaraf()).thenReturn(allFeatureList);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/listAll"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(featureService).getAllFeaturesForKaraf();
    }

    @Test
    public void getFeaturesTest() throws Exception {
        FeaturePageQueryRequest request = new FeaturePageQueryRequest();
        request.setInstalled(true);
        request.setName("test");
        request.setPageNum(1);
        request.setPageSize(10);
        request.setVersion("1");

        FeaturePageQueryParam featurePageQueryParam = new FeaturePageQueryParam();
        when(featureWebConverter.req2param(Mockito.any())).thenReturn(featurePageQueryParam);

        PageResult<FeatureInfo> featurePageInfo = new PageResult();
        when(featureService.getFeaturesForKaraf(Mockito.any())).thenReturn(featurePageInfo);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/list")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(featureWebConverter).req2param(Mockito.any());
        verify(featureService).getFeaturesForKaraf(Mockito.any());
    }

    @Test
    public void installFeaturesTest() throws Exception {

        List<FeatureInfo> listFeatures = new ArrayList<FeatureInfo>();
        FeatureInfo featureInfo = new FeatureInfo();
        featureInfo.setBlacklisted(true);
        featureInfo.setRequired(true);
        featureInfo.setName("test");
        listFeatures.add(featureInfo);

        doNothing().when(featureService).installFeature(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.put(URL + "/install")
                        .content(JSON.toJSONString(listFeatures))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(featureService).installFeature(Mockito.any());
    }

    @Test
    public void getBundlesForFeatureTest() throws Exception {
        List<String> mvnBundleNameList = new ArrayList<String>();
        mvnBundleNameList.add("");

        ArrayList<BundleInfo> listBundles = new ArrayList<BundleInfo>();
        BundleInfo bundleInfo = new BundleInfo();
        listBundles.add(bundleInfo);

        when(featureService.getBundlesForFeature(Mockito.any())).thenReturn(listBundles);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/listBundles")
                        .content(JSON.toJSONString(mvnBundleNameList))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(featureService).getBundlesForFeature(Mockito.any());
    }

    @Test
    public void uninstallFeaturesTest() throws Exception {
        List<FeatureInfo> listFeatures = new ArrayList<FeatureInfo>();
        FeatureInfo featureInfo = new FeatureInfo();
        featureInfo.setBlacklisted(true);
        featureInfo.setRequired(true);
        featureInfo.setName("test");
        listFeatures.add(featureInfo);

        doNothing().when(featureService).uninstallFeature(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.put(URL + "/uninstall")
                        .content(JSON.toJSONString(listFeatures))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(featureService).uninstallFeature(Mockito.any());
    }
}
