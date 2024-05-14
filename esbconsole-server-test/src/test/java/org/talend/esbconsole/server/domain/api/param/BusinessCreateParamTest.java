package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BusinessCreateParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessCreateParamTest {


    private void init(BusinessCreateParam param) {
        param.setResponsiblePerson("admin");
        param.setRemark("");
        param.setSystemName("test");

    }

    @Test
    public void test() {
        BusinessCreateParam param = new BusinessCreateParam();
        BusinessCreateParam businessCreateParam = param;
        assertTrue(param.equals(businessCreateParam));

        businessCreateParam = new BusinessCreateParam();
        assertTrue(param.equals(businessCreateParam));
        assertFalse(param.equals(null));

        init(businessCreateParam);
        businessCreateParam.toString();
        businessCreateParam.hashCode();

        init(param);
        assertTrue(param.equals(businessCreateParam));

        param.setResponsiblePerson("test");
        assertFalse(param.equals(businessCreateParam));

        init(param);
        param.setRemark("tt");
        assertFalse(param.equals(businessCreateParam));

        init(param);
        param.setSystemName("tt");
        assertFalse(param.equals(businessCreateParam));
    }

}
