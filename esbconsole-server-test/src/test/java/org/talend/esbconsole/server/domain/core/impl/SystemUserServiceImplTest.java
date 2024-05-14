package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.SystemRoleDTO;
import org.talend.esbconsole.server.domain.api.model.SystemUserDTO;
import org.talend.esbconsole.server.domain.api.param.AssignRoleParam;
import org.talend.esbconsole.server.domain.api.param.ResetPasswordParam;
import org.talend.esbconsole.server.domain.api.param.UserCreateParam;
import org.talend.esbconsole.server.domain.api.param.UserModifyParam;
import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.UserPageQuery;
import org.talend.esbconsole.server.domain.core.converter.SystemRoleConverter;
import org.talend.esbconsole.server.domain.core.converter.SystemUserConverter;
import org.talend.esbconsole.server.domain.core.converter.query.UserQueryConverter;
import org.talend.esbconsole.server.domain.core.util.GuavaCacheUtil;
import org.talend.esbconsole.server.domain.repository.entity.SystemRoleDO;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemRoleDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserRoleDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link SystemUserServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemUserServiceImplTest {

    @Mock
    private SystemRoleDAO systemRoleDAO;

    @Mock
    private SystemUserDAO systemUserDAO;

    @Mock
    private SystemUserRoleDAO systemUserRoleDAO;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private GuavaCacheUtil guavaCacheUtil;

    @Mock
    private SystemUserConverter systemUserConverter;

    @Mock
    private UserQueryConverter userQueryConverter;

    @Mock
    private SystemRoleConverter systemRoleConverter;

    @InjectMocks
    private SystemUserServiceImpl serviceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addUserTest() {

        Mockito.doReturn(new SystemUserDO()).when(systemUserConverter).param2do(Mockito.any(UserCreateParam.class));
        Mockito.doReturn("test123456").when(passwordEncoder).encode(null);
        Mockito.doNothing().when(systemUserDAO).saveSystemUser(Mockito.any(SystemUserDO.class));
        serviceImpl.addUser(new UserCreateParam());
        Mockito.verify(systemUserConverter).param2do(Mockito.any(UserCreateParam.class));
        Mockito.verify(passwordEncoder).encode(null);
        Mockito.verify(systemUserDAO).saveSystemUser(Mockito.any(SystemUserDO.class));
    }

    @Test
    public void removeUserTest() {

        Mockito.doReturn(1).when(systemUserRoleDAO).removeSystemUserRoleByUserId(Mockito.anyString());
        Mockito.doNothing().when(systemUserDAO).removeSystemUser(Mockito.anyString());
        serviceImpl.removeUser("123");
        Mockito.verify(systemUserRoleDAO).removeSystemUserRoleByUserId(Mockito.anyString());
        Mockito.verify(systemUserDAO).removeSystemUser(Mockito.anyString());
    }

    @Test
    public void modifyUserTest() {
        Mockito.doReturn(new SystemUserDO()).when(systemUserConverter).param2do(Mockito.any(UserModifyParam.class));
        Mockito.doNothing().when(systemUserDAO).updateSystemUser(Mockito.any(SystemUserDO.class));
        serviceImpl.modifyUser(new UserModifyParam());
        Mockito.verify(systemUserConverter).param2do(Mockito.any(UserModifyParam.class));
        Mockito.verify(systemUserDAO).updateSystemUser(Mockito.any(SystemUserDO.class));
    }

    @Test
    public void disableUserTest() {
        Mockito.doNothing().when(systemUserDAO).updateSystemUser(Mockito.any(SystemUserDO.class));
        serviceImpl.disableUser("456");
        Mockito.verify(systemUserDAO).updateSystemUser(Mockito.any(SystemUserDO.class));
    }

    @Test
    public void enableUserTest() {
        Mockito.doNothing().when(systemUserDAO).updateSystemUser(Mockito.any(SystemUserDO.class));
        serviceImpl.enableUser("456");
        Mockito.verify(systemUserDAO).updateSystemUser(Mockito.any(SystemUserDO.class));
    }

    @Test
    public void getAllUsersTest() {
        SystemUserDO systemUserDO = new SystemUserDO();
        Mockito.doReturn(Arrays.asList(systemUserDO)).when(systemUserDAO).listAllSystemUsers();
        Mockito.doReturn(Arrays.asList(new SystemUserDTO())).when(systemUserConverter).do2dto(Mockito.anyList());
        serviceImpl.getAllUsers();
        Mockito.verify(systemUserDAO).listAllSystemUsers();
        Mockito.verify(systemUserConverter).do2dto(Mockito.anyList());
    }

    @Test
    public void getUsersTest() {
        UserPageQueryParam param = new UserPageQueryParam();
        param.setPageNum(1);
        param.setPageSize(10);
        Mockito.doReturn(new UserPageQuery()).when(userQueryConverter).param2query(Mockito.any(UserPageQueryParam.class));
        Mockito.doReturn(Arrays.asList(new SystemUserDO())).when(systemUserDAO).listAllSystemUsersByConditions(Mockito.any(UserPageQuery.class));
        Mockito.doReturn(Arrays.asList(new SystemUserDTO())).when(systemUserConverter).do2dto(Mockito.anyList());
        serviceImpl.getUsers(param);
        Mockito.verify(userQueryConverter).param2query(Mockito.any(UserPageQueryParam.class));
        Mockito.verify(systemUserDAO).listAllSystemUsersByConditions(Mockito.any(UserPageQuery.class));
        Mockito.verify(systemUserConverter).do2dto(Mockito.anyList());
    }

    @Test
    public void getUserTest() {
        Mockito.doReturn(new SystemUserDO()).when(systemUserDAO).getSystemUserById(Mockito.anyString());
        Mockito.doReturn(new SystemUserDTO()).when(systemUserConverter).do2dto(Mockito.any(SystemUserDO.class));
        serviceImpl.getUser("4567");
        Mockito.verify(systemUserDAO).getSystemUserById(Mockito.anyString());
        Mockito.verify(systemUserConverter).do2dto(Mockito.any(SystemUserDO.class));
    }

    @Test
    public void getUserByUserNameTest() {
        Mockito.doReturn(new SystemUserDO()).when(systemUserDAO).getSystemUserByUserName(Mockito.anyString());
        Mockito.doReturn(new SystemUserDTO()).when(systemUserConverter).do2dto(Mockito.any(SystemUserDO.class));
        serviceImpl.getUserByUserName("4567");
        Mockito.verify(systemUserDAO).getSystemUserByUserName(Mockito.anyString());
        Mockito.verify(systemUserConverter).do2dto(Mockito.any(SystemUserDO.class));
    }

    @Test
    public void resetPasswordTest() {
        Mockito.doReturn(new SystemUserDO()).when(systemUserConverter).param2do(Mockito.any(ResetPasswordParam.class));
        Mockito.doNothing().when(systemUserDAO).updateSystemUser(Mockito.any(SystemUserDO.class));
        Mockito.doNothing().when(guavaCacheUtil).removeUserDetails(null);
        serviceImpl.resetPassword(new ResetPasswordParam());
        Mockito.verify(systemUserDAO).updateSystemUser(Mockito.any(SystemUserDO.class));
        Mockito.verify(systemUserConverter).param2do(Mockito.any(ResetPasswordParam.class));
        Mockito.verify(guavaCacheUtil).removeUserDetails(null);

    }

    @Test
    public void assignRoleForUserTest() {
        AssignRoleParam param = Mockito.mock(AssignRoleParam.class);
        Mockito.doReturn("12306").when(param).getUserId();
        Mockito.doReturn(new HashSet<String>(Arrays.asList("role"))).doReturn(new HashSet<String>()).when(param).getRoleIdList();
        Mockito.doReturn(1).when(systemUserRoleDAO).removeSystemUserRoleByUserId(Mockito.anyString());
        Mockito.doReturn(1).when(systemUserRoleDAO).saveSystemUserRoleList(Mockito.anyList());

        serviceImpl.assignRoleForUser(param);
        Mockito.verify(param).getRoleIdList();
        Mockito.verify(systemUserRoleDAO).removeSystemUserRoleByUserId(Mockito.anyString());
        Mockito.verify(systemUserRoleDAO).saveSystemUserRoleList(Mockito.anyList());
        serviceImpl.assignRoleForUser(param);
    }

    @Test
    public void getAllRolesAndUserRolesTest() {
        Mockito.doReturn(Arrays.asList(new SystemRoleDO())).when(systemRoleDAO).listAllSystemRoles();
        Mockito.doReturn(Arrays.asList(new SystemRoleDTO())).when(systemRoleConverter).do2dto(Mockito.anyList());
        Mockito.doReturn(Arrays.asList("role")).when(systemUserRoleDAO).listRoleIdByUserId(Mockito.anyString());
        serviceImpl.getAllRolesAndUserRoles("123456");
        Mockito.verify(systemRoleDAO).listAllSystemRoles();
        Mockito.verify(systemRoleConverter).do2dto(Mockito.anyList());
        Mockito.verify(systemUserRoleDAO).listRoleIdByUserId(Mockito.anyString());
    }

    @Test
    public void verifyPasswordTest() {
        Mockito.doReturn("test").when(systemUserDAO).getPasswordById(Mockito.anyString());
        Mockito.doReturn(true).doReturn(false).when(passwordEncoder).matches(Mockito.anyString(), Mockito.anyString());
        serviceImpl.verifyPassword("123456", "test");
        Mockito.verify(systemUserDAO).getPasswordById(Mockito.anyString());
        Mockito.verify(passwordEncoder).matches(Mockito.anyString(), Mockito.anyString());

        try {
            serviceImpl.verifyPassword("123456", "test");
        } catch (Exception e) {
            assertEquals("密码错误", e.getMessage());
        }
    }

    @Test
    public void changePasswordTest() {

        Mockito.doNothing().when(systemUserDAO).updateSystemUser(Mockito.any(SystemUserDO.class));
        Mockito.doNothing().when(guavaCacheUtil).removeUserDetails(Mockito.anyString());
        serviceImpl.changePassword("123456", "test");
        Mockito.verify(systemUserDAO).updateSystemUser(Mockito.any(SystemUserDO.class));
        Mockito.verify(guavaCacheUtil).removeUserDetails(Mockito.anyString());
    }
}
