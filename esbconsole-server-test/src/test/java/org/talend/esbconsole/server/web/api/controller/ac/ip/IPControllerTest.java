package org.talend.esbconsole.server.web.api.controller.ac.ip;

import com.alibaba.fastjson2.JSON;
import org.talend.esbconsole.server.domain.api.model.IPControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4IPPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4IPStatusActionParam;
import org.talend.esbconsole.server.domain.api.service.IPControlService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.ac.ip.converter.AC4IPWebConverter;
import org.talend.esbconsole.server.web.api.controller.ac.ip.request.AC4IPPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.ip.request.AC4IPStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.ip.vo.IPControlVO;
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
 * {@link IPController} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/8
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class IPControllerTest {

    private static final String URL = "/api/control/ac/ip";

    @Mock
    IPControlService ipControlService;

    @Mock
    AC4IPWebConverter ac4IPWebConverter;

    @InjectMocks
    IPController iPController;

    MockMvc mvc;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);

        //构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(iPController).build();
    }

    @Test
    public void getIPControlsTest() throws Exception {
        AC4IPPageQueryRequest request = new AC4IPPageQueryRequest();
        request.setStatus("0");
        request.setPageNum(1);
        request.setPageSize(10);

        AC4IPPageQueryParam ac4IPPageQueryParam = new AC4IPPageQueryParam();
        when(ac4IPWebConverter.req2param(request)).thenReturn(ac4IPPageQueryParam);

        PageResult<IPControlModel> ipControlsPageInfo = new PageResult<IPControlModel>();
        ipControlsPageInfo.setTotal(10L);
        when(ipControlService.getIPControls(ac4IPPageQueryParam)).thenReturn(ipControlsPageInfo);

        List<IPControlVO> ipControlVOS = new ArrayList<IPControlVO>();
        IPControlVO ipControlVO = new IPControlVO();
        ipControlVO.setId("54451");
        ipControlVO.setStatus("1");
        ipControlVOS.add(ipControlVO);
        when(ac4IPWebConverter.dto2vo(ipControlsPageInfo.getList())).thenReturn(ipControlVOS);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/list")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());
    }

    @Test
    public void getIPControlByIdTest() throws Exception {
        IPControlModel ipControl = new IPControlModel();
        when(ipControlService.getIPControlById(Mockito.any())).thenReturn(ipControl);

        IPControlVO ipControlVO = new IPControlVO();
        when(ac4IPWebConverter.dto2vo(ipControl)).thenReturn(ipControlVO);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/getIPControl")
                        .param("id", "561")
                )
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ipControlService).getIPControlById(Mockito.any());
        verify(ac4IPWebConverter).dto2vo(ipControl);
    }

    @Test
    public void getServiceNoBindIPControlTest() throws Exception {
        List<ServiceDTO> serviceNoBindIPControl = new ArrayList<ServiceDTO>();
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId("5641");
        serviceDTO.setFileType("jar");
        serviceNoBindIPControl.add(serviceDTO);
        when(ipControlService.getServiceNoBindIPControl()).thenReturn(serviceNoBindIPControl);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/getService"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ipControlService).getServiceNoBindIPControl();
    }

    @Test
    public void addIPControlTest() throws Exception {
        AC4IPStatusActionRequest request = new AC4IPStatusActionRequest();
        request.setId("4561561");

        AC4IPStatusActionParam ac4IPStatusActionParam = new AC4IPStatusActionParam();
        when(ac4IPWebConverter.req2param(request)).thenReturn(ac4IPStatusActionParam);

        doNothing().when(ipControlService).addIPControl(ac4IPStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/add")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4IPWebConverter).req2param(request);
        verify(ipControlService).addIPControl(ac4IPStatusActionParam);
    }

    @Test
    public void modifyIPControlTest() throws Exception {
        AC4IPStatusActionRequest request = new AC4IPStatusActionRequest();
        request.setId("4561561");

        AC4IPStatusActionParam ac4IPStatusActionParam = new AC4IPStatusActionParam();
        when(ac4IPWebConverter.req2param(request)).thenReturn(ac4IPStatusActionParam);

        doNothing().when(ipControlService).modifyIPControls(ac4IPStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.put(URL + "/modify")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4IPWebConverter).req2param(request);
        verify(ipControlService).modifyIPControls(ac4IPStatusActionParam);
    }

    @Test
    public void disableIPControlTest() throws Exception {
        AC4IPStatusActionRequest request = new AC4IPStatusActionRequest();
        request.setId("4561561");

        AC4IPStatusActionParam ac4IPStatusActionParam = new AC4IPStatusActionParam();
        when(ac4IPWebConverter.req2param(request)).thenReturn(ac4IPStatusActionParam);

        doNothing().when(ipControlService).disableIPControl(ac4IPStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.put(URL + "/disable")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4IPWebConverter).req2param(request);
        verify(ipControlService).disableIPControl(ac4IPStatusActionParam);
    }

    @Test
    public void enableIPControlTest() throws Exception {
        AC4IPStatusActionRequest request = new AC4IPStatusActionRequest();
        request.setId("4561561");

        AC4IPStatusActionParam ac4IPStatusActionParam = new AC4IPStatusActionParam();
        when(ac4IPWebConverter.req2param(request)).thenReturn(ac4IPStatusActionParam);

        doNothing().when(ipControlService).enableIPControl(ac4IPStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.put(URL + "/enable")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4IPWebConverter).req2param(request);
        verify(ipControlService).enableIPControl(ac4IPStatusActionParam);
    }

    @Test
    public void removeIPControlTest() throws Exception {
        List<AC4IPStatusActionRequest> request = new ArrayList<>();
        AC4IPStatusActionRequest ac4IPStatusActionRequest = new AC4IPStatusActionRequest();
        ac4IPStatusActionRequest.setId("4561561");
        request.add(ac4IPStatusActionRequest);
        AC4IPStatusActionParam ac4IPStatusActionParam = new AC4IPStatusActionParam();
        when(ac4IPWebConverter.req2param(ac4IPStatusActionRequest)).thenReturn(ac4IPStatusActionParam);

        doNothing().when(ipControlService).removeIPControl(ac4IPStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.delete(URL + "/remove")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4IPWebConverter).req2param(ac4IPStatusActionRequest);
        verify(ipControlService).removeIPControl(ac4IPStatusActionParam);
    }


}
