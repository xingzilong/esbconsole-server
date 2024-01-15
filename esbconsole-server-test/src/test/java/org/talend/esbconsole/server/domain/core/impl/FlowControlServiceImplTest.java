package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.FlowControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FlowPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FlowStatusActionParam;
import org.talend.esbconsole.server.domain.api.query.AC4FlowPageQuery;
import org.talend.esbconsole.server.domain.core.converter.ServiceConverter;
import org.talend.esbconsole.server.domain.core.converter.query.AC4FlowQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.domain.repository.mapper.AccessControlFlowDAO;
import org.talend.esbconsole.server.tools.base.exception.HTTPRequestFailedException;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * {@link FlowControlServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class FlowControlServiceImplTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    AccessControlFlowDAO accessControlFlowDAO;

    @Mock
    AC4FlowQueryConverter ac4FlowQueryConverter;

    @Mock
    ServiceConverter serviceConverter;

    @InjectMocks
    FlowControlServiceImpl flowControlServiceImpl;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getFlowControlsTest() {
        AC4FlowPageQueryParam ac4FlowPageQueryParam = new AC4FlowPageQueryParam();
        ac4FlowPageQueryParam.setPageNum(1);
        ac4FlowPageQueryParam.setPageSize(10);

        AC4FlowPageQuery ac4FlowPageQuery = new AC4FlowPageQuery();
        when(ac4FlowQueryConverter.param2query(Mockito.any())).thenReturn(ac4FlowPageQuery);

        List<FlowControlModel> listACFlow = new ArrayList<FlowControlModel>();
        when(accessControlFlowDAO.getAccessControlFlowByConditions(Mockito.any())).thenReturn(listACFlow);

        flowControlServiceImpl.getFlowControls(ac4FlowPageQueryParam);

        verify(ac4FlowQueryConverter).param2query(Mockito.any());
        verify(accessControlFlowDAO).getAccessControlFlowByConditions(ac4FlowPageQuery);
    }

    @Test
    public void addFlowControlTest() {
        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();

        MockedConstruction<HttpEntity> mocked = Mockito.mockConstruction(HttpEntity.class, (mock, context) -> {
        });

        ResponseEntity<ResponseResult> response = mock(ResponseEntity.class);
        when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.POST), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class))).thenReturn(response);

        HttpStatus status = mock(HttpStatus.class);
        when(response.getStatusCode()).thenReturn(status);
        //true
        when(status.is2xxSuccessful()).thenReturn(true);
        flowControlServiceImpl.addFlowControl(ac4FlowStatusActionParam);

        //false
        when(status.is2xxSuccessful()).thenReturn(false);

        ResponseResult responseResult = mock(ResponseResult.class);
        when(response.getBody()).thenReturn(responseResult);
        when(responseResult.getData()).thenReturn("");
        try {
            flowControlServiceImpl.addFlowControl(ac4FlowStatusActionParam);
        } catch (Exception e) {
            assertTrue(e instanceof HTTPRequestFailedException);
        }
        mocked.close();

    }

    @Test
    public void enableFlowControlTest() {
        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();
        MockedConstruction<HttpEntity> mocked = Mockito.mockConstruction(HttpEntity.class, (mock, context) -> {
        });

        ResponseEntity<ResponseResult> response = mock(ResponseEntity.class);
        when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class))).thenReturn(response);

        // 处理响应
        HttpStatus status = mock(HttpStatus.class);
        when(response.getStatusCode()).thenReturn(status);
        //true
        when(status.is2xxSuccessful()).thenReturn(true);
        flowControlServiceImpl.enableFlowControl(ac4FlowStatusActionParam);

        //false
        when(status.is2xxSuccessful()).thenReturn(false);

        ResponseResult responseResult = mock(ResponseResult.class);
        when(response.getBody()).thenReturn(responseResult);
        when(responseResult.getData()).thenReturn("");

        try {
            flowControlServiceImpl.enableFlowControl(ac4FlowStatusActionParam);

        } catch (Exception e) {
            assertTrue(e instanceof HTTPRequestFailedException);
        }

        mocked.close();
    }

    @Test
    public void disableFlowControlTest() {
        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();
        MockedConstruction<HttpEntity> mocked = Mockito.mockConstruction(HttpEntity.class, (mock, context) -> {
        });

        ResponseEntity<ResponseResult> response = mock(ResponseEntity.class);
        when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class))).thenReturn(response);

        // 处理响应
        HttpStatus status = mock(HttpStatus.class);
        when(response.getStatusCode()).thenReturn(status);

        //true
        when(status.is2xxSuccessful()).thenReturn(true);
        flowControlServiceImpl.disableFlowControl(ac4FlowStatusActionParam);

        //false
        when(status.is2xxSuccessful()).thenReturn(false);

        ResponseResult responseResult = mock(ResponseResult.class);
        when(response.getBody()).thenReturn(responseResult);
        when(responseResult.getData()).thenReturn("");

        try {
            flowControlServiceImpl.disableFlowControl(ac4FlowStatusActionParam);

        } catch (Exception e) {
            assertTrue(e instanceof HTTPRequestFailedException);
        }

        mocked.close();
    }

    @Test
    public void removeFlowControlTest() {
        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();

        ResponseEntity<ResponseResult> response = mock(ResponseEntity.class);
        when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.DELETE), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class))).thenReturn(response);

        // 处理响应
        HttpStatus status = mock(HttpStatus.class);
        when(response.getStatusCode()).thenReturn(status);

        //true
        when(status.is2xxSuccessful()).thenReturn(true);
        flowControlServiceImpl.removeFlowControl(ac4FlowStatusActionParam);

        //false
        when(status.is2xxSuccessful()).thenReturn(false);

        ResponseResult responseResult = mock(ResponseResult.class);
        when(response.getBody()).thenReturn(responseResult);
        when(responseResult.getData()).thenReturn("");

        try {
            flowControlServiceImpl.removeFlowControl(ac4FlowStatusActionParam);

        } catch (Exception e) {
            assertTrue(e instanceof HTTPRequestFailedException);
        }
    }

    @Test
    public void modifyFlowControlsTest() {
        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();

        ResponseEntity<ResponseResult> response = mock(ResponseEntity.class);
        when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class))).thenReturn(response);

        // 处理响应
        HttpStatus status = mock(HttpStatus.class);
        when(response.getStatusCode()).thenReturn(status);

        //true
        when(status.is2xxSuccessful()).thenReturn(true);
        flowControlServiceImpl.modifyFlowControls(ac4FlowStatusActionParam);

        //false
        when(status.is2xxSuccessful()).thenReturn(false);

        ResponseResult responseResult = mock(ResponseResult.class);
        when(response.getBody()).thenReturn(responseResult);
        when(responseResult.getData()).thenReturn("");

        try {
            flowControlServiceImpl.modifyFlowControls(ac4FlowStatusActionParam);

        } catch (Exception e) {
            assertTrue(e instanceof HTTPRequestFailedException);
        }
    }

    @Test
    public void getServiceNoBindFlowControlTest() {
        List<ServiceDO> serviceNoBindFlowControl = new ArrayList<ServiceDO>();
        when(accessControlFlowDAO.getServiceNoBindFlowControl()).thenReturn(serviceNoBindFlowControl);

        List<ServiceDTO> serviceNoBindFlowControl4DTO = new ArrayList<ServiceDTO>();
        when(serviceConverter.do2dto(serviceNoBindFlowControl)).thenReturn(serviceNoBindFlowControl4DTO);

        flowControlServiceImpl.getServiceNoBindFlowControl();

        verify(accessControlFlowDAO).getServiceNoBindFlowControl();
        verify(serviceConverter).do2dto(serviceNoBindFlowControl);
    }

    @Test
    public void getFlowControlByIdTest() {
        FlowControlModel flowControl = new FlowControlModel();
        when(accessControlFlowDAO.getFlowControlById("")).thenReturn(flowControl);
        flowControlServiceImpl.getFlowControlById("");
        verify(accessControlFlowDAO).getFlowControlById("");

    }
}
