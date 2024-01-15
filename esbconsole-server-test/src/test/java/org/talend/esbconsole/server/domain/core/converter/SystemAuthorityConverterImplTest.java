package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.SystemAuthorityDTO;
import org.talend.esbconsole.server.domain.repository.entity.SystemAuthorityDO;
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
 * {@link SystemAuthorityConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemAuthorityConverterImplTest {

    @InjectMocks
    private SystemAuthorityConverterImpl systemAuthorityConverterImpl;

    @Test
    public void do2dto4SystemAuthorityDOTest() {
        SystemAuthorityDO systemAuthorityDO = new SystemAuthorityDO();
        systemAuthorityDO.setCreateTime(LocalDateTime.now());
        systemAuthorityDO.setUpdateTime(LocalDateTime.now());
        SystemAuthorityDTO systemAuthorityDTO = systemAuthorityConverterImpl.do2dto(systemAuthorityDO);
        assertNotNull(systemAuthorityDTO);
        systemAuthorityDO = null;
        SystemAuthorityDTO systemAuthorityDTO1 = systemAuthorityConverterImpl.do2dto(systemAuthorityDO);
        assertNull(systemAuthorityDTO1);
    }

    @Test
    public void do2dto4SystemAuthorityDOListTest() {
        ArrayList<SystemAuthorityDO> systemAuthorityDOS = new ArrayList<>();
        systemAuthorityDOS.add(new SystemAuthorityDO());
        List<SystemAuthorityDTO> systemAuthorityDTOS = systemAuthorityConverterImpl.do2dto(systemAuthorityDOS);
        assertNotNull(systemAuthorityDTOS);
        systemAuthorityDOS = null;
        List<SystemAuthorityDTO> systemAuthorityDTOS1 = systemAuthorityConverterImpl.do2dto(systemAuthorityDOS);
        assertNull(systemAuthorityDTOS1);
    }

}
