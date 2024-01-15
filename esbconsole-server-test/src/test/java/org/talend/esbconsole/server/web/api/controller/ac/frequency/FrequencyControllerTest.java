package org.talend.esbconsole.server.web.api.controller.ac.frequency;

import com.alibaba.fastjson2.JSON;
import org.talend.esbconsole.server.domain.api.model.FrequencyControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyStatusActionParam;
import org.talend.esbconsole.server.domain.api.service.FrequencyControlService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.converter.AC4FrequencyWebConverter;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.request.AC4FrequencyPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.request.AC4FrequencyStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.vo.FrequencyControlVO;
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
 * {@link FrequencyController} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/8
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class FrequencyControllerTest {

    private static final String URL = "/api/control/ac/frequency";

    @Mock
    FrequencyControlService frequencyControlService;

    @Mock
    AC4FrequencyWebConverter ac4FrequencyWebConverter;

    @InjectMocks
    FrequencyController frequencyController;

    MockMvc mvc;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);

        //构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(frequencyController).build();
    }

    @Test
    public void getFrequencyControlsTest() throws Exception {
        AC4FrequencyPageQueryRequest request = new AC4FrequencyPageQueryRequest();
        request.setPageNum(1);
        request.setPageSize(10);

        AC4FrequencyPageQueryParam ac4FrequencyPageQueryParam = new AC4FrequencyPageQueryParam();
        when(ac4FrequencyWebConverter.req2param(request)).thenReturn(ac4FrequencyPageQueryParam);

        PageResult<FrequencyControlModel> frequencyControlsPageInfo = new PageResult<FrequencyControlModel>();
        frequencyControlsPageInfo.setTotal(10L);
        when(frequencyControlService.getFrequencyControls(ac4FrequencyPageQueryParam)).thenReturn(frequencyControlsPageInfo);

        List<FrequencyControlVO> frequencyControlVOS = new ArrayList<FrequencyControlVO>();
        FrequencyControlVO frequencyControlVO = new FrequencyControlVO();
        frequencyControlVO.setStatus("1");
        frequencyControlVO.setType("global");
        frequencyControlVO.setThreshold(4L);
        frequencyControlVOS.add(frequencyControlVO);
        when(ac4FrequencyWebConverter.dto2vo(frequencyControlsPageInfo.getList())).thenReturn(frequencyControlVOS);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/list")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

    }

    @Test
    public void getFrequencyControlByIdTest() throws Exception {
        FrequencyControlModel frequencyControl = new FrequencyControlModel();
        when(frequencyControlService.getFrequencyControlById(Mockito.any())).thenReturn(frequencyControl);

        FrequencyControlVO frequencyControlVO = new FrequencyControlVO();
        when(ac4FrequencyWebConverter.dto2vo(frequencyControl)).thenReturn(frequencyControlVO);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/getFrequencyControl")
                        .param("id", "4566")
                )
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(frequencyControlService).getFrequencyControlById(Mockito.any());
        verify(ac4FrequencyWebConverter).dto2vo(frequencyControl);
    }

    @Test
    public void getServiceNoBindFrequencyControlTest() throws Exception {
        List<ServiceDTO> serviceNoBindFrequencyControl = new ArrayList<ServiceDTO>();
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setFileType("kar");
        serviceDTO.setServiceType("restful_ws");
        serviceNoBindFrequencyControl.add(serviceDTO);
        when(frequencyControlService.getServiceNoBindFrequencyControl()).thenReturn(serviceNoBindFrequencyControl);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/getService"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(frequencyControlService).getServiceNoBindFrequencyControl();
    }

    @Test
    public void addFrequencyControlTest() throws Exception {
        AC4FrequencyStatusActionRequest request = new AC4FrequencyStatusActionRequest();
        request.setStatus("1");
        request.setType("global");
        request.setId("5456");

        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();
        when(ac4FrequencyWebConverter.req2param(request)).thenReturn(ac4FrequencyStatusActionParam);

        doNothing().when(frequencyControlService).addFrequencyControl(ac4FrequencyStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/add")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4FrequencyWebConverter).req2param(request);
        verify(frequencyControlService).addFrequencyControl(ac4FrequencyStatusActionParam);
    }

    @Test
    public void modifyFrequencyControlTest() throws Exception {
        AC4FrequencyStatusActionRequest request = new AC4FrequencyStatusActionRequest();
        request.setStatus("1");
        request.setType("global");
        request.setId("5456");

        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();
        when(ac4FrequencyWebConverter.req2param(request)).thenReturn(ac4FrequencyStatusActionParam);

        doNothing().when(frequencyControlService).modifyFrequencyControls(ac4FrequencyStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.put(URL + "/modify")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4FrequencyWebConverter).req2param(request);
        verify(frequencyControlService).modifyFrequencyControls(ac4FrequencyStatusActionParam);

    }

    @Test
    public void disableFrequencyControlTest() throws Exception {
        AC4FrequencyStatusActionRequest request = new AC4FrequencyStatusActionRequest();
        request.setStatus("1");
        request.setType("global");
        request.setId("5456");

        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();
        when(ac4FrequencyWebConverter.req2param(request)).thenReturn(ac4FrequencyStatusActionParam);

        doNothing().when(frequencyControlService).disableFrequencyControl(ac4FrequencyStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.put(URL + "/disable")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4FrequencyWebConverter).req2param(request);
        verify(frequencyControlService).disableFrequencyControl(ac4FrequencyStatusActionParam);
    }

    @Test
    public void enableFrequencyControlTest() throws Exception {
        AC4FrequencyStatusActionRequest request = new AC4FrequencyStatusActionRequest();
        request.setStatus("1");
        request.setType("global");
        request.setId("5456");

        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();
        when(ac4FrequencyWebConverter.req2param(request)).thenReturn(ac4FrequencyStatusActionParam);

        doNothing().when(frequencyControlService).enableFrequencyControl(ac4FrequencyStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.put(URL + "/enable")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4FrequencyWebConverter).req2param(request);
        verify(frequencyControlService).enableFrequencyControl(ac4FrequencyStatusActionParam);
    }

    @Test
    public void removeFrequencyControlTest() throws Exception {
        List<AC4FrequencyStatusActionRequest> request = new ArrayList<>();
        AC4FrequencyStatusActionRequest ac4FrequencyStatusActionRequest = new AC4FrequencyStatusActionRequest();
        ac4FrequencyStatusActionRequest.setStatus("1");
        ac4FrequencyStatusActionRequest.setType("global");
        ac4FrequencyStatusActionRequest.setId("5456");
        request.add(ac4FrequencyStatusActionRequest);
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();
        when(ac4FrequencyWebConverter.req2param(ac4FrequencyStatusActionRequest)).thenReturn(ac4FrequencyStatusActionParam);

        doNothing().when(frequencyControlService).removeFrequencyControl(ac4FrequencyStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.delete(URL + "/remove")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(ac4FrequencyWebConverter).req2param(ac4FrequencyStatusActionRequest);
        verify(frequencyControlService).removeFrequencyControl(ac4FrequencyStatusActionParam);
    }

}
