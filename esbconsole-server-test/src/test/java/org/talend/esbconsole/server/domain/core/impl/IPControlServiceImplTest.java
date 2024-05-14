package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.IPControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4IPPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4IPStatusActionParam;
import org.talend.esbconsole.server.domain.api.query.AC4IPPageQuery;
import org.talend.esbconsole.server.domain.core.converter.ServiceConverter;
import org.talend.esbconsole.server.domain.core.converter.query.AC4IPQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.domain.repository.mapper.AccessControlIPDAO;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link UserDetailsServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/12
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IPControlServiceImplTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AccessControlIPDAO accessControlIPDAO;

    @Mock
    private AC4IPQueryConverter ac4IPQueryConverter;

    @Mock
    private ServiceConverter serviceConverter;

    @InjectMocks
    private IPControlServiceImpl serviceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getIPControlsTest() {
        AC4IPPageQueryParam param = new AC4IPPageQueryParam();
        param.setPageNum(1);
        param.setPageSize(10);
        Mockito.doReturn(new AC4IPPageQuery()).when(ac4IPQueryConverter).param2query(Mockito.any(AC4IPPageQueryParam.class));
        Mockito.doReturn(Arrays.asList(new IPControlModel())).when(accessControlIPDAO).getAccessControlIPByConditions(Mockito.any(AC4IPPageQuery.class));
        serviceImpl.getIPControls(param);
        Mockito.verify(ac4IPQueryConverter).param2query(Mockito.any(AC4IPPageQueryParam.class));
        Mockito.verify(accessControlIPDAO).getAccessControlIPByConditions(Mockito.any(AC4IPPageQuery.class));

    }

    @Test
    public void addIPControlTest() {
        ResponseEntity<ResponseResult> response = Mockito.mock(ResponseEntity.class);
        Mockito.doReturn(response).when(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        HttpStatus status = Mockito.mock(HttpStatus.class);
        Mockito.doReturn(status).when(response).getStatusCode();
        Mockito.doReturn(true).doReturn(false).when(status).is2xxSuccessful();
        ResponseResult result = Mockito.mock(ResponseResult.class);
        Mockito.doReturn(result).when(response).getBody();
        Mockito.doReturn("test").when(result).getData();
        serviceImpl.addIPControl(new AC4IPStatusActionParam());
        Mockito.verify(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        Mockito.verify(status).is2xxSuccessful();
        try {
            serviceImpl.addIPControl(new AC4IPStatusActionParam());
        } catch (Exception e) {
            assertEquals("test", e.getMessage());
        }
    }

    @Test
    public void enableIPControlTest() {
        ResponseEntity<ResponseResult> response = Mockito.mock(ResponseEntity.class);
        Mockito.doReturn(response).when(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        HttpStatus status = Mockito.mock(HttpStatus.class);
        Mockito.doReturn(status).when(response).getStatusCode();
        Mockito.doReturn(true).doReturn(false).when(status).is2xxSuccessful();
        ResponseResult result = Mockito.mock(ResponseResult.class);
        Mockito.doReturn(result).when(response).getBody();
        Mockito.doReturn("test").when(result).getData();
        serviceImpl.enableIPControl(new AC4IPStatusActionParam());
        Mockito.verify(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        Mockito.verify(status).is2xxSuccessful();
        try {
            serviceImpl.enableIPControl(new AC4IPStatusActionParam());
        } catch (Exception e) {
            assertEquals("test", e.getMessage());
        }
    }

    @Test
    public void disableIPControlTest() {
        ResponseEntity<ResponseResult> response = Mockito.mock(ResponseEntity.class);
        Mockito.doReturn(response).when(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        HttpStatus status = Mockito.mock(HttpStatus.class);
        Mockito.doReturn(status).when(response).getStatusCode();
        Mockito.doReturn(true).doReturn(false).when(status).is2xxSuccessful();
        ResponseResult result = Mockito.mock(ResponseResult.class);
        Mockito.doReturn(result).when(response).getBody();
        Mockito.doReturn("test").when(result).getData();
        serviceImpl.disableIPControl(new AC4IPStatusActionParam());
        Mockito.verify(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        Mockito.verify(status).is2xxSuccessful();
        try {
            serviceImpl.disableIPControl(new AC4IPStatusActionParam());
        } catch (Exception e) {
            assertEquals("test", e.getMessage());
        }
    }

    @Test
    public void removeIPControlTest() {
        ResponseEntity<ResponseResult> response = Mockito.mock(ResponseEntity.class);
        Mockito.doReturn(response).when(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        HttpStatus status = Mockito.mock(HttpStatus.class);
        Mockito.doReturn(status).when(response).getStatusCode();
        Mockito.doReturn(true).doReturn(false).when(status).is2xxSuccessful();
        ResponseResult result = Mockito.mock(ResponseResult.class);
        Mockito.doReturn(result).when(response).getBody();
        Mockito.doReturn("test").when(result).getData();
        serviceImpl.removeIPControl(new AC4IPStatusActionParam());
        Mockito.verify(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        Mockito.verify(status).is2xxSuccessful();
        try {
            serviceImpl.removeIPControl(new AC4IPStatusActionParam());
        } catch (Exception e) {
            assertEquals("test", e.getMessage());
        }
    }

    @Test
    public void modifyIPControlsTest() {
        ResponseEntity<ResponseResult> response = Mockito.mock(ResponseEntity.class);
        Mockito.doReturn(response).when(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        HttpStatus status = Mockito.mock(HttpStatus.class);
        Mockito.doReturn(status).when(response).getStatusCode();
        Mockito.doReturn(true).doReturn(false).when(status).is2xxSuccessful();
        ResponseResult result = Mockito.mock(ResponseResult.class);
        Mockito.doReturn(result).when(response).getBody();
        Mockito.doReturn("test").when(result).getData();
        serviceImpl.modifyIPControls(new AC4IPStatusActionParam());
        Mockito.verify(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        Mockito.verify(status).is2xxSuccessful();
        try {
            serviceImpl.modifyIPControls(new AC4IPStatusActionParam());
        } catch (Exception e) {
            assertEquals("test", e.getMessage());
        }
    }

    @Test
    public void getServiceNoBindIPControlTest() {
        List<ServiceDTO> services = Mockito.mock(List.class);
        Mockito.doReturn(services).when(serviceConverter).do2dto(Mockito.anyList());
        Mockito.doReturn(Arrays.asList(new ServiceDO())).when(accessControlIPDAO).getServiceNoBindIPControl();
        assertSame(services, serviceImpl.getServiceNoBindIPControl());
        Mockito.verify(serviceConverter).do2dto(Mockito.anyList());
        Mockito.verify(accessControlIPDAO).getServiceNoBindIPControl();
    }

    @Test
    public void getIPControlByIdTest() {
        IPControlModel model = Mockito.mock(IPControlModel.class);
        Mockito.doReturn(model).when(accessControlIPDAO).getIPControlById(Mockito.anyString());
        assertSame(model, serviceImpl.getIPControlById("test"));
        Mockito.verify(accessControlIPDAO).getIPControlById(Mockito.anyString());
    }

}
