package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ConsumerCreateParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerCreateParamTest {

    private void init(ConsumerCreateParam param) {
        param.setIp("127.0.0.1");
        param.setRemark("");
        param.setResponsiblePerson("admin");
        param.setSystemName("test");
    }

    @Test
    public void test() {
        ConsumerCreateParam param = new ConsumerCreateParam();
        ConsumerCreateParam consumerCreateParam = param;
        assertTrue(param.equals(consumerCreateParam));

        consumerCreateParam = new ConsumerCreateParam();
        assertTrue(param.equals(consumerCreateParam));
        assertFalse(param.equals(null));

        init(consumerCreateParam);
        assertFalse(param.equals(consumerCreateParam));
        consumerCreateParam.toString();
        consumerCreateParam.hashCode();

        init(param);
        param.setIp("");
        assertFalse(param.equals(consumerCreateParam));

        init(param);
        param.setRemark("test");
        assertFalse(param.equals(consumerCreateParam));

        init(param);
        param.setResponsiblePerson("test");
        assertFalse(param.equals(consumerCreateParam));

        init(param);
        param.setSystemName("tt");
        assertFalse(param.equals(consumerCreateParam));

    }


}
