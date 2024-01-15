package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.BusinessSystemDTO;
import org.talend.esbconsole.server.domain.api.param.BusinessCreateParam;
import org.talend.esbconsole.server.domain.api.param.BusinessModifyParam;
import org.talend.esbconsole.server.domain.repository.entity.BusinessSystemDO;
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
 * {@link BusinessSystemConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessSystemConverterImplTest {

    @InjectMocks
    private BusinessSystemConverterImpl businessSystemConverterImpl;

    @Test
    public void do2dto4BusinessSystemDOTest() {
        BusinessSystemDO businessSystemDO = new BusinessSystemDO();
        businessSystemDO.setCreateTime(LocalDateTime.now());
        businessSystemDO.setUpdateTime(LocalDateTime.now());
        BusinessSystemDTO businessSystemDTO = businessSystemConverterImpl.do2dto(businessSystemDO);
        assertNotNull(businessSystemDTO);
        businessSystemDO = null;
        BusinessSystemDTO businessSystemDTO1 = businessSystemConverterImpl.do2dto(businessSystemDO);
        assertNull(businessSystemDTO1);
    }

    @Test
    public void do2dto4BusinessSystemDOListTest() {
        ArrayList<BusinessSystemDO> businessSystemDOS = new ArrayList<>();
        businessSystemDOS.add(new BusinessSystemDO());
        List<BusinessSystemDTO> businessSystemDTOS = businessSystemConverterImpl.do2dto(businessSystemDOS);
        assertNotNull(businessSystemDTOS);
        businessSystemDOS = null;
        List<BusinessSystemDTO> businessSystemDTOS1 = businessSystemConverterImpl.do2dto(businessSystemDOS);
        assertNull(businessSystemDTOS1);
    }

    @Test
    public void param2do4BusinessCreateParamTest() {
        BusinessCreateParam param = new BusinessCreateParam();
        BusinessSystemDO businessSystemDO = businessSystemConverterImpl.param2do(param);
        assertNotNull(businessSystemDO);
        param = null;
        BusinessSystemDO businessSystemDO1 = businessSystemConverterImpl.param2do(param);
        assertNull(businessSystemDO1);
    }

    @Test
    public void param2do4BusinessModifyParamTest() {
        BusinessModifyParam param = new BusinessModifyParam();
        BusinessSystemDO businessSystemDO = businessSystemConverterImpl.param2do(param);
        assertNotNull(businessSystemDO);
        param = null;
        BusinessSystemDO businessSystemDO1 = businessSystemConverterImpl.param2do(param);
        assertNull(businessSystemDO1);
    }
}
