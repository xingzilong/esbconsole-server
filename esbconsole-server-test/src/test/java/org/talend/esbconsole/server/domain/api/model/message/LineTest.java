package org.talend.esbconsole.server.domain.api.model.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link Line} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/25
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LineTest {

    public void init(Line line) {
        line.setMethod("get");
        line.setUri("/api");
        line.setProtocol("http/1.0");
    }

    @Test
    public void test() {

        Line line = new Line();
        Line line1 = new Line();

        assertTrue(line.equals(line1));

        line.hashCode();
        line.toString();
        init(line);
        line.hashCode();

        Line line2 = line;
        assertTrue(line2.equals(line));

        assertFalse(line1.equals(line));
        assertFalse(line1.equals(null));

        init(line1);
        assertTrue(line1.equals(line));

        init(line1);
        line1.setMethod("post");
        assertFalse(line1.equals(line));

        line1.setUri("/api/v1");
        assertFalse(line1.equals(line));

        init(line1);
        line1.setProtocol("http/1.1");
        assertFalse(line1.equals(line));

    }
}
