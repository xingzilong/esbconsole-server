package org.talend.esbconsole.server.web.api.controller.service;

import com.alibaba.fastjson2.JSONObject;
import org.talend.esbconsole.server.domain.api.model.APICallAnalysisTableDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTop5DataDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.model.TaskExecutionDTO;
import org.talend.esbconsole.server.domain.api.model.TaskTimeConsumptionDTO;
import org.talend.esbconsole.server.domain.api.param.APICallAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.APIRunAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.ServiceModifyParam;
import org.talend.esbconsole.server.domain.api.param.ServicePageQueryParam;
import org.talend.esbconsole.server.domain.api.param.ServiceStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.ServiceTimedTaskParam;
import org.talend.esbconsole.server.domain.api.service.ServiceService;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.service.converter.ServiceWebConverter;
import org.talend.esbconsole.server.web.api.controller.service.request.*;
import org.talend.esbconsole.server.web.api.controller.service.request.APICallAnalysisRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.APIRunAnalysisRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceModifyRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServicePageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceTimedTaskRequest;
import org.talend.esbconsole.server.web.api.controller.service.vo.TaskTimeConsumptionVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.ap.spi.BuilderInfo;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link ServiceController} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class ServiceControllerTest {

    private final String PATH = "/api/service/service";
    @Mock
    private ServiceService serviceService;
    @Mock
    private ServiceWebConverter serviceWebConverter;
    @InjectMocks
    private ServiceController controller;
    private MockMvc mvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllServicesTest() throws Exception {

        ServiceDTO dto = Mockito.mock(ServiceDTO.class);
        Mockito.doReturn(Arrays.asList(dto)).when(serviceService).getAllServices();
        this.mvc.perform(get(PATH + "/listAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getAllServices();
    }

    @Test
    public void getServiceByIdTest() throws Exception {
        ServiceDTO dto = Mockito.mock(ServiceDTO.class);
        Mockito.doReturn(dto).when(serviceService).getServiceById(Mockito.anyString());
        this.mvc.perform(get(PATH + "/getServiceById")
                        .param("id", "456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getServiceById(Mockito.anyString());
    }

    @Test
    public void getServicesTest() throws Exception {
        ServicePageQueryRequest request = Mockito.mock(ServicePageQueryRequest.class);
        ServicePageQueryParam param = Mockito.mock(ServicePageQueryParam.class);
        PageResult<ServiceDTO> result = Mockito.mock(PageResult.class);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(ServicePageQueryRequest.class));
        Mockito.doReturn(result).when(serviceService).getServices(Mockito.any(ServicePageQueryParam.class));
        this.mvc.perform(post(PATH + "/list")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getServices(Mockito.any(ServicePageQueryParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(ServicePageQueryRequest.class));
    }

    @Test
    public void getBundlesForServiceTest() throws Exception {
        ServiceStatusActionRequest request = new ServiceStatusActionRequest();
        request.setFileName("test");
        request.setFileType("kar");
        request.setId("456");
        ServiceStatusActionParam param = Mockito.mock(ServiceStatusActionParam.class);
        BuilderInfo result = Mockito.mock(BuilderInfo.class);
        ArrayList<BuilderInfo> info = new ArrayList<BuilderInfo>();
        info.add(result);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(ServiceStatusActionRequest.class));
        Mockito.doReturn(info).when(serviceService).getBundlesForService(Mockito.any(ServiceStatusActionParam.class));
        this.mvc.perform(post(PATH + "/listBundles")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getBundlesForService(Mockito.any(ServiceStatusActionParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(ServiceStatusActionRequest.class));
    }

    @Test
    public void installServiceTest() throws Exception {
        int status = controller.installService(null, null).getCode();
        assertEquals(200, status);
//		ServiceIntsallRequest request = new ServiceIntsallRequest();
//		request.setName("test");
//		request.setBusinessSystem("esb");
//		request.setServiceType("soap_ws");
//		request.setResponsiblePerson("txx");
//		ServiceInstallParam param = Mockito.mock(ServiceInstallParam.class);
//		MockMultipartFile file = new MockMultipartFile("file","sawagger.txt","text/plain","rest".getBytes());
////		Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(ServiceIntsallRequest.class));
////		Mockito.doNothing().when(serviceService).installService(Mockito.any(MultipartFile.class),Mockito.any(ServiceInstallParam.class));	
//				this.mvc.perform(MockMvcRequestBuilders.multipart(PATH+"/install")
//				.file(file)
////				.content(JSONObject.toJSONString(request))
////				.file("service", JSONObject.toJSONString(request).getBytes())
//				
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				)
//		.andExpect(status().isOk())
//		.andDo(print());
    }

    @Test
    public void modifyServiceTest() throws Exception {
        ServiceModifyRequest request = new ServiceModifyRequest();
        request.setId("4567");
        request.setName("test");
        request.setBusinessSystemId("esb");
        request.setResponsiblePerson("test");
        ServiceModifyParam param = Mockito.mock(ServiceModifyParam.class);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(ServiceModifyRequest.class));
        Mockito.doNothing().when(serviceService).modifyService(Mockito.any(ServiceModifyParam.class));
        this.mvc.perform(post(PATH + "/modify")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).modifyService(Mockito.any(ServiceModifyParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(ServiceModifyRequest.class));
    }

    @Test
    public void uninstallServicesTest() throws Exception {
        ServiceStatusActionRequest request = Mockito.mock(ServiceStatusActionRequest.class);
        ServiceStatusActionParam param = Mockito.mock(ServiceStatusActionParam.class);
        Mockito.doReturn(Arrays.asList(param)).when(serviceWebConverter).req2param(Mockito.anyList());
        Mockito.doNothing().when(serviceService).unInstallService(Mockito.any(ServiceStatusActionParam.class));
        this.mvc.perform(delete(PATH + "/uninstall")
                        .content(JSONObject.toJSONString(Arrays.asList(request)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).unInstallService(Mockito.any(ServiceStatusActionParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.anyList());
    }

    @Test
    public void startServicesTest() throws Exception {
        ServiceStatusActionRequest request = Mockito.mock(ServiceStatusActionRequest.class);
        ServiceStatusActionParam param = Mockito.mock(ServiceStatusActionParam.class);
        Mockito.doReturn(Arrays.asList(param)).when(serviceWebConverter).req2param(Mockito.anyList());
        Mockito.doNothing().when(serviceService).startService(Mockito.any(ServiceStatusActionParam.class));
        this.mvc.perform(put(PATH + "/start")
                        .content(JSONObject.toJSONString(Arrays.asList(request)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).startService(Mockito.any(ServiceStatusActionParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.anyList());
    }

    @Test
    public void stopServicesTest() throws Exception {
        ServiceStatusActionRequest request = Mockito.mock(ServiceStatusActionRequest.class);
        ServiceStatusActionParam param = Mockito.mock(ServiceStatusActionParam.class);
        Mockito.doReturn(Arrays.asList(param)).when(serviceWebConverter).req2param(Mockito.anyList());
        Mockito.doNothing().when(serviceService).stopService(Mockito.any(ServiceStatusActionParam.class));
        this.mvc.perform(put(PATH + "/stop")
                        .content(JSONObject.toJSONString(Arrays.asList(request)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).stopService(Mockito.any(ServiceStatusActionParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.anyList());
    }

    @Test
    public void getCallTotalTop5ChartTest() throws Exception {
        APICallAnalysisRequest request = new APICallAnalysisRequest();
        request.setId("456789");
        request.setConsumerSystem("esb");
        TimeInterval time = new TimeInterval();
        time.setEndTime("2021-10-05");
        time.setStartTime("2021-09-16");
        request.setTimeInterval(time);
        request.setPageNum(1);
        request.setPageSize(10);
        APICallAnalysisParam param = Mockito.mock(APICallAnalysisParam.class);
        APICallTop5DataDTO dto = Mockito.mock(APICallTop5DataDTO.class);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(APICallAnalysisRequest.class));
        Mockito.doReturn(Arrays.asList(dto)).when(serviceService).getCallTotalTop5Chart(Mockito.any(APICallAnalysisParam.class));
        this.mvc.perform(post(PATH + "/callAnalysis/getCallTotalTop5Chart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getCallTotalTop5Chart(Mockito.any(APICallAnalysisParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(APICallAnalysisRequest.class));
    }

    @Test
    public void getFailureTotalTop5ChartTest() throws Exception {
        APICallAnalysisRequest request = new APICallAnalysisRequest();
        request.setId("456789");
        request.setConsumerSystem("esb");
        TimeInterval time = new TimeInterval();
        time.setEndTime("2021-10-05");
        time.setStartTime("2021-09-16");
        request.setTimeInterval(time);
        request.setPageNum(1);
        request.setPageSize(10);
        APICallAnalysisParam param = Mockito.mock(APICallAnalysisParam.class);
        APICallTop5DataDTO dto = Mockito.mock(APICallTop5DataDTO.class);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(APICallAnalysisRequest.class));
        Mockito.doReturn(Arrays.asList(dto)).when(serviceService).getFailureTotalTop5Chart(Mockito.any(APICallAnalysisParam.class));
        this.mvc.perform(post(PATH + "/callAnalysis/getFailureTotalTop5Chart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getFailureTotalTop5Chart(Mockito.any(APICallAnalysisParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(APICallAnalysisRequest.class));
    }

    @Test
    public void getAPICallAnalysisTest() throws Exception {
        APICallAnalysisRequest request = new APICallAnalysisRequest();
        request.setId("456789");
        request.setConsumerSystem("esb");
        TimeInterval time = new TimeInterval();
        time.setEndTime("2021-10-05");
        time.setStartTime("2021-09-16");
        request.setTimeInterval(time);
        request.setPageNum(1);
        request.setPageSize(10);
        APICallAnalysisParam param = Mockito.mock(APICallAnalysisParam.class);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(APICallAnalysisRequest.class));
        Mockito.doReturn(new PageResult<APICallAnalysisTableDTO>()).when(serviceService).getAPICallAnalysis(Mockito.any(APICallAnalysisParam.class));
        this.mvc.perform(post(PATH + "/callAnalysis/getAPICallAnalysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getAPICallAnalysis(Mockito.any(APICallAnalysisParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(APICallAnalysisRequest.class));
    }

    @Test
    public void getAPICallTotaleAnalysisTest() throws Exception {
        APIRunAnalysisRequest request = new APIRunAnalysisRequest();
        request.setId("456789");
        TimeInterval time = new TimeInterval();
        time.setEndTime("2021-10-05");
        time.setStartTime("2021-09-16");
        request.setTimeInterval(time);
        APIRunAnalysisParam param = Mockito.mock(APIRunAnalysisParam.class);
        APICallTotalAnalysisDTO dto = Mockito.mock(APICallTotalAnalysisDTO.class);
        Map<String, List<APICallTotalAnalysisDTO>> result = new HashMap<String, List<APICallTotalAnalysisDTO>>() {{
            put("test", Arrays.asList(dto));
        }};
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(APIRunAnalysisRequest.class));
        Mockito.doReturn(result).when(serviceService).getAPICallTotaleAnalysis(Mockito.any(APIRunAnalysisParam.class));
        this.mvc.perform(post(PATH + "/runAnalysis/getAPICallTotaleAnalysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getAPICallTotaleAnalysis(Mockito.any(APIRunAnalysisParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(APIRunAnalysisRequest.class));
    }

    @Test
    public void getAPIResponseTimeAnalysisTest() throws Exception {
        APIRunAnalysisRequest request = new APIRunAnalysisRequest();
        request.setId("456789");
        TimeInterval time = new TimeInterval();
        time.setEndTime("2021-10-05");
        time.setStartTime("2021-09-16");
        request.setTimeInterval(time);
        APIRunAnalysisParam param = Mockito.mock(APIRunAnalysisParam.class);
        APICallTotalAnalysisDTO dto = Mockito.mock(APICallTotalAnalysisDTO.class);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(APIRunAnalysisRequest.class));
        Mockito.doReturn(Arrays.asList(dto)).when(serviceService).getAPIResponseTimeAnalysis(Mockito.any(APIRunAnalysisParam.class));
        this.mvc.perform(post(PATH + "/runAnalysis/getAPIResponseTimeAnalysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getAPIResponseTimeAnalysis(Mockito.any(APIRunAnalysisParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(APIRunAnalysisRequest.class));
    }

    @Test
    public void getAPIMessageSizeAnalysisTest() throws Exception {
        APIRunAnalysisRequest request = new APIRunAnalysisRequest();
        request.setId("456789");
        TimeInterval time = new TimeInterval();
        time.setEndTime("2021-10-05");
        time.setStartTime("2021-09-16");
        request.setTimeInterval(time);
        APIRunAnalysisParam param = Mockito.mock(APIRunAnalysisParam.class);
        APICallTotalAnalysisDTO dto = Mockito.mock(APICallTotalAnalysisDTO.class);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(APIRunAnalysisRequest.class));
        Mockito.doReturn(Arrays.asList(dto)).when(serviceService).getAPIMessageSizeAnalysis(Mockito.any(APIRunAnalysisParam.class));
        this.mvc.perform(post(PATH + "/runAnalysis/getAPIMessageSizeAnalysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getAPIMessageSizeAnalysis(Mockito.any(APIRunAnalysisParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(APIRunAnalysisRequest.class));
    }

    @Test
    public void getTaskConsumptionTest() throws Exception {
        ServiceTimedTaskRequest request = new ServiceTimedTaskRequest();
        request.setId("456789");
        TimeInterval time = new TimeInterval();
        time.setEndTime("2021-10-05");
        time.setStartTime("2021-09-16");
        request.setTimeInterval(time);
        request.setPageNum(1);
        request.setPageSize(10);
        ServiceTimedTaskParam param = Mockito.mock(ServiceTimedTaskParam.class);
        TaskExecutionDTO dto = Mockito.mock(TaskExecutionDTO.class);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(ServiceTimedTaskRequest.class));
        Mockito.doReturn(Arrays.asList(dto)).when(serviceService).getTaskExecution(Mockito.any(ServiceTimedTaskParam.class));
        this.mvc.perform(post(PATH + "/timedAnalysis/getTaskExectutionChart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getTaskExecution(Mockito.any(ServiceTimedTaskParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(ServiceTimedTaskRequest.class));
    }

    @Test
    public void getTaskTimeConsumptionTest() throws Exception {
        ServiceTimedTaskRequest request = new ServiceTimedTaskRequest();
        request.setId("456789");
        TimeInterval time = new TimeInterval();
        time.setEndTime("2021-10-05");
        time.setStartTime("2021-09-16");
        request.setTimeInterval(time);
        request.setPageNum(1);
        request.setPageSize(10);
        ServiceTimedTaskParam param = Mockito.mock(ServiceTimedTaskParam.class);
        TaskTimeConsumptionDTO dto = Mockito.mock(TaskTimeConsumptionDTO.class);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(ServiceTimedTaskRequest.class));
        Mockito.doReturn(Arrays.asList(dto)).when(serviceService).getTaskConsumption(Mockito.any(ServiceTimedTaskParam.class));
        Mockito.doReturn(Arrays.asList(new TaskTimeConsumptionVO())).when(serviceWebConverter).dto2vo(Mockito.anyList());
        this.mvc.perform(post(PATH + "/timedAnalysis/getTaskTimeConsumptionChart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getTaskConsumption(Mockito.any(ServiceTimedTaskParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(ServiceTimedTaskRequest.class));
        Mockito.verify(serviceWebConverter).dto2vo(Mockito.anyList());
    }

    @Test
    public void getTaskTimeTableTest() throws Exception {
        ServiceTimedTaskRequest request = new ServiceTimedTaskRequest();
        request.setId("456789");
        TimeInterval time = new TimeInterval();
        time.setEndTime("2021-10-05");
        time.setStartTime("2021-09-16");
        request.setTimeInterval(time);
        request.setPageNum(1);
        request.setPageSize(10);
        ServiceTimedTaskParam param = Mockito.mock(ServiceTimedTaskParam.class);
        Mockito.doReturn(param).when(serviceWebConverter).req2param(Mockito.any(ServiceTimedTaskRequest.class));
        Mockito.doReturn(PageResult.of()).when(serviceService).getTimedTaskTable(Mockito.any(ServiceTimedTaskParam.class));
        this.mvc.perform(post(PATH + "/timedAnalysis/getTable")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(serviceService).getTimedTaskTable(Mockito.any(ServiceTimedTaskParam.class));
        Mockito.verify(serviceWebConverter).req2param(Mockito.any(ServiceTimedTaskRequest.class));
    }


}
