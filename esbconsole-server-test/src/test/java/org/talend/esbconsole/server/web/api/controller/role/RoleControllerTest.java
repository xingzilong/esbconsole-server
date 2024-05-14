package org.talend.esbconsole.server.web.api.controller.role;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.talend.esbconsole.server.domain.api.model.AuthorityOtherInfoDTO;
import org.talend.esbconsole.server.domain.api.model.SystemRoleDTO;
import org.talend.esbconsole.server.domain.api.model.SystemUserModel;
import org.talend.esbconsole.server.domain.api.param.AssignAuthorityParam;
import org.talend.esbconsole.server.domain.api.param.RoleCreateParam;
import org.talend.esbconsole.server.domain.api.param.RoleModifyParam;
import org.talend.esbconsole.server.domain.api.param.RolePageQueryParam;
import org.talend.esbconsole.server.domain.api.service.SystemRoleService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.role.converter.RoleWebConverter;
import org.talend.esbconsole.server.web.api.controller.role.request.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.talend.esbconsole.server.web.api.controller.role.request.AssignAuthorityRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RoleCreateRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RoleModifyRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RolePageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RoleRemoveRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link RoleController} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class RoleControllerTest {

    private static final String PATH = "/api/user/role";

    private MockMvc mockMvc;

    @Mock
    private SystemRoleService systemRoleService;

    @Mock
    private RoleWebConverter roleWebConverter;

    @InjectMocks
    private RoleController roleController;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
        //构建mvc环境
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    public void getRoleTest() throws Exception {
        SystemRoleDTO systemRoleDTO = Mockito.mock(SystemRoleDTO.class);
        Mockito.doReturn(systemRoleDTO).when(systemRoleService).getRole(Mockito.anyString());
        mockMvc.perform(get(PATH + "/getRole")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).getRole(Mockito.anyString());
    }

    @Test
    public void verifyRoleNameTest() throws Exception {
        SystemRoleDTO systemRoleDTO = Mockito.mock(SystemRoleDTO.class);
        Mockito.doReturn(systemRoleDTO).doReturn(null).when(systemRoleService).getRoleByRoleName(Mockito.anyString());
        mockMvc.perform(get(PATH + "/verify")
                        .param("roleName", "test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(systemRoleService).getRoleByRoleName(Mockito.anyString());

        mockMvc.perform(get(PATH + "/verify")
                        .param("roleName", "test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getAllRolesTest() throws Exception {
        SystemRoleDTO systemRoleDTO = Mockito.mock(SystemRoleDTO.class);
        List<SystemRoleDTO> allRoles = new ArrayList<SystemRoleDTO>();
        allRoles.add(systemRoleDTO);
        Mockito.doReturn(allRoles).doReturn(null).when(systemRoleService).getAllRoles();
        mockMvc.perform(get(PATH + "/listAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).getAllRoles();
    }

    @Test
    public void getRolesTest() throws Exception {
        SystemRoleDTO systemRoleDTO = Mockito.mock(SystemRoleDTO.class);
        PageResult<SystemRoleDTO> users = new PageResult<SystemRoleDTO>();
        users.setList(Arrays.asList(systemRoleDTO));
        RolePageQueryParam rolePageQueryParam = Mockito.mock(RolePageQueryParam.class);
        RolePageQueryRequest request = Mockito.mock(RolePageQueryRequest.class);
        Mockito.doReturn(rolePageQueryParam).when(roleWebConverter).req2param(Mockito.any(RolePageQueryRequest.class));
        Mockito.doReturn(users).doReturn(null).when(systemRoleService).getRoles(Mockito.any(RolePageQueryParam.class));
        mockMvc.perform(post(PATH + "/list")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).getRoles(Mockito.any(RolePageQueryParam.class));
        Mockito.verify(roleWebConverter).req2param(Mockito.any(RolePageQueryRequest.class));
    }

    @Test
    public void addRoleTest() throws Exception {
        RoleCreateParam roleCreateParam = Mockito.mock(RoleCreateParam.class);
        RoleCreateRequest request = new RoleCreateRequest();
        request.setName("test");
        request.setRoleName("testname");
        Mockito.doReturn(roleCreateParam).when(roleWebConverter).req2param(Mockito.any(RoleCreateRequest.class));
        Mockito.doNothing().when(systemRoleService).addRole(Mockito.any(RoleCreateParam.class));
        mockMvc.perform(post(PATH + "/add")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).addRole(Mockito.any(RoleCreateParam.class));
        Mockito.verify(roleWebConverter).req2param(Mockito.any(RoleCreateRequest.class));
    }

    @Test
    public void removeRoleTest() throws Exception {
        ArrayList<RoleRemoveRequest> request = new ArrayList<>();
        RoleRemoveRequest roleRemoveRequest = new RoleRemoveRequest();
        roleRemoveRequest.setId("asda123asda");
        request.add(roleRemoveRequest);
        Mockito.doNothing().when(systemRoleService).removeRole(Mockito.anyString());
        mockMvc.perform(delete(PATH + "/remove")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).removeRole(Mockito.anyString());
    }

    @Test
    public void modifyRoleTest() throws Exception {
        RoleModifyParam roleModifyParam = Mockito.mock(RoleModifyParam.class);
        RoleModifyRequest request = new RoleModifyRequest();
        request.setId("1");
        request.setName("test");
        request.setRoleName("testname");
        Mockito.doReturn(roleModifyParam).when(roleWebConverter).req2param(Mockito.any(RoleModifyRequest.class));
        Mockito.doNothing().when(systemRoleService).modifyRole(Mockito.any(RoleModifyParam.class));
        mockMvc.perform(put(PATH + "/modify")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).modifyRole(Mockito.any(RoleModifyParam.class));
        Mockito.verify(roleWebConverter).req2param(Mockito.any(RoleModifyRequest.class));
    }

    @Test
    public void disableRoleTest() throws Exception {
        Mockito.doNothing().when(systemRoleService).disableRole(Mockito.anyString());
        mockMvc.perform(put(PATH + "/disable")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).disableRole(Mockito.anyString());
    }

    @Test
    public void enableRoleTest() throws Exception {
        Mockito.doNothing().when(systemRoleService).enableRole(Mockito.anyString());
        mockMvc.perform(put(PATH + "/enable")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).enableRole(Mockito.anyString());
    }

    @Test
    public void getAuthorityOtherInfoTest() throws Exception {
        AuthorityOtherInfoDTO authorityOtherInfoDTO = Mockito.mock(AuthorityOtherInfoDTO.class);
        Mockito.doReturn(authorityOtherInfoDTO).when(systemRoleService).getAuthorityOtherInfo(Mockito.anyString());
        mockMvc.perform(get(PATH + "/getAuthorityOtherInfo")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).getAuthorityOtherInfo(Mockito.anyString());
    }

    @Test
    public void assignAuthorityForRoleTest() throws Exception {
        AssignAuthorityParam assignAuthorityParam = Mockito.mock(AssignAuthorityParam.class);
        AssignAuthorityRequest request = new AssignAuthorityRequest();
        request.setRoleId("1");
        request.setAuthorityIdList(new HashSet<>());
        Mockito.doReturn(assignAuthorityParam).when(roleWebConverter).req2param(Mockito.any(AssignAuthorityRequest.class));
        Mockito.doNothing().when(systemRoleService).assignAuthorityForRole(Mockito.any(AssignAuthorityParam.class));
        mockMvc.perform(post(PATH + "/assignAuthority")
                        .content(JSONObject.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).assignAuthorityForRole(Mockito.any(AssignAuthorityParam.class));
        Mockito.verify(roleWebConverter).req2param(Mockito.any(AssignAuthorityRequest.class));
    }

    @Test
    public void getInUsingTest() throws Exception {
        SystemUserModel systemUserModel = Mockito.mock(SystemUserModel.class);
        Mockito.doReturn(Arrays.asList(systemUserModel)).when(systemRoleService).listUserRoleByRoleId(Mockito.anyString());
        mockMvc.perform(get(PATH + "/getUserByRoleId")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).listUserRoleByRoleId(Mockito.anyString());
    }

    @Test
    public void cancelAuthorityTest() throws Exception {
        Mockito.doNothing().when(systemRoleService).removeSystemUserRoleByUserIdAndRoleId(Mockito.anyString(), Mockito.anyString());
        mockMvc.perform(delete(PATH + "/cancelAuthority")
                        .param("userId", "1")
                        .param("roleId", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(systemRoleService).removeSystemUserRoleByUserIdAndRoleId(Mockito.anyString(), Mockito.anyString());
    }

}
