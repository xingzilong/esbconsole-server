package org.talend.esbconsole.server.tools.base.exception.file;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link FileTypeIllegalException} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FileTypeIllegalExceptionTest {

    @Test
    public void init() {
        FileTypeIllegalException exception = new FileTypeIllegalException("test");
    }

}