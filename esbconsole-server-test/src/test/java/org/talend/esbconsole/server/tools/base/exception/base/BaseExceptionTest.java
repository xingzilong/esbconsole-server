package org.talend.esbconsole.server.tools.base.exception.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link BaseExceptionTest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BaseExceptionTest {

    @Test
    public void init() {

        String[] args = new String[1];
        BaseException exception = new BaseException("base", "200", args, "test");
        BaseException exception_message = new BaseException("base", "200", new String[2]);
        BaseException exception_info = new BaseException("base", "test");
        BaseException exception_data = new BaseException("200", new String[2]);
        BaseException exception_default = new BaseException("test");
        assertEquals("base", exception.getModule());
        assertEquals("200", exception.getCode());
        assertEquals("test", exception.getDefaultMessage());
        assertSame(args, exception.getArgs());
        exception.getCause();
        exception.getMessage();
        exception.getLocalizedMessage();
        assertEquals("test", exception_info.getMessage());

    }
}
