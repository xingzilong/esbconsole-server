package org.talend.esbconsole.server.tools.base.exception.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link InvalidTokenException} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class InvalidTokenExceptionTest {

    @Test
    public void init() {
        InvalidTokenException exception = new InvalidTokenException("Test");
    }
}
