package org.talend.esbconsole.server.tools.base.exception.jmx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link JMXException} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JMXExceptionTest {

    @Test
    public void init() {
        JMXException exception = new JMXException("200");
    }

}