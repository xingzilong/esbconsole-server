package org.talend.esbconsole.server.tools.base.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link ServerInfoReadException} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServerInfoReadExceptionTest {
    @Test
    public void init() {
        ServerInfoReadException exception = new ServerInfoReadException("test");
    }
}
