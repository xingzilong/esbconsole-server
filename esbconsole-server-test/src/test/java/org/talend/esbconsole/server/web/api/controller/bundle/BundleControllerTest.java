package org.talend.esbconsole.server.web.api.controller.bundle;

import com.alibaba.fastjson2.JSON;
import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.param.BundlePageQueryParam;
import org.talend.esbconsole.server.domain.api.service.BundleService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.bundle.converter.BundleWebConverter;
import org.talend.esbconsole.server.web.api.controller.bundle.request.BundlePageQueryRequest;
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
 * {@link BundleController} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BundleControllerTest {

    private static final String URL = "/api/service/bundle";
    @Mock
    BundleService bundleService;
    @Mock
    BundleWebConverter bundleWebConverter;
    @InjectMocks
    BundleController bundleController;
    MockMvc mvc;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);

        //构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(bundleController).build();
    }

    @Test
    public void getAllBundlesTest() throws Exception {
        List<BundleInfo> bundleInfos = new ArrayList<BundleInfo>();
        BundleInfo bundleInfo = new BundleInfo();
        bundleInfo.setBundleName("test");
        bundleInfo.setState("1");
        bundleInfos.add(bundleInfo);
        when(bundleService.getAllBundlesForKaraf()).thenReturn(bundleInfos);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/listAll"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(bundleService).getAllBundlesForKaraf();
    }

    @Test
    public void getBundlesTest() throws Exception {

        BundlePageQueryRequest request = new BundlePageQueryRequest();
        BundlePageQueryParam bundlePageQueryParam = new BundlePageQueryParam();
        when(bundleWebConverter.req2param(request)).thenReturn(bundlePageQueryParam);

        PageResult<BundleInfo> bundlePageInfo = new PageResult();
        when(bundleService.getBundlesForKaraf(bundlePageQueryParam)).thenReturn(bundlePageInfo);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/list")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(bundleWebConverter).req2param(request);
        verify(bundleService).getBundlesForKaraf(bundlePageQueryParam);
    }

    @Test
    public void startBundlesTest() throws Exception {
        List<BundleInfo> listBundles = new ArrayList<BundleInfo>();
        BundleInfo bundleInfo = new BundleInfo();
        bundleInfo.setBundleName("test");
        bundleInfo.setIdentifier(4564156L);
        listBundles.add(bundleInfo);

        doNothing().when(bundleService).startBundleByID(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.put(URL + "/start")
                        .content(JSON.toJSONString(listBundles))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(bundleService).startBundleByID(Mockito.any());
    }

    @Test
    public void stopBundleTest() throws Exception {
        List<BundleInfo> listBundles = new ArrayList<BundleInfo>();
        BundleInfo bundleInfo = new BundleInfo();
        bundleInfo.setBundleName("test");
        bundleInfo.setIdentifier(4564156L);
        listBundles.add(bundleInfo);

        doNothing().when(bundleService).stopBundleByID(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.put(URL + "/stop")
                        .content(JSON.toJSONString(listBundles))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(bundleService).stopBundleByID(Mockito.any());

    }

}
