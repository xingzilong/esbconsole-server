package org.talend.esbconsole.server.web.api.controller.servicelog;

import com.alibaba.fastjson2.JSONObject;
import org.talend.esbconsole.server.domain.api.model.ServiceLogDTO;
import org.talend.esbconsole.server.domain.api.model.message.HttpMessage;
import org.talend.esbconsole.server.domain.api.param.ServiceLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.service.ServiceLogService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.servicelog.converter.ServiceLogWebConverter;
import org.talend.esbconsole.server.web.api.controller.servicelog.request.ServiceLogPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.servicelog.vo.ServiceLogVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link ServiceLogController} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class ServiceLogControllerTest {

    private final String PATH = "/api/monitor/serviceLog";
    @Mock
    private ServiceLogService serviceLogService;
    @Mock
    private ServiceLogWebConverter serviceLogWebConverter;
    @InjectMocks
    private ServiceLogController controller;
    private MockMvc mvc;

    @Before
    public void setup() {

        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getServiceLogsTest() throws Exception {

        ServiceLogPageQueryRequest request = Mockito.mock(ServiceLogPageQueryRequest.class);
        ServiceLogPageQueryParam param = Mockito.mock(ServiceLogPageQueryParam.class);
        Mockito.doReturn(param).when(serviceLogWebConverter).req2param(Mockito.any(ServiceLogPageQueryRequest.class));
        PageResult<ServiceLogDTO> result = Mockito.mock(PageResult.class);
        Mockito.doReturn(result).when(serviceLogService).getServiceLogs(Mockito.any(ServiceLogPageQueryParam.class));
        ServiceLogVO vo = Mockito.mock(ServiceLogVO.class);
        Mockito.doReturn(new ArrayList<ServiceLogDTO>()).when(result).getList();
        Mockito.doReturn(Arrays.asList(vo)).when(serviceLogWebConverter).dto2vo(Mockito.anyList());
        this.mvc.perform(post(PATH + "/list")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceLogService).getServiceLogs(Mockito.any(ServiceLogPageQueryParam.class));
        Mockito.verify(serviceLogWebConverter).req2param(Mockito.any(ServiceLogPageQueryRequest.class));
    }

    @Test
    public void getRequestMessageTest() throws Exception {
        HttpMessage message = Mockito.mock(HttpMessage.class);
        Mockito.doReturn(message).when(serviceLogService).getRequestMessage(Mockito.anyString());
        this.mvc.perform(get(PATH + "/getRequestMessage")
                        .param("flowId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceLogService).getRequestMessage(Mockito.anyString());
    }

    @Test
    public void getResponseMessageTest() throws Exception {
        HttpMessage message = Mockito.mock(HttpMessage.class);
        Mockito.doReturn(message).when(serviceLogService).getResponseMessage(Mockito.anyString());
        this.mvc.perform(get(PATH + "/getResponseMessage")
                        .param("flowId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceLogService).getResponseMessage(Mockito.anyString());
    }

}
