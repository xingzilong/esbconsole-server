package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.FrequencyControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyStatusActionParam;
import org.talend.esbconsole.server.domain.api.query.AC4FrequencyPageQuery;
import org.talend.esbconsole.server.domain.core.converter.ServiceConverter;
import org.talend.esbconsole.server.domain.core.converter.query.AC4FrequencyQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.domain.repository.mapper.AccessControlFrequencyDAO;
import org.talend.esbconsole.server.tools.base.exception.HTTPRequestFailedException;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
 * {@link FrequencyControlServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/12
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class FrequencyControlServiceImplTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    AccessControlFrequencyDAO accessControlFrequencyDAO;

    @Mock
    AC4FrequencyQueryConverter ac4FrequencyQueryConverter;

    @Mock
    ServiceConverter serviceConverter;

    @InjectMocks
    FrequencyControlServiceImpl frequencyControlService;

    @Test
    public void getFrequencyControlsTest() {
        AC4FrequencyPageQueryParam ac4FrequencyPageQueryParam = new AC4FrequencyPageQueryParam();
        ac4FrequencyPageQueryParam.setPageNum(1);
        ac4FrequencyPageQueryParam.setPageSize(10);

        AC4FrequencyPageQuery ac4FrequencyPageQuery = new AC4FrequencyPageQuery();
        when(ac4FrequencyQueryConverter.param2query(ac4FrequencyPageQueryParam)).thenReturn(ac4FrequencyPageQuery);

        List<FrequencyControlModel> listACFrequency = new ArrayList<FrequencyControlModel>();
        when(accessControlFrequencyDAO.getAccessControlFrequencyByConditions(ac4FrequencyPageQuery)).thenReturn(listACFrequency);

        frequencyControlService.getFrequencyControls(ac4FrequencyPageQueryParam);

        verify(ac4FrequencyQueryConverter).param2query(ac4FrequencyPageQueryParam);
        verify(accessControlFrequencyDAO).getAccessControlFrequencyByConditions(ac4FrequencyPageQuery);
    }

    @Test
    public void modifyFrequencyControlsTest() {
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();

        ResponseEntity<ResponseResult> response = mock(ResponseEntity.class);
        when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class))).thenReturn(response);

        // 处理响应
        HttpStatus status = mock(HttpStatus.class);
        when(response.getStatusCode()).thenReturn(status);

        //true
        when(status.is2xxSuccessful()).thenReturn(true);
        frequencyControlService.modifyFrequencyControls(ac4FrequencyStatusActionParam);

        //false
        when(status.is2xxSuccessful()).thenReturn(false);

        ResponseResult responseResult = mock(ResponseResult.class);
        when(response.getBody()).thenReturn(responseResult);
        when(responseResult.getData()).thenReturn("");

        verify(restTemplate).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class));
        verify(status).is2xxSuccessful();
        verify(response).getStatusCode();

        try {
            frequencyControlService.modifyFrequencyControls(ac4FrequencyStatusActionParam);

        } catch (Exception e) {
            assertTrue(e instanceof HTTPRequestFailedException);
        }
    }

    @Test
    public void addFrequencyControlTest() {
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();

        ResponseEntity<ResponseResult> response = mock(ResponseEntity.class);
        when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.POST), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class))).thenReturn(response);

        // 处理响应
        HttpStatus status = mock(HttpStatus.class);
        when(response.getStatusCode()).thenReturn(status);

        //true
        when(status.is2xxSuccessful()).thenReturn(true);
        frequencyControlService.addFrequencyControl(ac4FrequencyStatusActionParam);

        //false
        when(status.is2xxSuccessful()).thenReturn(false);

        ResponseResult responseResult = mock(ResponseResult.class);
        when(response.getBody()).thenReturn(responseResult);
        when(responseResult.getData()).thenReturn("");

        verify(restTemplate).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.POST), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class));
        verify(status).is2xxSuccessful();
        verify(response).getStatusCode();

        try {
            frequencyControlService.addFrequencyControl(ac4FrequencyStatusActionParam);

        } catch (Exception e) {
            assertTrue(e instanceof HTTPRequestFailedException);
        }
    }


    @Test
    public void enableFrequencyControlTest() {
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();

        ResponseEntity<ResponseResult> response = mock(ResponseEntity.class);
        when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class))).thenReturn(response);

        // 处理响应
        HttpStatus status = mock(HttpStatus.class);
        when(response.getStatusCode()).thenReturn(status);

        //true
        when(status.is2xxSuccessful()).thenReturn(true);
        frequencyControlService.enableFrequencyControl(ac4FrequencyStatusActionParam);

        //false
        when(status.is2xxSuccessful()).thenReturn(false);

        ResponseResult responseResult = mock(ResponseResult.class);
        when(response.getBody()).thenReturn(responseResult);
        when(responseResult.getData()).thenReturn("");

        verify(restTemplate).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class));
        verify(status).is2xxSuccessful();
        verify(response).getStatusCode();

        try {
            frequencyControlService.enableFrequencyControl(ac4FrequencyStatusActionParam);

        } catch (Exception e) {
            assertTrue(e instanceof HTTPRequestFailedException);
        }
    }

    @Test
    public void disableFrequencyControlTest() {
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();

        ResponseEntity<ResponseResult> response = mock(ResponseEntity.class);
        when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class))).thenReturn(response);

        // 处理响应
        HttpStatus status = mock(HttpStatus.class);
        when(response.getStatusCode()).thenReturn(status);

        //true
        when(status.is2xxSuccessful()).thenReturn(true);
        frequencyControlService.disableFrequencyControl(ac4FrequencyStatusActionParam);

        //false
        when(status.is2xxSuccessful()).thenReturn(false);

        ResponseResult responseResult = mock(ResponseResult.class);
        when(response.getBody()).thenReturn(responseResult);
        when(responseResult.getData()).thenReturn("");

        verify(restTemplate).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class));
        verify(status).is2xxSuccessful();
        verify(response).getStatusCode();

        try {
            frequencyControlService.disableFrequencyControl(ac4FrequencyStatusActionParam);

        } catch (Exception e) {
            assertTrue(e instanceof HTTPRequestFailedException);
        }
    }

    @Test
    public void removeFrequencyControlTest() {
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();

        ResponseEntity<ResponseResult> response = mock(ResponseEntity.class);
        when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.DELETE), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class))).thenReturn(response);

        // 处理响应
        HttpStatus status = mock(HttpStatus.class);
        when(response.getStatusCode()).thenReturn(status);

        //true
        when(status.is2xxSuccessful()).thenReturn(true);
        frequencyControlService.removeFrequencyControl(ac4FrequencyStatusActionParam);

        //false
        when(status.is2xxSuccessful()).thenReturn(false);

        ResponseResult responseResult = mock(ResponseResult.class);
        when(response.getBody()).thenReturn(responseResult);
        when(responseResult.getData()).thenReturn("");

        verify(restTemplate).exchange(Mockito.anyString(), Mockito.eq(HttpMethod.DELETE), Mockito.any(HttpEntity.class), Mockito.eq(ResponseResult.class));
        verify(status).is2xxSuccessful();
        verify(response).getStatusCode();

        try {
            frequencyControlService.removeFrequencyControl(ac4FrequencyStatusActionParam);

        } catch (Exception e) {
            assertTrue(e instanceof HTTPRequestFailedException);
        }
    }

    @Test
    public void getServiceNoBindFrequencyControlTest() {
        List<ServiceDO> serviceNoBindFrequencyControl = new ArrayList<ServiceDO>();
        when(accessControlFrequencyDAO.getServiceNoBindFrequencyControl()).thenReturn(serviceNoBindFrequencyControl);

        List<ServiceDTO> serviceNoBindFrequencyControlDTOS = new ArrayList<ServiceDTO>();
        when(serviceConverter.do2dto(serviceNoBindFrequencyControl)).thenReturn(serviceNoBindFrequencyControlDTOS);

        frequencyControlService.getServiceNoBindFrequencyControl();

        verify(accessControlFrequencyDAO).getServiceNoBindFrequencyControl();
        verify(serviceConverter).do2dto(serviceNoBindFrequencyControl);
    }

    @Test
    public void getFrequencyControlByIdTest() {
        FrequencyControlModel frequencyControl = new FrequencyControlModel();
        when(accessControlFrequencyDAO.getFrequencyControlById("")).thenReturn(frequencyControl);

        frequencyControlService.getFrequencyControlById("");
        verify(accessControlFrequencyDAO).getFrequencyControlById("");
    }

}
