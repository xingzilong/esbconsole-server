package org.talend.esbconsole.server.web.api.controller.servicelog.converter;

import org.talend.esbconsole.server.domain.api.model.ServiceLogDTO;
import org.talend.esbconsole.server.domain.api.param.ServiceLogPageQueryParam;
import org.talend.esbconsole.server.web.api.controller.servicelog.request.ServiceLogPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.servicelog.vo.ServiceLogVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link ServiceLogWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceLogWebConverterImplTest {

    @InjectMocks
    private ServiceLogWebConverterImpl serviceLogWebConverterImpl;

    @Test
    public void req2paramTest() {
        ServiceLogPageQueryRequest request = new ServiceLogPageQueryRequest();
        ServiceLogPageQueryParam serviceLogPageQueryParam = serviceLogWebConverterImpl.req2param(request);
        assertNotNull(serviceLogPageQueryParam);
        request = null;
        ServiceLogPageQueryParam serviceLogPageQueryParam1 = serviceLogWebConverterImpl.req2param(request);
        assertNull(serviceLogPageQueryParam1);
    }

    @Test
    public void dto2vo4ServiceLogDTOTest() {
        ServiceLogDTO serviceLogDTO = new ServiceLogDTO();
        ServiceLogVO serviceLogVO = serviceLogWebConverterImpl.dto2vo(serviceLogDTO);
        assertNotNull(serviceLogVO);
        serviceLogDTO = null;
        ServiceLogVO serviceLogVO1 = serviceLogWebConverterImpl.dto2vo(serviceLogDTO);
        assertNull(serviceLogVO1);
    }

    @Test
    public void dto2vo4ServiceLogDTOListTest() {
        ArrayList<ServiceLogDTO> serviceLogDTOS = new ArrayList<>();
        serviceLogDTOS.add(new ServiceLogDTO());
        List<ServiceLogVO> serviceLogVOS = serviceLogWebConverterImpl.dto2vo(serviceLogDTOS);
        assertNotNull(serviceLogVOS);
        serviceLogDTOS = null;
        List<ServiceLogVO> serviceLogVOS1 = serviceLogWebConverterImpl.dto2vo(serviceLogDTOS);
        assertNull(serviceLogVOS1);
    }

}
