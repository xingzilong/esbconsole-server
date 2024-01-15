package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ResetPasswordParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ResetPasswordParamTest {

    public ResetPasswordParam getParam() {

        ResetPasswordParam param = new ResetPasswordParam();
        param.setId("1");
        param.setPassword("Wps456");
        return param;
    }

    @Test
    public void test() {

        ResetPasswordParam param = getParam();
        ResetPasswordParam target = getParam();
        assertTrue(param.equals(target));
        assertFalse(param.equals(null));
        target.setId("2");
        assertFalse(param.equals(target));
        target.setId("1");
        target.setPassword("dsdf");
        assertFalse(param.equals(target));
        target.setId("2");
        assertFalse(param.equals(target));
        target = param;
        assertTrue(target.equals(param));

        param.toString();
        param.hashCode();

        ResetPasswordParam pr = new ResetPasswordParam();
        pr.hashCode();
        assertFalse(pr.equals(param));
        pr.setId("1");
        assertFalse(pr.equals(param));

    }
}
