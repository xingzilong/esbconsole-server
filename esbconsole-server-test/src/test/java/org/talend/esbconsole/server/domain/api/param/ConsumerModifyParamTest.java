package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ConsumerModifyParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerModifyParamTest {

    private void init(ConsumerModifyParam param) {
        param.setId("13213");
        param.setIp("127.0.0.1");
        param.setRemark("");
        param.setResponsiblePerson("admin");
        param.setSystemName("test");
    }

    @Test
    public void test() {
        ConsumerModifyParam param = new ConsumerModifyParam();
        ConsumerModifyParam consumerModifyParam = param;
        assertTrue(param.equals(consumerModifyParam));

        consumerModifyParam = new ConsumerModifyParam();
        assertTrue(param.equals(consumerModifyParam));
        assertFalse(param.equals(null));

        init(consumerModifyParam);
        consumerModifyParam.toString();
        consumerModifyParam.hashCode();
        assertFalse(param.equals(consumerModifyParam));

        init(param);
        param.setId("12");
        assertFalse(param.equals(consumerModifyParam));

        init(param);
        param.setIp("");
        assertFalse(param.equals(consumerModifyParam));

        init(param);
        param.setRemark("test");
        assertFalse(param.equals(consumerModifyParam));

        init(param);
        param.setResponsiblePerson("test");
        assertFalse(param.equals(consumerModifyParam));

        init(param);
        param.setSystemName("tt");
        assertFalse(param.equals(consumerModifyParam));

    }
}
