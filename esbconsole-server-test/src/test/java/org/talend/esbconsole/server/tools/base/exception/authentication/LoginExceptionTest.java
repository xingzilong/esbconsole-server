package org.talend.esbconsole.server.tools.base.exception.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link LoginException} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LoginExceptionTest {

    @Test
    public void init() {
        LoginException exception = new LoginException("Test");
    }
}
