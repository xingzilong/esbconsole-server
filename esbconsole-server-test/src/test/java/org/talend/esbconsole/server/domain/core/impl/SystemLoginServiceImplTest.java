package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.RouteModel;
import org.talend.esbconsole.server.domain.api.model.RouterDTO;
import org.talend.esbconsole.server.domain.api.model.UserInfoDTO;
import org.talend.esbconsole.server.domain.api.param.LoginUserParam;
import org.talend.esbconsole.server.domain.api.service.RouterService;
import org.talend.esbconsole.server.domain.core.util.GuavaCacheUtil;
import org.talend.esbconsole.server.domain.core.util.LoginUserDetails;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemAuthorityDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link SystemLoginServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemLoginServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private GuavaCacheUtil guavaCacheUtil;

    @Mock
    private RouterService routerService;

    @Mock
    private SystemAuthorityDAO systemAuthorityDAO;

    @Mock
    private SystemUserDAO systemUserDAO;

    @InjectMocks
    private SystemLoginServiceImpl serviceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loginTest() {
        LoginUserParam param = new LoginUserParam();
        param.setPassword("wps123456");
        param.setUserName("test");
        Authentication au = Mockito.mock(Authentication.class);
        Mockito.doReturn(au).doReturn(null).when(authenticationManager).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        LoginUserDetails details = Mockito.mock(LoginUserDetails.class);
        SystemUserDO user = new SystemUserDO();
        user.setId("123456");
        Mockito.doReturn(details).when(au).getPrincipal();
        Mockito.doReturn(user).when(details).getSystemUser();
        Mockito.doNothing().when(guavaCacheUtil).setUserDetails(Mockito.anyString(), Mockito.any(LoginUserDetails.class));
        serviceImpl.login(param);
        Mockito.verify(authenticationManager).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        Mockito.verify(guavaCacheUtil).setUserDetails(Mockito.anyString(), Mockito.any(LoginUserDetails.class));
        try {
            serviceImpl.login(param);
        } catch (Exception e) {
            assertEquals("登录失败", e.getMessage());
        }
    }

    @Test
    public void logoutTest() {
        Mockito.doNothing().when(guavaCacheUtil).removeUserDetails(Mockito.anyString());
        serviceImpl.logout("123456");
        Mockito.verify(guavaCacheUtil).removeUserDetails(Mockito.anyString());
    }

    @Test
    public void getRouterTest() {

        RouteModel model = new RouteModel();
        RouteModel router = new RouteModel();
        model.setChildren(Arrays.asList(new RouteModel()));
        Mockito.doReturn(Arrays.asList(model, router)).when(routerService).getRouteListToTreeByUserId(Mockito.anyString());
        List<RouterDTO> result = serviceImpl.getRouter("role");
        assertEquals(2, result.size());
        Mockito.verify(routerService).getRouteListToTreeByUserId(Mockito.anyString());
    }

    @Test
    public void getUserInfoTest() {
        Mockito.doReturn("test").when(systemUserDAO).getUserNameById(Mockito.anyString());
        Mockito.doReturn(new HashSet<String>()).when(systemAuthorityDAO).listAuthorityKeyByUserId(Mockito.anyString());
        UserInfoDTO dto = serviceImpl.getUserInfo("test");
        assertEquals("test", dto.getUserName());
        Mockito.verify(systemUserDAO).getUserNameById(Mockito.anyString());
        Mockito.verify(systemAuthorityDAO).listAuthorityKeyByUserId(Mockito.anyString());
    }

    @Test
    public void getRouterPathTest() {

        RouteModel model = Mockito.mock(RouteModel.class);
        Mockito.doReturn("test").when(model).getRoutePath();
        Mockito.doReturn(0L).doReturn(1L).when(model).getParentId();
        assertEquals("/test", serviceImpl.getRouterPath(model));
        assertEquals("test", serviceImpl.getRouterPath(model));
    }

}
