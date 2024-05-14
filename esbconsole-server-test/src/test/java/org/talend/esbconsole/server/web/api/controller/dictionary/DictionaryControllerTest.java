package org.talend.esbconsole.server.web.api.controller.dictionary;

import com.alibaba.fastjson2.JSON;
import org.talend.esbconsole.server.domain.api.model.BusinessSystemDTO;
import org.talend.esbconsole.server.domain.api.model.ConsumerSystemDTO;
import org.talend.esbconsole.server.domain.api.param.BusinessCreateParam;
import org.talend.esbconsole.server.domain.api.param.BusinessModifyParam;
import org.talend.esbconsole.server.domain.api.param.BusinessPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerCreateParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerModifyParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerPageQueryParam;
import org.talend.esbconsole.server.domain.api.service.BusinessSystemService;
import org.talend.esbconsole.server.domain.api.service.ConsumerSystemService;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.web.api.controller.dictionary.converter.DictionaryWebConverter;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.*;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessCreateRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessModifyRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessRemoveRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerCreateRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerModifyRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerRemoveRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link DictionaryController} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class DictionaryControllerTest {

    private static final String URL = "/api/service/dictionary";

    @Mock
    ConsumerSystemService consumerSystemService;

    @Mock
    BusinessSystemService businessSystemService;

    @Mock
    DictionaryWebConverter dictionaryWebConverter;

    @InjectMocks
    DictionaryController dictionaryController;

    MockMvc mvc;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);

        //构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(dictionaryController).build();
    }

    @Test
    public void getConsumerTest() throws Exception {
        ConsumerSystemDTO consumerSystemDTO = new ConsumerSystemDTO();
        when(consumerSystemService.getConsumer(Mockito.any())).thenReturn(consumerSystemDTO);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/consumer/getConsumer")
                        .param("id", "456415"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(consumerSystemService).getConsumer(Mockito.any());
    }

    @Test
    public void verifyConsumerIpTest() throws Exception {
        ConsumerSystemDTO consumerSystemDTO = new ConsumerSystemDTO();
        when(consumerSystemService.getConsumerByIp(Mockito.any())).thenReturn(consumerSystemDTO);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/consumer/verify")
                        .param("ip", "456415"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(consumerSystemService).getConsumerByIp(Mockito.any());

        consumerSystemDTO = null;
        when(consumerSystemService.getConsumerByIp(Mockito.any())).thenReturn(consumerSystemDTO);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/consumer/verify")
                        .param("ip", "456415"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());
    }

    @Test
    public void getConsumersTest() throws Exception {
        ConsumerPageQueryRequest request = new ConsumerPageQueryRequest();
        request.setIp("127.0.0.1");
        request.setPageNum(1);
        request.setPageSize(10);

        ConsumerPageQueryParam consumerPageQueryParam = new ConsumerPageQueryParam();
        when(dictionaryWebConverter.req2param(request)).thenReturn(consumerPageQueryParam);

        PageResult<ConsumerSystemDTO> consumersPageInfo = new PageResult();
        when(consumerSystemService.getConsumers(consumerPageQueryParam)).thenReturn(consumersPageInfo);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/consumer/list")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(dictionaryWebConverter).req2param(request);
        verify(consumerSystemService).getConsumers(consumerPageQueryParam);
    }

    @Test
    public void addConsumerTest() throws Exception {
        ConsumerCreateRequest request = new ConsumerCreateRequest();
        request.setIp("127.0.0.1");
        request.setResponsiblePerson("admin");
        request.setSystemName("test");

        ConsumerCreateParam consumerCreateParam = new ConsumerCreateParam();
        when(dictionaryWebConverter.req2param(request)).thenReturn(consumerCreateParam);

        doNothing().when(consumerSystemService).addConsumer(consumerCreateParam);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/consumer/add")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(dictionaryWebConverter).req2param(request);
        verify(consumerSystemService).addConsumer(consumerCreateParam);
    }

    @Test
    public void removeConsumerTest() throws Exception {
        List<ConsumerRemoveRequest> request = new ArrayList<>();
        ConsumerRemoveRequest consumerRemoveRequest = new ConsumerRemoveRequest();
        consumerRemoveRequest.setId("12adasd143");
        request.add(consumerRemoveRequest);
        doNothing().when(consumerSystemService).removeConsumer(Mockito.anyString());

        mvc.perform(MockMvcRequestBuilders.delete(URL + "/consumer/remove")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(consumerSystemService).removeConsumer(Mockito.anyString());
    }

    @Test
    public void modifyConsumerTest() throws Exception {
        ConsumerModifyRequest request = new ConsumerModifyRequest();
        request.setId("54613");
        request.setIp("127.0.0.1");
        request.setSystemName("test");
        request.setResponsiblePerson("admin");

        ConsumerModifyParam consumerModifyParam = new ConsumerModifyParam();
        when(dictionaryWebConverter.req2param(request)).thenReturn(consumerModifyParam);

        doNothing().when(consumerSystemService).modifyConsumer(consumerModifyParam);

        mvc.perform(MockMvcRequestBuilders.put(URL + "/consumer/modify")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(dictionaryWebConverter).req2param(request);
        verify(consumerSystemService).modifyConsumer(consumerModifyParam);
    }

    @Test
    public void disableConsumerTest() throws Exception {
        doNothing().when(consumerSystemService).disableConsumer(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.put(URL + "/consumer/disable")
                        .param("id", "12313"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(consumerSystemService).disableConsumer(Mockito.any());
    }

    @Test
    public void enableConsumerTest() throws Exception {
        doNothing().when(consumerSystemService).enableConsumer(Mockito.any());
        mvc.perform(MockMvcRequestBuilders.put(URL + "/consumer/enable")
                        .param("id", "1651")
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(consumerSystemService).enableConsumer(Mockito.any());
    }

    @Test
    public void getBusinessById() throws Exception {
        BusinessSystemDTO businessSystemDTO = new BusinessSystemDTO();

        when(businessSystemService.getBusiness(Mockito.any())).thenReturn(businessSystemDTO);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/business/getBusinessById")
                        .param("id", "12313"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(businessSystemService).getBusiness(Mockito.any());
    }

    @Test
    public void verifyBusinessSystemNameTest() throws Exception {
        BusinessSystemDTO businessSystemDTO = new BusinessSystemDTO();
        when(businessSystemService.getBusinessByBusinessName(Mockito.any())).thenReturn(businessSystemDTO);
        mvc.perform(MockMvcRequestBuilders.get(URL + "/business/verify")
                        .param("systemName", "test"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(businessSystemService).getBusinessByBusinessName(Mockito.any());

        businessSystemDTO = null;
        when(businessSystemService.getBusinessByBusinessName(Mockito.any())).thenReturn(businessSystemDTO);
        mvc.perform(MockMvcRequestBuilders.get(URL + "/business/verify")
                        .param("systemName", "test"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void getBusinesssTest() throws Exception {
        BusinessPageQueryRequest request = new BusinessPageQueryRequest();
        request.setPageSize(10);
        request.setPageNum(1);

        BusinessPageQueryParam businessPageQueryParam = new BusinessPageQueryParam();
        when(dictionaryWebConverter.req2param(request)).thenReturn(businessPageQueryParam);

        PageResult<BusinessSystemDTO> businesssPageInfo = new PageResult();
        when(businessSystemService.getBusinesss(businessPageQueryParam)).thenReturn(businesssPageInfo);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/business/list")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(dictionaryWebConverter).req2param(request);
        verify(businessSystemService).getBusinesss(businessPageQueryParam);
    }

    @Test
    public void getAllBusinessSystemNameTest() throws Exception {
        List<BusinessSystemDTO> allBusinessSystemName = new ArrayList<BusinessSystemDTO>();
        when(businessSystemService.getAllBusinessSystemName()).thenReturn(allBusinessSystemName);

        mvc.perform(MockMvcRequestBuilders.get(URL + "/business/listAllBSN"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(businessSystemService).getAllBusinessSystemName();
    }

    @Test
    public void addBusinessTest() throws Exception {
        BusinessCreateRequest request = new BusinessCreateRequest();
        request.setSystemName("test");
        request.setResponsiblePerson("admin");

        BusinessCreateParam businessCreateParam = new BusinessCreateParam();
        when(dictionaryWebConverter.req2param(request)).thenReturn(businessCreateParam);

        doNothing().when(businessSystemService).addBusiness(businessCreateParam);

        mvc.perform(MockMvcRequestBuilders.post(URL + "/business/add")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(dictionaryWebConverter).req2param(request);
        verify(businessSystemService).addBusiness(businessCreateParam);
    }

    @Test
    public void removeBusinessTest() throws Exception {
        List<BusinessRemoveRequest> request = new ArrayList<>();
        BusinessRemoveRequest businessRemoveRequest = new BusinessRemoveRequest();
        businessRemoveRequest.setId("12adasd143");
        request.add(businessRemoveRequest);
        doNothing().when(businessSystemService).removeBusiness(Mockito.anyString());
        mvc.perform(MockMvcRequestBuilders.delete(URL + "/business/remove")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(businessSystemService).removeBusiness(Mockito.anyString());
    }

    @Test
    public void modifyBusinessTest() throws Exception {
        BusinessModifyRequest request = new BusinessModifyRequest();
        request.setSystemName("test");
        request.setResponsiblePerson("admin");
        request.setId("1312");

        BusinessModifyParam businessModifyParam = new BusinessModifyParam();
        when(dictionaryWebConverter.req2param(request)).thenReturn(businessModifyParam);

        doNothing().when(businessSystemService).modifyBusiness(businessModifyParam);

        mvc.perform(MockMvcRequestBuilders.put(URL + "/business/modify")
                        .content(JSON.toJSONString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(dictionaryWebConverter).req2param(request);
        verify(businessSystemService).modifyBusiness(businessModifyParam);
    }

    @Test
    public void disableBusinessTest() throws Exception {
        doNothing().when(businessSystemService).disableBusiness(Mockito.any());
        mvc.perform(MockMvcRequestBuilders.put(URL + "/business/disable")
                        .param("id", "42131"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(businessSystemService).disableBusiness(Mockito.any());
    }

    @Test
    public void enableBusinessTest() throws Exception {
        doNothing().when(businessSystemService).enableBusiness(Mockito.any());
        mvc.perform(MockMvcRequestBuilders.put(URL + "/business/enable")
                        .param("id", "561313"))
                .andExpect(status().isOk()) //验证码状态
                .andDo(print());

        verify(businessSystemService).enableBusiness(Mockito.any());
    }
}
