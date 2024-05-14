package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link EventsDO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/18
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EventsDOTest {

    public void init(EventsDO eventsDO) {
        eventsDO.setId(1L);
        eventsDO.setEiTimestamp(LocalDateTime.MIN);
        eventsDO.setEiEventType("test-name");
        eventsDO.setOrigCustomId("test-username");
        eventsDO.setOrigProcessId("test-password");
        eventsDO.setOrigHostname("test-email");
        eventsDO.setOrigPrincipal("test-number");
        eventsDO.setMiPortType("test");
        eventsDO.setMiOperationName("test");
        eventsDO.setMiMessageId("test");
        eventsDO.setMiFlowId("test");
        eventsDO.setMiTransportType("test");
        eventsDO.setMessageContent("test");
        eventsDO.setServiceKey("test");
        eventsDO.setHttpMethod("test");
        eventsDO.setUri("test");
        eventsDO.setQuerystring("test");
        eventsDO.setProtocol("test");
        eventsDO.setHttpHeaders("test");
        eventsDO.setConsumerIp("test");
        eventsDO.setHttpStatus(200);
        eventsDO.setResponseTime(16L);
        eventsDO.setFailureCause("test");
        eventsDO.setMessageType("test");
        eventsDO.setContentCut(false);
    }

    @Test
    public void test() {

        EventsDO eventsDO = new EventsDO();
        EventsDO eventsDO1 = new EventsDO();

        assertTrue(eventsDO.equals(eventsDO1));

        eventsDO.hashCode();
        eventsDO.toString();
        init(eventsDO);
        eventsDO.hashCode();

        EventsDO eventsDO2 = eventsDO;
        assertTrue(eventsDO2.equals(eventsDO));

        assertFalse(eventsDO1.equals(eventsDO));
        assertFalse(eventsDO1.equals(null));

        init(eventsDO1);
        assertTrue(eventsDO1.equals(eventsDO));

        eventsDO1.setId(11L);
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setEiTimestamp(LocalDateTime.MIN.plusDays(1L));
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setEiEventType("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setOrigCustomId("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setOrigProcessId("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setOrigHostname("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setOrigPrincipal("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setMiPortType("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setMiOperationName("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setMiMessageId("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setMiFlowId("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setMiTransportType("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setMessageContent("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setServiceKey("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setHttpMethod("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setUri("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setQuerystring("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setProtocol("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));


        init(eventsDO1);
        eventsDO1.setHttpHeaders("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setConsumerIp("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setHttpStatus(500);
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setResponseTime(160L);
        assertFalse(eventsDO1.equals(eventsDO));

        init(eventsDO1);
        eventsDO1.setFailureCause("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));


        init(eventsDO1);
        eventsDO1.setMessageType("test-systemnames");
        assertFalse(eventsDO1.equals(eventsDO));


        init(eventsDO1);
        eventsDO1.setContentCut(true);
        assertFalse(eventsDO1.equals(eventsDO));

    }
}
