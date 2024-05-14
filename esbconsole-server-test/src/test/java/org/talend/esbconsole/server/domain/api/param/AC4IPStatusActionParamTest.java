package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4IPStatusActionParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4IPStatusActionParamTest {

    private void init(AC4IPStatusActionParam param) {
        param.setBlackList("");
        param.setId("5616");
        param.setServiceKey("t");
        param.setServiceName("test");
        param.setStatus("0");
        param.setType("white");
        param.setWhiteList("");
    }

    @Test
    public void test() {
        AC4IPStatusActionParam param = new AC4IPStatusActionParam();
        AC4IPStatusActionParam ac4ipStatusActionParam = new AC4IPStatusActionParam();

        assertTrue(param.equals(ac4ipStatusActionParam));
        assertFalse(param.equals(null));

        init(ac4ipStatusActionParam);
        ac4ipStatusActionParam.toString();
        ac4ipStatusActionParam.hashCode();
        assertFalse(ac4ipStatusActionParam.equals(param));

        ac4ipStatusActionParam.getBlackList();
        ac4ipStatusActionParam.getId();
        ac4ipStatusActionParam.getServiceKey();
        ac4ipStatusActionParam.getServiceName();
        ac4ipStatusActionParam.getStatus();
        ac4ipStatusActionParam.getType();
        ac4ipStatusActionParam.getWhiteList();

        init(param);
        assertTrue(param.equals(ac4ipStatusActionParam));

        param.setBlackList("127.0.0.1");
        assertFalse(ac4ipStatusActionParam.equals(param));

        init(param);
        param.setId("7891");
        assertFalse(ac4ipStatusActionParam.equals(param));

        init(param);
        param.setServiceKey("tt");
        assertFalse(ac4ipStatusActionParam.equals(param));

        init(param);
        param.setServiceName("tt");
        assertFalse(ac4ipStatusActionParam.equals(param));

        init(param);
        param.setStatus("1");
        assertFalse(ac4ipStatusActionParam.equals(param));

        init(param);
        param.setType("black");
        assertFalse(ac4ipStatusActionParam.equals(param));

        init(param);
        param.setWhiteList("127.0.0.1");
        assertFalse(ac4ipStatusActionParam.equals(param));

    }
}
