package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.ServiceInstallParam;
import org.talend.esbconsole.server.domain.api.param.ServiceModifyParam;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link ServiceConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceConverterImplTest {

    @InjectMocks
    private ServiceConverterImpl serviceConverterImpl;

    @Test
    public void jsonString2ListTest() {
        String josnString = "[\"aaa\" , \"bbb\"]";
        List<String> list = serviceConverterImpl.jsonString2List(josnString);
        assertNotNull(list);
        josnString = null;
        List<String> list1 = serviceConverterImpl.jsonString2List(josnString);
        assertNull(list1);
    }

    @Test
    public void do2dto4ServiceDOTest() {
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setCreateTime(LocalDateTime.now());
        serviceDO.setUpdateTime(LocalDateTime.now());
        ServiceDTO serviceDTO = serviceConverterImpl.do2dto(serviceDO);
        assertNotNull(serviceDTO);
        serviceDO = null;
        ServiceDTO serviceDTO1 = serviceConverterImpl.do2dto(serviceDO);
        assertNull(serviceDTO1);
    }

    @Test
    public void do2dto4ServiceDOListTest() {
        ArrayList<ServiceDO> serviceDOS = new ArrayList<>();
        serviceDOS.add(new ServiceDO());
        List<ServiceDTO> serviceDTOS = serviceConverterImpl.do2dto(serviceDOS);
        assertNotNull(serviceDTOS);
        serviceDOS = null;
        List<ServiceDTO> serviceDTOS1 = serviceConverterImpl.do2dto(serviceDOS);
        assertNull(serviceDTOS1);
    }

    @Test
    public void param2do4ServiceInstallParamTest() {
        ServiceInstallParam param = new ServiceInstallParam();
        ServiceDO serviceDO = serviceConverterImpl.param2do(param);
        assertNotNull(serviceDO);
        param = null;
        ServiceDO serviceDO1 = serviceConverterImpl.param2do(param);
        assertNull(serviceDO1);
    }

    @Test
    public void param2do4ServiceModifyParamTest() {
        ServiceModifyParam param = new ServiceModifyParam();
        ServiceDO serviceDO = serviceConverterImpl.param2do(param);
        assertNotNull(serviceDO);
        param = null;
        ServiceDO serviceDO1 = serviceConverterImpl.param2do(param);
        assertNull(serviceDO1);
    }
}
