package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.SystemUserDTO;
import org.talend.esbconsole.server.domain.api.param.ResetPasswordParam;
import org.talend.esbconsole.server.domain.api.param.UserCreateParam;
import org.talend.esbconsole.server.domain.api.param.UserModifyParam;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
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
 * {@link SystemUserConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemUserConverterImplTest {

    @InjectMocks
    private SystemUserConverterImpl systemUserConverterImpl;

    @Test
    public void do2dto4SystemUserDOTest() {
        SystemUserDO systemUserDO = new SystemUserDO();
        systemUserDO.setCreateTime(LocalDateTime.now());
        systemUserDO.setUpdateTime(LocalDateTime.now());
        SystemUserDTO systemUserDTO = systemUserConverterImpl.do2dto(systemUserDO);
        assertNotNull(systemUserDTO);
        systemUserDO = null;
        SystemUserDTO systemUserDTO1 = systemUserConverterImpl.do2dto(systemUserDO);
        assertNull(systemUserDTO1);
    }

    @Test
    public void do2dto4SystemUserDOListTest() {
        ArrayList<SystemUserDO> systemUserDOS = new ArrayList<>();
        systemUserDOS.add(new SystemUserDO());
        List<SystemUserDTO> systemUserDTOS = systemUserConverterImpl.do2dto(systemUserDOS);
        assertNotNull(systemUserDTOS);
        systemUserDOS = null;
        List<SystemUserDTO> systemUserDTOS1 = systemUserConverterImpl.do2dto(systemUserDOS);
        assertNull(systemUserDTOS1);
    }

    @Test
    public void param2do4UserCreateParamTest() {
        UserCreateParam param = new UserCreateParam();
        SystemUserDO systemUserDO = systemUserConverterImpl.param2do(param);
        assertNotNull(systemUserDO);
        param = null;
        SystemUserDO systemUserDO1 = systemUserConverterImpl.param2do(param);
        assertNull(systemUserDO1);
    }

    @Test
    public void param2do4UserModifyParamTest() {
        UserModifyParam param = new UserModifyParam();
        SystemUserDO systemUserDO = systemUserConverterImpl.param2do(param);
        assertNotNull(systemUserDO);
        param = null;
        SystemUserDO systemUserDO1 = systemUserConverterImpl.param2do(param);
        assertNull(systemUserDO1);
    }

    @Test
    public void param2do4ResetPasswordParamTest() {
        ResetPasswordParam param = new ResetPasswordParam();
        SystemUserDO systemUserDO = systemUserConverterImpl.param2do(param);
        assertNotNull(systemUserDO);
        param = null;
        SystemUserDO systemUserDO1 = systemUserConverterImpl.param2do(param);
        assertNull(systemUserDO1);
    }
}
