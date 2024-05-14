package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.RouteAndAuthorityModel;
import org.talend.esbconsole.server.domain.api.model.RouteModel;
import org.talend.esbconsole.server.domain.api.model.SystemRoleDTO;
import org.talend.esbconsole.server.domain.api.param.AssignAuthorityParam;
import org.talend.esbconsole.server.domain.api.param.RoleCreateParam;
import org.talend.esbconsole.server.domain.api.param.RoleModifyParam;
import org.talend.esbconsole.server.domain.api.param.RolePageQueryParam;
import org.talend.esbconsole.server.domain.api.query.RolePageQuery;
import org.talend.esbconsole.server.domain.core.converter.SystemRoleConverter;
import org.talend.esbconsole.server.domain.core.converter.query.RoleQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.SystemRoleDO;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.talend.esbconsole.server.domain.repository.mapper.SystemAuthorityDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemRoleAuthorityDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemRoleDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserRoleDAO;

import java.util.*;

/**
 * {@link SystemRoleServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemRoleServiceImplTest {

    @Mock
    private SystemRoleDAO systemRoleDAO;

    @Mock
    private SystemUserDAO systemUserDAO;

    @Mock
    private SystemUserRoleDAO systemUserRoleDAO;

    @Mock
    private SystemRoleAuthorityDAO systemRoleAuthorityDAO;

    @Mock
    private SystemAuthorityDAO systemAuthorityDAO;

    @Mock
    private SystemRoleConverter systemRoleConverter;

    @Mock
    private RoleQueryConverter roleQueryConverter;

    @InjectMocks
    private SystemRoleServiceImpl serviceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addRoleTest() {
        Mockito.doReturn(new SystemRoleDO()).when(systemRoleConverter).param2do(Mockito.any(RoleCreateParam.class));
        Mockito.doNothing().when(systemRoleDAO).saveSystemRole(Mockito.any(SystemRoleDO.class));
        serviceImpl.addRole(new RoleCreateParam());
        Mockito.verify(systemRoleConverter).param2do(Mockito.any(RoleCreateParam.class));
        Mockito.verify(systemRoleDAO).saveSystemRole(Mockito.any(SystemRoleDO.class));
    }

    @Test
    public void removeRoleTest() {
        List<String> systemUserIds = new ArrayList<>();
        Mockito.doReturn(systemUserIds).when(systemUserRoleDAO).listUserIdsRoleByRoleId(Mockito.anyString());
        Mockito.doReturn(1).when(systemRoleAuthorityDAO).removeSystemRoleAuthorityByRoleId(Mockito.anyString());
        Mockito.doReturn(1).when(systemUserRoleDAO).removeSystemUserRoleByRoleId(Mockito.anyString());
        Mockito.doNothing().when(systemRoleDAO).removeSystemRole(Mockito.anyString());
        serviceImpl.removeRole("123");
        Mockito.verify(systemUserRoleDAO).listUserIdsRoleByRoleId(Mockito.anyString());
        Mockito.verify(systemRoleAuthorityDAO).removeSystemRoleAuthorityByRoleId(Mockito.anyString());
        Mockito.verify(systemRoleDAO).removeSystemRole(Mockito.anyString());
        Mockito.verify(systemUserRoleDAO).removeSystemUserRoleByRoleId(Mockito.anyString());
    }

    @Test
    public void removeSystemUserRoleByUserIdAndRoleIdTest() {
        Mockito.doNothing().when(systemRoleDAO).updateSystemRole(Mockito.any(SystemRoleDO.class));
        serviceImpl.removeSystemUserRoleByUserIdAndRoleId("123456", "456789");
        Mockito.verify(systemRoleDAO).updateSystemRole(Mockito.any(SystemRoleDO.class));
    }

    @Test
    public void modifyRoleTest() {
        Mockito.doReturn(new SystemRoleDO()).when(systemRoleConverter).param2do(Mockito.any(RoleModifyParam.class));
        Mockito.doNothing().when(systemRoleDAO).updateSystemRole(Mockito.any(SystemRoleDO.class));
        serviceImpl.modifyRole(new RoleModifyParam());
        Mockito.verify(systemRoleConverter).param2do(Mockito.any(RoleModifyParam.class));
        Mockito.verify(systemRoleDAO).updateSystemRole(Mockito.any(SystemRoleDO.class));
    }

    @Test
    public void disableRoleTest() {
        Mockito.doNothing().when(systemRoleDAO).updateSystemRole(Mockito.any(SystemRoleDO.class));
        serviceImpl.disableRole("123456");
        Mockito.verify(systemRoleDAO).updateSystemRole(Mockito.any(SystemRoleDO.class));
    }

    @Test
    public void enableRoleTest() {
        Mockito.doNothing().when(systemRoleDAO).updateSystemRole(Mockito.any(SystemRoleDO.class));
        serviceImpl.enableRole("123456");
        Mockito.verify(systemRoleDAO).updateSystemRole(Mockito.any(SystemRoleDO.class));
    }

    @Test
    public void getAllRolesTest() {
        Mockito.doReturn(Arrays.asList(new SystemRoleDO())).when(systemRoleDAO).listAllSystemRoles();
        Mockito.doReturn(Arrays.asList(new SystemRoleDTO())).when(systemRoleConverter).do2dto(Mockito.anyList());
        serviceImpl.getAllRoles();
        Mockito.verify(systemRoleDAO).listAllSystemRoles();
        Mockito.verify(systemRoleConverter).do2dto(Mockito.anyList());
    }

    @Test
    public void getRolesTest() {
        RolePageQueryParam param = new RolePageQueryParam();
        param.setPageNum(1);
        param.setPageSize(10);
        Mockito.doReturn(new RolePageQuery()).when(roleQueryConverter).param2query(Mockito.any(RolePageQueryParam.class));
        Mockito.doReturn(Arrays.asList(new SystemRoleDO())).when(systemRoleDAO).listAllSystemRolesByConditions(Mockito.any(RolePageQuery.class));
        Mockito.doReturn(Arrays.asList(new SystemRoleDTO())).when(systemRoleConverter).do2dto(Mockito.anyList());
        serviceImpl.getRoles(param);
        Mockito.verify(roleQueryConverter).param2query(Mockito.any(RolePageQueryParam.class));
        Mockito.verify(systemRoleDAO).listAllSystemRolesByConditions(Mockito.any(RolePageQuery.class));
        Mockito.verify(systemRoleConverter).do2dto(Mockito.anyList());
    }

    @Test
    public void getRoleTest() {
        Mockito.doReturn(new SystemRoleDO()).when(systemRoleDAO).getSystemRoleById(Mockito.anyString());
        Mockito.doReturn(new SystemRoleDTO()).when(systemRoleConverter).do2dto(Mockito.any(SystemRoleDO.class));
        serviceImpl.getRole("123456");
        Mockito.verify(systemRoleDAO).getSystemRoleById(Mockito.anyString());
        Mockito.verify(systemRoleConverter).do2dto(Mockito.any(SystemRoleDO.class));
    }

    @Test
    public void listUserRoleByRoleIdTest() {
        Mockito.doReturn(Arrays.asList("role")).when(systemUserRoleDAO).listUserIdsRoleByRoleId(Mockito.anyString());
        Mockito.doReturn(new SystemUserDO()).when(systemUserDAO).getSystemUserById(Mockito.anyString());
        serviceImpl.listUserRoleByRoleId("role");
        Mockito.verify(systemUserRoleDAO).listUserIdsRoleByRoleId(Mockito.anyString());
        Mockito.verify(systemUserDAO).getSystemUserById(Mockito.anyString());
    }

    @Test
    public void getRoleByRoleNameTest() {
        Mockito.doReturn(new SystemRoleDO()).when(systemRoleDAO).getSystemRoleByRoleName(Mockito.eq("test"));
        Mockito.doReturn(new SystemRoleDTO()).when(systemRoleConverter).do2dto(Mockito.any(SystemRoleDO.class));
        serviceImpl.getRoleByRoleName("test");
        Mockito.verify(systemRoleDAO).getSystemRoleByRoleName(Mockito.eq("test"));
        Mockito.verify(systemRoleConverter).do2dto(Mockito.any(SystemRoleDO.class));
    }

    @Test
    public void assignAuthorityForRoleTest() {
        AssignAuthorityParam param = Mockito.mock(AssignAuthorityParam.class);
        Mockito.doReturn("12306").when(param).getRoleId();
        RouteModel route = new RouteModel();
        route.setId(3L);
        Mockito.doReturn(new HashSet<Long>(Arrays.asList(1L, 2L))).doReturn(new HashSet<Long>()).when(param).getAuthorityIdList();
        Mockito.doReturn(Arrays.asList(route)).when(systemAuthorityDAO).listAllRoutes();
        Mockito.doReturn(new HashSet<Long>(Arrays.asList(1L, 2L))).when(systemAuthorityDAO).listParentIdByIdList(Mockito.anySet());
        Mockito.doReturn(1).when(systemRoleAuthorityDAO).removeSystemRoleAuthorityByRoleId(Mockito.anyString());
        Mockito.doReturn(1).when(systemRoleAuthorityDAO).saveSystemRoleAuthorityList(Mockito.anyList());
        serviceImpl.assignAuthorityForRole(param);
        Mockito.verify(systemAuthorityDAO).listAllRoutes();
        Mockito.verify(systemAuthorityDAO).listParentIdByIdList(Mockito.anySet());
        Mockito.verify(systemRoleAuthorityDAO).removeSystemRoleAuthorityByRoleId(Mockito.anyString());
        Mockito.verify(systemRoleAuthorityDAO).saveSystemRoleAuthorityList(Mockito.anyList());
        serviceImpl.assignAuthorityForRole(param);
    }

    @Test
    public void getAllRouteAndAuthorityToTreeTest() {
        Mockito.doReturn(Arrays.asList()).when(systemAuthorityDAO).listAllRouteAndAuthority();
        serviceImpl.getAllRouteAndAuthorityToTree();
        Mockito.verify(systemAuthorityDAO).listAllRouteAndAuthority();
    }

    @Test
    public void getAuthorityOtherInfoTest() {
        Mockito.doReturn(Arrays.asList()).when(systemAuthorityDAO).listAllRouteAndAuthority();
        Mockito.doReturn(new HashSet<Long>()).when(systemAuthorityDAO).listIdForRoute();
        Mockito.doReturn(new HashSet<Long>()).when(systemAuthorityDAO).listIdForAuthorityByRoleId(Mockito.anyString());
        serviceImpl.getAuthorityOtherInfo("test");
        Mockito.verify(systemAuthorityDAO).listAllRouteAndAuthority();
        Mockito.verify(systemAuthorityDAO).listIdForRoute();
        Mockito.verify(systemAuthorityDAO).listIdForAuthorityByRoleId(Mockito.anyString());
    }

    @Test
    public void getTreeStructTest() {
        List<RouteAndAuthorityModel> list = Mockito.mock(List.class);
        Iterator<RouteAndAuthorityModel> iterator = Mockito.mock(Iterator.class);
        Mockito.doReturn(iterator).when(list).iterator();
        RouteAndAuthorityModel model = Mockito.mock(RouteAndAuthorityModel.class);
        Mockito.doReturn(model).when(iterator).next();
        Mockito.doReturn(true).doReturn(true).doReturn(true).doReturn(true).doReturn(false).when(iterator).hasNext();
        Mockito.doReturn(1L).doReturn(0L).doReturn(2L).when(model).getParentId();
        Mockito.doReturn(1L).doReturn(2L).when(model).getId();
        serviceImpl.getTreeStruct(list, 0);
        Mockito.verify(model, Mockito.times(2)).getId();
        Mockito.verify(iterator, Mockito.times(7)).hasNext();
    }

    @Test
    public void findRouteIdForAuthorityTest() {
        RouteModel model = Mockito.mock(RouteModel.class);
        Mockito.doReturn(1L).when(model).getId();
        Mockito.doReturn(1L).doReturn(0L).when(model).getParentId();
        serviceImpl.findRouteIdForAuthority(Arrays.asList(model), new HashSet<Long>(Arrays.asList(1L)));
        Mockito.verify(model, Mockito.times(2)).getParentId();

    }

}
