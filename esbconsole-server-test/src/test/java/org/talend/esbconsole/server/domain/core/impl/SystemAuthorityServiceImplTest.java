package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.RouteAndAuthorityModel;
import org.talend.esbconsole.server.domain.api.model.SystemAuthorityDTO;
import org.talend.esbconsole.server.domain.api.service.SystemRoleService;
import org.talend.esbconsole.server.domain.core.converter.SystemAuthorityConverter;
import org.talend.esbconsole.server.domain.repository.entity.SystemAuthorityDO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemAuthorityDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertSame;

/**
 * {@link SystemAuthorityServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/12
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemAuthorityServiceImplTest {

    @Mock
    private SystemAuthorityDAO systemAuthorityDAO;

    @Mock
    private SystemRoleService systemRoleService;

    @Mock
    private SystemAuthorityConverter systemAuthorityConverter;

    @InjectMocks
    private SystemAuthorityServiceImpl serviceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllAuthoritiesTreeTest() {
        List<RouteAndAuthorityModel> list = Mockito.mock(List.class);
        Mockito.doReturn(list).when(systemRoleService).getAllRouteAndAuthorityToTree();
        List<RouteAndAuthorityModel> result = serviceImpl.getAllAuthoritiesTree();
        assertSame(list, result);
        Mockito.verify(systemRoleService).getAllRouteAndAuthorityToTree();
    }

    @Test
    public void getAllAuthoritiesTest() {
        Mockito.doReturn(Arrays.asList(new SystemAuthorityDO())).when(systemAuthorityDAO).listAllSystemAuthorities();
        List<SystemAuthorityDTO> list = Mockito.mock(List.class);
        Mockito.doReturn(list).when(systemAuthorityConverter).do2dto(Mockito.anyList());
        List<SystemAuthorityDTO> result = serviceImpl.getAllAuthorities();
        assertSame(list, result);
        Mockito.verify(systemAuthorityDAO).listAllSystemAuthorities();
        Mockito.verify(systemAuthorityConverter).do2dto(Mockito.anyList());
    }

    @Test
    public void getAuthorityTest() {
        Mockito.doReturn(new SystemAuthorityDO()).when(systemAuthorityDAO).getSystemAuthorityById(Mockito.anyLong());
        SystemAuthorityDTO dto = Mockito.mock(SystemAuthorityDTO.class);
        Mockito.doReturn(dto).when(systemAuthorityConverter).do2dto(Mockito.any(SystemAuthorityDO.class));
        SystemAuthorityDTO result = serviceImpl.getAuthority(1L);
        assertSame(dto, result);
        Mockito.verify(systemAuthorityDAO).getSystemAuthorityById(Mockito.anyLong());
        Mockito.verify(systemAuthorityConverter).do2dto(Mockito.any(SystemAuthorityDO.class));

    }
}
