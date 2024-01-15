package org.talend.esbconsole.server.web.api.controller.user;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.talend.esbconsole.server.domain.api.model.SystemRoleDTO;
import org.talend.esbconsole.server.domain.api.model.SystemUserDTO;
import org.talend.esbconsole.server.domain.api.model.UserRolesDTO;
import org.talend.esbconsole.server.domain.api.param.AssignRoleParam;
import org.talend.esbconsole.server.domain.api.param.ResetPasswordParam;
import org.talend.esbconsole.server.domain.api.param.UserCreateParam;
import org.talend.esbconsole.server.domain.api.param.UserModifyParam;
import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;
import org.talend.esbconsole.server.domain.api.service.SystemUserService;
import org.talend.esbconsole.server.domain.core.util.LoginUserDetails;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.user.converter.UserWebConverter;
import org.talend.esbconsole.server.web.api.controller.user.request.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.talend.esbconsole.server.web.api.controller.user.request.AssignRoleRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.ResetPasswordRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserCreateRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserModifyRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserRemoveRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.VerifyAndChangePasswordRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link UserController} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/8
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    private final String PATH = "/api/user/user";
    @Mock
    private SystemUserService systemUserService;
    @Mock
    private UserWebConverter userWebConverter;
    @InjectMocks
    private UserController controller;
    private MockMvc mvc;

    @Before
    public void setup() {

        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getUserTest() throws Exception {

        SystemUserDTO user = Mockito.mock(SystemUserDTO.class);
        Mockito.doReturn(user).when(systemUserService).getUser(Mockito.anyString());
        this.mvc.perform(get(PATH + "/getUser")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).getUser(Mockito.anyString());
    }

    @Test
    public void verifyUserNameTest() throws Exception {
        SystemUserDTO user = Mockito.mock(SystemUserDTO.class);
        Mockito.doReturn(user).doReturn(null).when(systemUserService).getUserByUserName(Mockito.anyString());
        this.mvc.perform(get(PATH + "/verify")
                        .param("userName", "test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(systemUserService).getUserByUserName(Mockito.anyString());

        this.mvc.perform(get(PATH + "/verify")
                        .param("userName", "test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void getAllUsersTest() throws Exception {
        SystemUserDTO user = Mockito.mock(SystemUserDTO.class);
        List<SystemUserDTO> users = new ArrayList<SystemUserDTO>();
        users.add(user);
        Mockito.doReturn(users).doReturn(null).when(systemUserService).getAllUsers();
        this.mvc.perform(get(PATH + "/listAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).getAllUsers();
    }

    @Test
    public void getUsersTest() throws Exception {
        SystemUserDTO user = Mockito.mock(SystemUserDTO.class);
        PageResult<SystemUserDTO> users = new PageResult<SystemUserDTO>();
        users.setList(Arrays.asList(user));
        UserPageQueryParam param = Mockito.mock(UserPageQueryParam.class);
        UserPageQueryRequest request = Mockito.mock(UserPageQueryRequest.class);
        Mockito.doReturn(param).when(userWebConverter).req2param(Mockito.any(UserPageQueryRequest.class));
        Mockito.doReturn(users).doReturn(null).when(systemUserService).getUsers(Mockito.any(UserPageQueryParam.class));
        this.mvc.perform(post(PATH + "/list")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).getUsers(Mockito.any(UserPageQueryParam.class));
        Mockito.verify(userWebConverter).req2param(Mockito.any(UserPageQueryRequest.class));
    }

    @Test
    public void addUserTest() throws Exception {
        UserCreateParam param = Mockito.mock(UserCreateParam.class);
        UserCreateRequest request = new UserCreateRequest();
        request.setEmail("test@qq.com");
        request.setName("test");
        request.setPassword("Wkt456s");
        request.setPhoneNumber("19816784923");
        request.setUserName("ssss");
        Mockito.doReturn(param).when(userWebConverter).req2param(Mockito.any(UserCreateRequest.class));
        Mockito.doNothing().when(systemUserService).addUser(Mockito.any(UserCreateParam.class));
        this.mvc.perform(post(PATH + "/add")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).addUser(Mockito.any(UserCreateParam.class));
        Mockito.verify(userWebConverter).req2param(Mockito.any(UserCreateRequest.class));
    }

    @Test
    public void removeUserTest() throws Exception {
        List<UserRemoveRequest> request = new ArrayList<>();
        UserRemoveRequest userRemoveRequest = new UserRemoveRequest();
        userRemoveRequest.setId("12adasd143");
        request.add(userRemoveRequest);
        Mockito.doNothing().when(systemUserService).removeUser(Mockito.anyString());
        this.mvc.perform(delete(PATH + "/remove")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).removeUser(Mockito.anyString());
    }

    @Test
    public void modifyUserTest() throws Exception {
        UserModifyParam param = Mockito.mock(UserModifyParam.class);
        UserModifyRequest request = new UserModifyRequest();
        request.setEmail("test@qq.com");
        request.setName("test");
        request.setId("1");
        request.setPhoneNumber("19816784923");
        request.setUserName("ssss");
        Mockito.doReturn(param).when(userWebConverter).req2param(Mockito.any(UserModifyRequest.class));
        Mockito.doNothing().when(systemUserService).modifyUser(Mockito.any(UserModifyParam.class));
        this.mvc.perform(put(PATH + "/modify")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).modifyUser(Mockito.any(UserModifyParam.class));
        Mockito.verify(userWebConverter).req2param(Mockito.any(UserModifyRequest.class));
    }

    @Test
    public void disableUserTest() throws Exception {
        Mockito.doNothing().when(systemUserService).disableUser(Mockito.anyString());
        this.mvc.perform(put(PATH + "/disable")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).disableUser(Mockito.anyString());
    }

    @Test
    public void enableUserTest() throws Exception {
        Mockito.doNothing().when(systemUserService).enableUser(Mockito.anyString());
        this.mvc.perform(put(PATH + "/enable")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).enableUser(Mockito.anyString());
    }

    @Test
    public void resetPasswordTest() throws Exception {
        ResetPasswordParam param = Mockito.mock(ResetPasswordParam.class);
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setId("1");
        request.setPassword("Wra456ss");
        Mockito.doReturn(param).when(userWebConverter).req2param(Mockito.any(ResetPasswordRequest.class));
        Mockito.doNothing().when(systemUserService).resetPassword(Mockito.any(ResetPasswordParam.class));
        this.mvc.perform(put(PATH + "/resetPassword")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).resetPassword(Mockito.any(ResetPasswordParam.class));
        Mockito.verify(userWebConverter).req2param(Mockito.any(ResetPasswordRequest.class));
    }

    @Test
    public void getRoleTest() throws Exception {
        SystemRoleDTO role = Mockito.mock(SystemRoleDTO.class);
        UserRolesDTO dto = new UserRolesDTO();
        dto.setAllRoles(Arrays.asList(role));
        dto.setRoleIdList(Arrays.asList("1"));
        Mockito.doReturn(dto).when(systemUserService).getAllRolesAndUserRoles(Mockito.anyString());
        this.mvc.perform(get(PATH + "/getUserRoles")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).getAllRolesAndUserRoles(Mockito.anyString());
    }

    @Test
    public void assignRoleForUserTest() throws Exception {
        AssignRoleParam param = Mockito.mock(AssignRoleParam.class);
        AssignRoleRequest request = new AssignRoleRequest();
        request.setUserId("1");
        request.setRoleIdList(new HashSet<String>(Arrays.asList("ssss")));
        Mockito.doReturn(param).when(userWebConverter).req2param(Mockito.any(AssignRoleRequest.class));
        Mockito.doNothing().when(systemUserService).assignRoleForUser(Mockito.any(AssignRoleParam.class));
        this.mvc.perform(post(PATH + "/assignRole")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).assignRoleForUser(Mockito.any(AssignRoleParam.class));
        Mockito.verify(userWebConverter).req2param(Mockito.any(AssignRoleRequest.class));
    }

    @Test
    public void verifyPasswordTest() throws Exception {
        VerifyAndChangePasswordRequest request = new VerifyAndChangePasswordRequest();
        request.setPassword("wrds456S");
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        LoginUserDetails login = Mockito.mock(LoginUserDetails.class);
        SystemUserDO user = Mockito.mock(SystemUserDO.class);
        MockedStatic<SecurityContextHolder> holder = Mockito.mockStatic(SecurityContextHolder.class);
        holder.when(() -> SecurityContextHolder.getContext()).thenReturn(context);
        Mockito.doReturn(auth).when(context).getAuthentication();
        Mockito.doReturn(login).when(auth).getPrincipal();
        Mockito.doReturn(user).when(login).getSystemUser();
        Mockito.doReturn("1").when(user).getId();
        Mockito.doNothing().when(systemUserService).verifyPassword(Mockito.anyString(), Mockito.anyString());
        this.mvc.perform(post(PATH + "/verifyPassword")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemUserService).verifyPassword(Mockito.anyString(), Mockito.anyString());

        Mockito.doNothing().when(systemUserService).changePassword(Mockito.anyString(), Mockito.anyString());
        this.mvc.perform(post(PATH + "/changePassword")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(systemUserService).changePassword(Mockito.anyString(), Mockito.anyString());
        holder.close();
    }

}
