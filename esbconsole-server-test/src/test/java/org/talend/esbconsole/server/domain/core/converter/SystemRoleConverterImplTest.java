package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.SystemRoleDTO;
import org.talend.esbconsole.server.domain.api.param.RoleCreateParam;
import org.talend.esbconsole.server.domain.api.param.RoleModifyParam;
import org.talend.esbconsole.server.domain.repository.entity.SystemRoleDO;
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
 * {@link SystemRoleConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemRoleConverterImplTest {

    @InjectMocks
    private SystemRoleConverterImpl systemRoleConverterImpl;

    @Test
    public void do2dto4SystemRoleDOTest() {
        SystemRoleDO systemRoleDO = new SystemRoleDO();
        systemRoleDO.setCreateTime(LocalDateTime.now());
        systemRoleDO.setUpdateTime(LocalDateTime.now());
        SystemRoleDTO systemRoleDTO = systemRoleConverterImpl.do2dto(systemRoleDO);
        assertNotNull(systemRoleDTO);
        systemRoleDO = null;
        SystemRoleDTO systemRoleDTO1 = systemRoleConverterImpl.do2dto(systemRoleDO);
        assertNull(systemRoleDTO1);
    }

    @Test
    public void do2dto4SystemRoleDOListTest() {
        ArrayList<SystemRoleDO> systemRoleDOS = new ArrayList<>();
        systemRoleDOS.add(new SystemRoleDO());
        List<SystemRoleDTO> systemRoleDTOS = systemRoleConverterImpl.do2dto(systemRoleDOS);
        assertNotNull(systemRoleDTOS);
        systemRoleDOS = null;
        List<SystemRoleDTO> systemRoleDTOS1 = systemRoleConverterImpl.do2dto(systemRoleDOS);
        assertNull(systemRoleDTOS1);
    }

    @Test
    public void param2do4RoleCreateParamTest() {
        RoleCreateParam param = new RoleCreateParam();
        SystemRoleDO systemRoleDO = systemRoleConverterImpl.param2do(param);
        assertNotNull(systemRoleDO);
        param = null;
        SystemRoleDO systemRoleDO1 = systemRoleConverterImpl.param2do(param);
        assertNull(systemRoleDO1);
    }

    @Test
    public void param2do4RoleModifyParamTest() {
        RoleModifyParam param = new RoleModifyParam();
        SystemRoleDO systemRoleDO = systemRoleConverterImpl.param2do(param);
        assertNotNull(systemRoleDO);
        param = null;
        SystemRoleDO systemRoleDO1 = systemRoleConverterImpl.param2do(param);
        assertNull(systemRoleDO1);
    }
}
