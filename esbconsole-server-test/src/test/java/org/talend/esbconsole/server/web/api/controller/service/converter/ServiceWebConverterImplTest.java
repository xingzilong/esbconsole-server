package org.talend.esbconsole.server.web.api.controller.service.converter;

import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.model.TaskTimeConsumptionDTO;
import org.talend.esbconsole.server.web.api.controller.service.request.*;
import org.talend.esbconsole.server.web.api.controller.service.request.APICallAnalysisRequest;
import org.talend.esbconsole.server.web.api.controller.service.vo.ServiceVO;
import org.talend.esbconsole.server.web.api.controller.service.vo.TaskTimeConsumptionVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.talend.esbconsole.server.domain.api.param.APICallAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.APIRunAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.ServiceInstallParam;
import org.talend.esbconsole.server.domain.api.param.ServiceModifyParam;
import org.talend.esbconsole.server.domain.api.param.ServicePageQueryParam;
import org.talend.esbconsole.server.domain.api.param.ServiceStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.ServiceTimedTaskParam;
import org.talend.esbconsole.server.web.api.controller.service.request.APIRunAnalysisRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceInstallRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceModifyRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServicePageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceTimedTaskRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link ServiceWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceWebConverterImplTest {

    @InjectMocks
    private ServiceWebConverterImpl serviceWebConverterImpl;

    @Test
    public void req2param4ServicePageQueryRequestTest() {
        ServicePageQueryRequest request = new ServicePageQueryRequest();
        ServicePageQueryParam servicePageQueryParam = serviceWebConverterImpl.req2param(request);
        assertNotNull(servicePageQueryParam);
        request = null;
        ServicePageQueryParam servicePageQueryParam1 = serviceWebConverterImpl.req2param(request);
        assertNull(servicePageQueryParam1);
    }

    @Test
    public void req2param4ServiceStatusActionRequestTest() {
        ServiceStatusActionRequest request = new ServiceStatusActionRequest();
        ServiceStatusActionParam serviceStatusActionParam = serviceWebConverterImpl.req2param(request);
        assertNotNull(serviceStatusActionParam);
        request = null;
        ServiceStatusActionParam serviceStatusActionParam1 = serviceWebConverterImpl.req2param(request);
        assertNull(serviceStatusActionParam1);
    }

    @Test
    public void req2param4ServiceStatusActionRequestListTest() {
        ArrayList<ServiceStatusActionRequest> serviceStatusActionRequests = new ArrayList<>();
        serviceStatusActionRequests.add(new ServiceStatusActionRequest());
        List<ServiceStatusActionParam> serviceStatusActionParams = serviceWebConverterImpl.req2param(serviceStatusActionRequests);
        assertNotNull(serviceStatusActionParams);
        serviceStatusActionRequests = null;
        List<ServiceStatusActionParam> serviceStatusActionParams1 = serviceWebConverterImpl.req2param(serviceStatusActionRequests);
        assertNull(serviceStatusActionParams1);
    }

    @Test
    public void req2param4ServiceIntsallRequestTest() {
        ServiceInstallRequest request = new ServiceInstallRequest();
        ServiceInstallParam serviceInstallParam = serviceWebConverterImpl.req2param(request);
        assertNotNull(serviceInstallParam);
        request = null;
        ServiceInstallParam serviceInstallParam1 = serviceWebConverterImpl.req2param(request);
        assertNull(serviceInstallParam1);
    }

    @Test
    public void req2param4ServiceModifyRequestTest() {
        ServiceModifyRequest request = new ServiceModifyRequest();
        ServiceModifyParam serviceModifyParam = serviceWebConverterImpl.req2param(request);
        assertNotNull(serviceModifyParam);
        request = null;
        ServiceModifyParam serviceModifyParam1 = serviceWebConverterImpl.req2param(request);
        assertNull(serviceModifyParam1);
    }

    @Test
    public void req2param4APICallAnalysisRequestTest() {
        APICallAnalysisRequest request = new APICallAnalysisRequest();
        APICallAnalysisParam apiCallAnalysisParam = serviceWebConverterImpl.req2param(request);
        assertNotNull(apiCallAnalysisParam);
        request = null;
        APICallAnalysisParam apiCallAnalysisParam1 = serviceWebConverterImpl.req2param(request);
        assertNull(apiCallAnalysisParam1);
    }

    @Test
    public void req2param4APIRunAnalysisRequestTest() {
        APIRunAnalysisRequest request = new APIRunAnalysisRequest();
        APIRunAnalysisParam apiRunAnalysisParam = serviceWebConverterImpl.req2param(request);
        assertNotNull(apiRunAnalysisParam);
        request = null;
        APIRunAnalysisParam apiRunAnalysisParam1 = serviceWebConverterImpl.req2param(request);
        assertNull(apiRunAnalysisParam1);
    }

    @Test
    public void dto2vo4TaskTimeConsumptionDTOListTest() {
        ArrayList<TaskTimeConsumptionDTO> taskTimeConsumptionDTOS = new ArrayList<>();
        taskTimeConsumptionDTOS.add(new TaskTimeConsumptionDTO());
        List<TaskTimeConsumptionVO> taskTimeConsumptionVOS = serviceWebConverterImpl.dto2vo(taskTimeConsumptionDTOS);
        assertNotNull(taskTimeConsumptionVOS);
        taskTimeConsumptionDTOS = null;
        List<TaskTimeConsumptionVO> taskTimeConsumptionVOS1 = serviceWebConverterImpl.dto2vo(taskTimeConsumptionDTOS);
        assertNull(taskTimeConsumptionVOS1);
    }

    @Test
    public void req2param4ServiceTimedTaskRequestTest() {
        ServiceTimedTaskRequest request = new ServiceTimedTaskRequest();
        ServiceTimedTaskParam serviceTimedTaskParam = serviceWebConverterImpl.req2param(request);
        assertNotNull(serviceTimedTaskParam);
        request = null;
        ServiceTimedTaskParam serviceTimedTaskParam1 = serviceWebConverterImpl.req2param(request);
        assertNull(serviceTimedTaskParam1);
    }

    @Test
    public void dto2vo4serviceTest() {
        ArrayList<ServiceDTO> serviceDTOS = new ArrayList<>();
        serviceDTOS.add(new ServiceDTO());
        List<ServiceVO> serviceVOS = serviceWebConverterImpl.dto2vo4service(serviceDTOS);
        assertNotNull(serviceVOS);
        serviceDTOS = null;
        List<ServiceVO> serviceVOS1 = serviceWebConverterImpl.dto2vo4service(serviceDTOS);
        assertNull(serviceVOS1);
    }

    @Test
    public void jsonString2ListTest() {
        String josnString = "[\"aaa\" , \"bbb\"]";
        List<String> list = serviceWebConverterImpl.jsonString2List(josnString);
        assertNotNull(list);
        josnString = null;
        List<String> list1 = serviceWebConverterImpl.jsonString2List(josnString);
        assertNull(list1);
    }
}
