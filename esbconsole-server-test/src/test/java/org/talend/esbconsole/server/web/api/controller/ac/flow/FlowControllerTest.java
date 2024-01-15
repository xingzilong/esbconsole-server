package org.talend.esbconsole.server.web.api.controller.ac.flow;

import com.alibaba.fastjson2.JSON;
import org.talend.esbconsole.server.domain.api.model.FlowControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FlowPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FlowStatusActionParam;
import org.talend.esbconsole.server.domain.api.service.FlowControlService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.ac.flow.converter.AC4FlowWebConverter;
import org.talend.esbconsole.server.web.api.controller.ac.flow.request.AC4FlowPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.flow.request.AC4FlowStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.flow.vo.FlowControlVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
 * {@link FlowController} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/8
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class FlowControllerTest {

    private static final String URL = "/api/control/ac/flow";

    @Mock
    FlowControlService flowControlService;

    @Mock
    AC4FlowWebConverter ac4FlowWebConverter;

    @InjectMocks
    FlowController flowController;

    MockMvc mvc;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);

        //构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(flowController).build();
    }

    @Test
    public void getFlowControlsTest() throws Exception {
        AC4FlowPageQueryRequest request = new AC4FlowPageQueryRequest();
        request.setPageNum(1);
        request.setPageSize(10);
        AC4FlowPageQueryParam ac4FlowPageQueryParam = new AC4FlowPageQueryParam();

        when(ac4FlowWebConverter.req2param(request)).thenReturn(ac4FlowPageQueryParam);

        PageResult<FlowControlModel> flowControlsPageInfo = new PageResult<FlowControlModel>();
        flowControlsPageInfo.setTotal(10L);
        when(flowControlService.getFlowControls(ac4FlowPageQueryParam)).thenReturn(flowControlsPageInfo);
        List<FlowControlVO> flowControlVOS = new ArrayList<FlowControlVO>();
        FlowControlVO flowControlVO = new FlowControlVO();
        flowControlVO.setType("single");
        flowControlVOS.add(flowControlVO);
        when(ac4FlowWebConverter.dto2vo(flowControlsPageInfo.getList())).thenReturn(flowControlVOS);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/list")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4FlowWebConverter).req2param(request);
        verify(flowControlService).getFlowControls(ac4FlowPageQueryParam);
        verify(ac4FlowWebConverter).dto2vo(flowControlsPageInfo.getList());
    }

    @Test
    public void getFlowControlByIdTest() throws Exception {
        String id = "4156415";

        FlowControlModel flowControl = new FlowControlModel();
        flowControl.setType("single");
        flowControl.setId(id);
        flowControl.setStatus("0");
        when(flowControlService.getFlowControlById(id)).thenReturn(flowControl);

        FlowControlVO flowControlVO = new FlowControlVO();
        flowControlVO.setId(id);
        flowControlVO.setStatus("0");
        when(ac4FlowWebConverter.dto2vo(flowControl)).thenReturn(flowControlVO);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/getFlowControl")
                        .param("id", id))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(flowControlService).getFlowControlById(id);
        verify(ac4FlowWebConverter).dto2vo(flowControl);
    }

    @Test
    public void getServiceNoBindFlowControlTest() throws Exception {
        List<ServiceDTO> serviceNoBindFlowControl = new ArrayList<ServiceDTO>();
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setServiceType("restful_ws");
        serviceDTO.setFileType("kar");
        serviceNoBindFlowControl.add(serviceDTO);

        when(flowControlService.getServiceNoBindFlowControl()).thenReturn(serviceNoBindFlowControl);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/getService"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(flowControlService).getServiceNoBindFlowControl();
    }

    @Test
    public void addFlowControlTest() throws Exception {
        AC4FlowStatusActionRequest request = new AC4FlowStatusActionRequest();
        request.setId("456416516L");
        request.setType("single");
        request.setStatus("1");

        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();

        when(ac4FlowWebConverter.req2param(request)).thenReturn(ac4FlowStatusActionParam);
        doNothing().when(flowControlService).addFlowControl(ac4FlowStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/add")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4FlowWebConverter).req2param(request);
        verify(flowControlService).addFlowControl(ac4FlowStatusActionParam);

    }

    @Test
    public void modifyFlowControlTest() throws Exception {
        AC4FlowStatusActionRequest request = new AC4FlowStatusActionRequest();
        request.setId("654564156L");
        request.setStatus("0");

        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();
        ac4FlowStatusActionParam.setStatus("0");
        ac4FlowStatusActionParam.setId("654564156L");
        when(ac4FlowWebConverter.req2param(request)).thenReturn(ac4FlowStatusActionParam);

        doNothing().when(flowControlService).modifyFlowControls(ac4FlowStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.put(URL + "/modify")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4FlowWebConverter).req2param(request);
        verify(flowControlService).modifyFlowControls(ac4FlowStatusActionParam);
    }

    @Test
    public void disableFlowControlTest() throws Exception {
        AC4FlowStatusActionRequest request = new AC4FlowStatusActionRequest();
        request.setId("654564156L");
        request.setStatus("0");

        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();
        ac4FlowStatusActionParam.setStatus("0");
        ac4FlowStatusActionParam.setId("654564156L");

        when(ac4FlowWebConverter.req2param(request)).thenReturn(ac4FlowStatusActionParam);

        doNothing().when(flowControlService).disableFlowControl(ac4FlowStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.put(URL + "/disable")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4FlowWebConverter).req2param(request);
        verify(flowControlService).disableFlowControl(ac4FlowStatusActionParam);
    }

    @Test
    public void enableFlowControlTest() throws Exception {
        AC4FlowStatusActionRequest request = new AC4FlowStatusActionRequest();
        request.setId("654564156L");
        request.setStatus("0");

        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();
        ac4FlowStatusActionParam.setStatus("0");
        ac4FlowStatusActionParam.setId("654564156L");

        when(ac4FlowWebConverter.req2param(request)).thenReturn(ac4FlowStatusActionParam);

        doNothing().when(flowControlService).enableFlowControl(ac4FlowStatusActionParam);
        mvc.perform(MockMvcRequestBuilders.put(URL + "/enable")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4FlowWebConverter).req2param(request);
        verify(flowControlService).enableFlowControl(ac4FlowStatusActionParam);
    }

    @Test
    public void removeFlowControlTest() throws Exception {
        List<AC4FlowStatusActionRequest> request = new ArrayList<>();
        AC4FlowStatusActionRequest ac4FlowStatusActionRequest = new AC4FlowStatusActionRequest();
        ac4FlowStatusActionRequest.setId("654564156L");
        ac4FlowStatusActionRequest.setStatus("0");
        request.add(ac4FlowStatusActionRequest);
        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();
        ac4FlowStatusActionParam.setStatus("0");
        ac4FlowStatusActionParam.setId("654564156L");

        when(ac4FlowWebConverter.req2param(ac4FlowStatusActionRequest)).thenReturn(ac4FlowStatusActionParam);

        doNothing().when(flowControlService).removeFlowControl(ac4FlowStatusActionParam);

        mvc.perform(MockMvcRequestBuilders.delete(URL + "/remove")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(ac4FlowWebConverter).req2param(ac4FlowStatusActionRequest);
        verify(flowControlService).removeFlowControl(ac4FlowStatusActionParam);
    }


}
