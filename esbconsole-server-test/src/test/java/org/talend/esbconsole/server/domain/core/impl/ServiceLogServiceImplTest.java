package org.talend.esbconsole.server.domain.core.impl;

import com.alibaba.fastjson2.JSONArray;
import org.talend.esbconsole.server.domain.api.model.ServiceLogDTO;
import org.talend.esbconsole.server.domain.api.model.ServiceLogModel;
import org.talend.esbconsole.server.domain.api.model.message.HttpMessage;
import org.talend.esbconsole.server.domain.api.param.ServiceLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.ServiceLogPageQuery;
import org.talend.esbconsole.server.domain.core.converter.EventsConverter;
import org.talend.esbconsole.server.domain.core.converter.query.ServiceLogQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.EventsDO;
import org.talend.esbconsole.server.domain.repository.mapper.EventsDAO;
import org.talend.esbconsole.server.util.ReadFileUtil;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@link ServiceLogServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/12
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceLogServiceImplTest {

    @Mock
    private EventsDAO eventsDAO;

    @Mock
    private ServiceLogQueryConverter serviceLogQueryConverter;

    @Mock
    private EventsConverter eventsConverter;

    @InjectMocks
    private ServiceLogServiceImpl serviceLogService;

    @Test
    public void getServiceLogsTest() {
        ServiceLogPageQueryParam serviceLogPageQueryParam = Mockito.mock(ServiceLogPageQueryParam.class);
        ServiceLogPageQuery serviceLogPageQuery = new ServiceLogPageQuery();
        serviceLogPageQuery.setEiTimeStampSort("asc");
        List<String> uuIdList = Arrays.asList("123");
        String rs = ReadFileUtil.readJson("mockdata/servicelog.json");
        List<ServiceLogModel> serviceLogModelList = JSONArray.parseArray(rs, ServiceLogModel.class);
        Mockito.doReturn(serviceLogPageQuery).when(serviceLogQueryConverter).param2query(serviceLogPageQueryParam);
        Mockito.doReturn(uuIdList).when(eventsDAO).listREQMiFlowIdByConditions(serviceLogPageQuery);
        Mockito.doReturn(serviceLogModelList).when(eventsDAO).listEventsByMiFlowIdList(uuIdList);

        PageResult<ServiceLogDTO> serviceLogs = serviceLogService.getServiceLogs(serviceLogPageQueryParam);

        Mockito.verify(serviceLogQueryConverter).param2query(serviceLogPageQueryParam);
        Mockito.verify(eventsDAO).listREQMiFlowIdByConditions(serviceLogPageQuery);
        Mockito.verify(eventsDAO).listEventsByMiFlowIdList(uuIdList);

        assertNotNull(serviceLogs);

        // 排序覆盖
        serviceLogPageQuery.setEiTimeStampSort("desc");

        Mockito.doReturn(serviceLogPageQuery).when(serviceLogQueryConverter).param2query(serviceLogPageQueryParam);
        Mockito.doReturn(uuIdList).when(eventsDAO).listREQMiFlowIdByConditions(serviceLogPageQuery);
        Mockito.doReturn(serviceLogModelList).when(eventsDAO).listEventsByMiFlowIdList(uuIdList);

        PageResult<ServiceLogDTO> serviceLogs1 = serviceLogService.getServiceLogs(serviceLogPageQueryParam);

        assertNotNull(serviceLogs1);

        // 返回0条数据
        Mockito.doReturn(serviceLogPageQuery).when(serviceLogQueryConverter).param2query(serviceLogPageQueryParam);
        Mockito.doReturn(Arrays.asList()).when(eventsDAO).listREQMiFlowIdByConditions(serviceLogPageQuery);

        PageResult<ServiceLogDTO> serviceLogs2 = serviceLogService.getServiceLogs(serviceLogPageQueryParam);

        assertNotNull(serviceLogs2);
    }

    @Test
    public void getRequestMessageTest() {
        EventsDO eventsDO = Mockito.mock(EventsDO.class);
        HttpMessage httpMessage = Mockito.mock(HttpMessage.class);
        Mockito.doReturn(eventsDO).when(eventsDAO).getHttpMessage(Mockito.anyString(), Mockito.anyString());
        Mockito.doReturn(httpMessage).when(eventsConverter).do2dto(eventsDO);

        HttpMessage httpMessageRS = serviceLogService.getRequestMessage("id");

        Mockito.verify(eventsDAO).getHttpMessage(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(eventsConverter).do2dto(eventsDO);

        assertNotNull(httpMessageRS);
    }

    @Test
    public void getResponseMessageTest() {
        EventsDO eventsDO = Mockito.mock(EventsDO.class);
        HttpMessage httpMessage = Mockito.mock(HttpMessage.class);
        Mockito.doReturn(eventsDO).when(eventsDAO).getHttpMessage(Mockito.anyString(), Mockito.anyString());
        Mockito.doReturn(httpMessage).when(eventsConverter).do2dto(eventsDO);

        HttpMessage httpMessageRS = serviceLogService.getResponseMessage("id");

        Mockito.verify(eventsDAO).getHttpMessage(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(eventsConverter).do2dto(eventsDO);

        assertNotNull(httpMessageRS);
    }
}
