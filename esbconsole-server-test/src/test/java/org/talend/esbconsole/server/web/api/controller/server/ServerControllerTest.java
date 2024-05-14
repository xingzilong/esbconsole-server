package org.talend.esbconsole.server.web.api.controller.server;

import org.talend.esbconsole.server.domain.api.model.JVMDetails;
import org.talend.esbconsole.server.domain.api.model.JVMInfo;
import org.talend.esbconsole.server.domain.api.service.JVMService;
import org.talend.esbconsole.server.web.api.controller.server.converter.ServerWebConverter;
import org.talend.esbconsole.server.web.api.controller.server.service.Server;
import org.talend.esbconsole.server.web.api.controller.server.vo.JVMInfoVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.MockedConstructionImpl;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link ServerController} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class ServerControllerTest {

    private static final String PATH = "/api/system/server";

    private MockMvc mockMvc;

    @Mock
    private JVMService jvmService;

    @Mock
    private ServerWebConverter serverWebConverter;

    @InjectMocks
    private ServerController serverController;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
        //构建mvc环境
        mockMvc = MockMvcBuilders.standaloneSetup(serverController).build();
    }

    @Test
    public void getServerInfoTest() throws Exception {
        MockedConstructionImpl<Server> mockConstruction = (MockedConstructionImpl<Server>) Mockito.mockConstruction(Server.class, (mock, context) -> {
            Mockito.doNothing().when(mock).copyTo();
        });
        mockMvc.perform(get(PATH + "/getServerInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        mockConstruction.close();

        MockedConstructionImpl<Server> mockConstructionException = (MockedConstructionImpl<Server>) Mockito.mockConstruction(Server.class, (mock, context) -> {
            Mockito.doThrow(new RuntimeException("mock-exception")).when(mock).copyTo();
        });

        try {
            mockMvc.perform(get(PATH + "/getServerInfo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
//			assertEquals("Request processing failed; nested exception is authentication.exception.org.talend.esbconsole.server.tools.base.LoginException: test", e.getMessage());
        } finally {
            mockConstructionException.close();
        }
    }

    @Test
    public void getESBServerJVMMemoryInfoTest() throws Exception {
        JVMInfo esbServerJVMMemory = Mockito.mock(JVMInfo.class);
        JVMInfoVO jvmInfoVO = Mockito.mock(JVMInfoVO.class);
        Mockito.doReturn(esbServerJVMMemory).when(jvmService).getESBServerJVMMemory();
        Mockito.doReturn(jvmInfoVO).when(serverWebConverter).dto2vo(esbServerJVMMemory);
        mockMvc.perform(get(PATH + "/getJVMMemoryInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(jvmService).getESBServerJVMMemory();
        Mockito.verify(serverWebConverter).dto2vo(esbServerJVMMemory);
    }

    @Test
    public void getESBServerJVMInfoTest() throws Exception {
        JVMDetails jvmDetails = Mockito.mock(JVMDetails.class);
        Mockito.doReturn(jvmDetails).when(jvmService).getESBServerJVMInfo();
        mockMvc.perform(get(PATH + "/getJVMDetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(jvmService).getESBServerJVMInfo();
    }

}
