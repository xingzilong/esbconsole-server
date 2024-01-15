package org.talend.esbconsole.server.start.authentication;

import com.alibaba.fastjson2.JSONObject;
import org.talend.esbconsole.server.domain.api.model.RouterDTO;
import org.talend.esbconsole.server.domain.api.model.UserInfoDTO;
import org.talend.esbconsole.server.domain.api.param.LoginUserParam;
import org.talend.esbconsole.server.domain.api.service.SystemLoginService;
import org.talend.esbconsole.server.domain.core.util.LoginUserDetails;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.talend.esbconsole.server.start.authentication.converter.AuthenticationWebConverter;
import org.talend.esbconsole.server.start.authentication.request.LoginUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link SystemLoginController} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/8
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class SystemLoginControllerTest {

    private static final String PATH = "/api/authentication";

    private MockMvc mockMvc;

    @Mock
    private SystemLoginService systemLoginService;

    @Mock
    private AuthenticationWebConverter authenticationWebConverter;

    @InjectMocks
    private SystemLoginController systemLoginController;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
        //构建mvc环境
        mockMvc = MockMvcBuilders.standaloneSetup(systemLoginController).build();
    }

    @Test
    public void loginTest() throws Exception {
        LoginUserRequest request = Mockito.mock(LoginUserRequest.class);
        LoginUserParam loginUserParam = Mockito.mock(LoginUserParam.class);
        Mockito.doReturn(loginUserParam).when(authenticationWebConverter).req2param(Mockito.any(LoginUserRequest.class));
        Mockito.doReturn("mock-token:mock测试").doThrow(new DisabledException("mock-exception")).when(systemLoginService).login(Mockito.any(LoginUserParam.class));
        mockMvc.perform(post(PATH + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(authenticationWebConverter).req2param(Mockito.any(LoginUserRequest.class));
        Mockito.verify(systemLoginService).login(loginUserParam);

        try {
            mockMvc.perform(post(PATH + "/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JSONObject.toJSONString(request))
                    .accept(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
//			assertEquals("Request processing failed; nested exception is authentication.exception.org.talend.esbconsole.server.tools.base.LoginException: test", e.getMessage());
        }
    }

    @Test
    public void logoutTest() throws Exception {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        LoginUserDetails loginUserDetailslogin = Mockito.mock(LoginUserDetails.class);
        SystemUserDO systemUserDO = Mockito.mock(SystemUserDO.class);
        MockedStatic<SecurityContextHolder> holder = Mockito.mockStatic(SecurityContextHolder.class);
        holder.when(() -> SecurityContextHolder.getContext()).thenReturn(securityContext);
        Mockito.doReturn(authentication).when(securityContext).getAuthentication();
        Mockito.doReturn(loginUserDetailslogin).when(authentication).getPrincipal();
        Mockito.doReturn(systemUserDO).when(loginUserDetailslogin).getSystemUser();
        Mockito.doReturn("649343fd-caf1-40fd-8f2d-8709131dbb7a").when(systemUserDO).getId();
        Mockito.doNothing().when(systemLoginService).logout(Mockito.anyString());
        mockMvc.perform(delete(PATH + "/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemLoginService).logout(Mockito.anyString());
        holder.close();
    }

    @Test
    public void getUserInfoTest() throws Exception {
        UserInfoDTO userInfoDTO = Mockito.mock(UserInfoDTO.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        LoginUserDetails loginUserDetailslogin = Mockito.mock(LoginUserDetails.class);
        SystemUserDO systemUserDO = Mockito.mock(SystemUserDO.class);
        MockedStatic<SecurityContextHolder> holder = Mockito.mockStatic(SecurityContextHolder.class);
        holder.when(() -> SecurityContextHolder.getContext()).thenReturn(securityContext);
        Mockito.doReturn(authentication).when(securityContext).getAuthentication();
        Mockito.doReturn(loginUserDetailslogin).when(authentication).getPrincipal();
        Mockito.doReturn(systemUserDO).when(loginUserDetailslogin).getSystemUser();
        Mockito.doReturn("649343fd-caf1-40fd-8f2d-8709131dbb7a").when(systemUserDO).getId();
        Mockito.doReturn(userInfoDTO).when(systemLoginService).getUserInfo(Mockito.anyString());
        mockMvc.perform(get(PATH + "/getUserInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemLoginService).getUserInfo(Mockito.anyString());
        holder.close();
    }

    @Test
    public void getRoutesTest() throws Exception {
        RouterDTO router = Mockito.mock(RouterDTO.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        LoginUserDetails loginUserDetails = Mockito.mock(LoginUserDetails.class);
        SystemUserDO systemUserDO = Mockito.mock(SystemUserDO.class);
        MockedStatic<SecurityContextHolder> holder = Mockito.mockStatic(SecurityContextHolder.class);
        holder.when(() -> SecurityContextHolder.getContext()).thenReturn(securityContext);
        Mockito.doReturn(authentication).when(securityContext).getAuthentication();
        Mockito.doReturn(loginUserDetails).when(authentication).getPrincipal();
        Mockito.doReturn(systemUserDO).when(loginUserDetails).getSystemUser();
        Mockito.doReturn("649343fd-caf1-40fd-8f2d-8709131dbb7a").when(systemUserDO).getId();
        Mockito.doReturn(Arrays.asList(router)).when(systemLoginService).getRouter(Mockito.anyString());
        mockMvc.perform(get(PATH + "/getRoutes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemLoginService).getRouter(Mockito.anyString());
        holder.close();
    }
}
