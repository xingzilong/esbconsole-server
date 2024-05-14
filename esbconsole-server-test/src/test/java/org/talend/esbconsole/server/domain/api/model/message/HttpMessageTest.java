package org.talend.esbconsole.server.domain.api.model.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link HttpMessage} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/25
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class HttpMessageTest {

    public void init(HttpMessage httpMessage) {
        httpMessage.setHttpStatus("200");
        httpMessage.setLine(new Line());
        httpMessage.setHeader(new HashMap<String, String>());
        httpMessage.setBody("body");
    }

    @Test
    public void test() {

        HttpMessage httpMessage = new HttpMessage();
        HttpMessage httpMessage1 = new HttpMessage();

        assertTrue(httpMessage.equals(httpMessage1));

        httpMessage.hashCode();
        httpMessage.toString();
        init(httpMessage);
        httpMessage.hashCode();

        HttpMessage httpMessage2 = httpMessage;
        assertTrue(httpMessage2.equals(httpMessage));

        assertFalse(httpMessage1.equals(httpMessage));
        assertFalse(httpMessage1.equals(null));

        init(httpMessage1);
        assertTrue(httpMessage1.equals(httpMessage));

        init(httpMessage1);
        httpMessage1.setHttpStatus("500");
        assertFalse(httpMessage1.equals(httpMessage));

        httpMessage1.setLine(new Line());
        assertFalse(httpMessage1.equals(httpMessage));

        init(httpMessage1);
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        httpMessage1.setHeader(header);
        assertFalse(httpMessage1.equals(httpMessage));

        init(httpMessage1);
        httpMessage1.setBody("bodys");
        assertFalse(httpMessage1.equals(httpMessage));

    }
}
