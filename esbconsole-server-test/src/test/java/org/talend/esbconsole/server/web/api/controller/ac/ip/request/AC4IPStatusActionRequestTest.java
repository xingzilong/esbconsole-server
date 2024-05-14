package org.talend.esbconsole.server.web.api.controller.ac.ip.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4IPStatusActionRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4IPStatusActionRequestTest {

    private void init(AC4IPStatusActionRequest request) {
        request.setBlackList("");
        request.setId("5616");
        request.setServiceKey("t");
        request.setServiceName("test");
        request.setStatus("0");
        request.setType("white");
        request.setWhiteList("");
    }

    @Test
    public void test() {
        AC4IPStatusActionRequest request = new AC4IPStatusActionRequest();
        AC4IPStatusActionRequest ac4ipStatusActionRequest = new AC4IPStatusActionRequest();

        assertTrue(request.equals(ac4ipStatusActionRequest));
        assertFalse(request.equals(null));

        init(ac4ipStatusActionRequest);
        ac4ipStatusActionRequest.toString();
        ac4ipStatusActionRequest.hashCode();
        assertFalse(ac4ipStatusActionRequest.equals(request));

        ac4ipStatusActionRequest.getBlackList();
        ac4ipStatusActionRequest.getId();
        ac4ipStatusActionRequest.getServiceKey();
        ac4ipStatusActionRequest.getServiceName();
        ac4ipStatusActionRequest.getStatus();
        ac4ipStatusActionRequest.getType();
        ac4ipStatusActionRequest.getWhiteList();

        init(request);
        assertTrue(request.equals(ac4ipStatusActionRequest));

        request.setBlackList("127.0.0.1");
        assertFalse(ac4ipStatusActionRequest.equals(request));

        init(request);
        request.setId("7891");
        assertFalse(ac4ipStatusActionRequest.equals(request));

        init(request);
        request.setServiceKey("tt");
        assertFalse(ac4ipStatusActionRequest.equals(request));

        init(request);
        request.setServiceName("tt");
        assertFalse(ac4ipStatusActionRequest.equals(request));

        init(request);
        request.setStatus("1");
        assertFalse(ac4ipStatusActionRequest.equals(request));

        init(request);
        request.setType("black");
        assertFalse(ac4ipStatusActionRequest.equals(request));

        init(request);
        request.setWhiteList("127.0.0.1");
        assertFalse(ac4ipStatusActionRequest.equals(request));

    }
}
