package org.talend.esbconsole.server.web.api.controller.authority;

import org.talend.esbconsole.server.domain.api.model.RouteAndAuthorityModel;
import org.talend.esbconsole.server.domain.api.model.SystemAuthorityDTO;
import org.talend.esbconsole.server.domain.api.service.SystemAuthorityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link AuthorityController} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AuthorityControllerTest {

    private static final String URL = "/api/user/authority";
    @Mock
    SystemAuthorityService systemAuthorityService;
    @InjectMocks
    AuthorityController authorityController;
    MockMvc mvc;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);

        //构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(authorityController).build();
    }


    @Test
    public void getAuthorityTest() throws Exception {
        SystemAuthorityDTO authority = new SystemAuthorityDTO();
        when(systemAuthorityService.getAuthority(Mockito.any())).thenReturn(authority);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/getAuthority")
                        .param("id", "54654156"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(systemAuthorityService).getAuthority(Mockito.any());
    }

    @Test
    public void getAllAuthoritiesTest() throws Exception {
        List<SystemAuthorityDTO> allAuthorities = new ArrayList<SystemAuthorityDTO>();
        when(systemAuthorityService.getAllAuthorities()).thenReturn(allAuthorities);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/listAll"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(systemAuthorityService).getAllAuthorities();
    }

    @Test
    public void getAllAuthoritiesTreeTest() throws Exception {
        List<RouteAndAuthorityModel> allAuthoritiesTree = new ArrayList<RouteAndAuthorityModel>();
        when(systemAuthorityService.getAllAuthoritiesTree()).thenReturn(allAuthoritiesTree);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/listAllTree"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(systemAuthorityService).getAllAuthoritiesTree();

    }

}
