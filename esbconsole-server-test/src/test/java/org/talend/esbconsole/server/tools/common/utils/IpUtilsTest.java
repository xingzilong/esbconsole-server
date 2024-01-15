package org.talend.esbconsole.server.tools.common.utils;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link IpUtils} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class IpUtilsTest {

    @InjectMocks
    IpUtils ipUtils;

    @Test
    public void getHostIpTest() {
        ipUtils.getHostIp();

    }

    @Test
    public void getHostNameTest() {
        ipUtils.getHostName();
    }

}
