package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceLogModel} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceLogModelTest {

    public void init(ServiceLogModel serviceLogModel) {
        serviceLogModel.setServiceName("test");
        serviceLogModel.setBusinessSystem("test-pid");
        serviceLogModel.setId(10L);
        serviceLogModel.setEiTimestamp(LocalDateTime.MIN);
        serviceLogModel.setEiEventType("test");
        serviceLogModel.setOrigCustomId("test");
        serviceLogModel.setOrigProcessId("test");
        serviceLogModel.setOrigHostname("test");
        serviceLogModel.setOrigIp("test");
        serviceLogModel.setOrigPrincipal("test");
        serviceLogModel.setMiPortType("test");
        serviceLogModel.setMiOperationName("test");
        serviceLogModel.setMiMessageId("test");
        serviceLogModel.setMiFlowId("test");
        serviceLogModel.setMiTransportType("test");
        serviceLogModel.setServiceKey("test");
        serviceLogModel.setHttpMethod("test");
        serviceLogModel.setConsumerIp("test");
        serviceLogModel.setHttpStatus(200);
        serviceLogModel.setResponseTime(10L);
        serviceLogModel.setFailureCause("test");
        serviceLogModel.setMessageType("test");
    }

    @Test
    public void test() {

        ServiceLogModel serviceLogModel = new ServiceLogModel();
        ServiceLogModel serviceLogModel1 = new ServiceLogModel();

        assertTrue(serviceLogModel.equals(serviceLogModel1));

        serviceLogModel.hashCode();
        serviceLogModel.toString();
        init(serviceLogModel);
        serviceLogModel.hashCode();

        ServiceLogModel serviceLogModel2 = serviceLogModel;
        assertTrue(serviceLogModel2.equals(serviceLogModel));

        assertFalse(serviceLogModel1.equals(serviceLogModel));
        assertFalse(serviceLogModel1.equals(null));

        init(serviceLogModel1);
        assertTrue(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setServiceName("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setBusinessSystem("test-pids");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setId(100L);
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setEiTimestamp(LocalDateTime.now().plusDays(1));
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setEiEventType("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setOrigCustomId("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setOrigProcessId("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setOrigHostname("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setOrigIp("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setOrigPrincipal("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setMiPortType("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setMiOperationName("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setMiMessageId("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setMiFlowId("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setMiTransportType("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setServiceKey("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setHttpMethod("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setServiceKey("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setConsumerIp("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setHttpStatus(500);
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setResponseTime(100L);
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setFailureCause("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));

        init(serviceLogModel1);
        serviceLogModel1.setMessageType("tests");
        assertFalse(serviceLogModel1.equals(serviceLogModel));
    }
}
