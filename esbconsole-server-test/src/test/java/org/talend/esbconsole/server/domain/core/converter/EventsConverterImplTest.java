package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.message.HttpMessage;
import org.talend.esbconsole.server.domain.repository.entity.EventsDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link EventsConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EventsConverterImplTest {

    @InjectMocks
    private EventsConverterImpl eventsConverterImpl;

    @Test
    public void do2dtoTest() {
        EventsDO eventsDO = new EventsDO();
        eventsDO.setHttpStatus(200);
        eventsDO.setUri("/api/query/test");
        eventsDO.setQuerystring("name=test&age=18");
        eventsDO.setHttpHeaders("{\"Content-Length\" : \"12306\", \"Server\" : \"Apache\"}");
        eventsDO.setMessageContent("这是消息内容");
        HttpMessage httpMessage = eventsConverterImpl.do2dto(eventsDO);
        assertNotNull(httpMessage);
        eventsDO = null;
        HttpMessage httpMessage1 = eventsConverterImpl.do2dto(eventsDO);
        assertNull(httpMessage1);
    }

}
