package org.talend.esbconsole.server.tools.base.exception.file;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link FileAlreadyExistsException} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FileAlreadyExistsExceptionTest {

    @Test
    public void init() {
        FileAlreadyExistsException exception = new FileAlreadyExistsException("test");
    }

}
