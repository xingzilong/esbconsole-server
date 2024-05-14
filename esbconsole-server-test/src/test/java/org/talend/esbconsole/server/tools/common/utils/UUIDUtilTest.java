package org.talend.esbconsole.server.tools.common.utils;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link UUIDUtil} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UUIDUtilTest {

    @InjectMocks
    UUIDUtil uUIDUtil;

    @Test
    public void getUUIDTest() {
        uUIDUtil.getUUID();
    }
}
