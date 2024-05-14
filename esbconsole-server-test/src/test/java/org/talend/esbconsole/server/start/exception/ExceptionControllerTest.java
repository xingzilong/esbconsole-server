//package org.talend.esbconsole.server.start.exception;
//
//import request.authentication.org.talend.esbconsole.server.start.LoginUserRequest;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import javax.servlet.http.HttpServletRequest;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * {@link ExceptionController} 单元测试
// *
// * @author xingzilong
// * @date 2021/10/8
// */
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//@WebAppConfiguration
//public class ExceptionControllerTest {
//
//    private static final String PATH = "/api/exception";
//
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private ExceptionController exceptionController;
//
//    @Before
//    public void setUp() {
//        //初始化
//        MockitoAnnotations.openMocks(this);
//        //构建mvc环境
//        mockMvc = MockMvcBuilders.standaloneSetup(exceptionController).build();
//    }
//
//    @Test
//    public void authenticationExceptionTest() throws Exception {
//        Mockito.doNothing().when(exceptionController).authenticationException(Mockito.any(HttpServletRequest.class));
//
//        mockMvc.perform(get(PATH + "/authentication")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//        Mockito.verify(exceptionController).authenticationException(Mockito.any(HttpServletRequest.class));
//    }
//}
