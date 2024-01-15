package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BusinessModifyParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessModifyParamTest {


    private void init(BusinessModifyParam param) {
        param.setId("1531");
        param.setRemark("");
        param.setResponsiblePerson("admin");
        param.setSystemName("test");
    }

    @Test
    public void test() {
        BusinessModifyParam param = new BusinessModifyParam();
        BusinessModifyParam businessCreateParam = param;
        assertTrue(param.equals(businessCreateParam));

        businessCreateParam = new BusinessModifyParam();
        assertTrue(param.equals(businessCreateParam));
        assertFalse(param.equals(null));

        init(businessCreateParam);
        assertFalse(param.equals(businessCreateParam));
        businessCreateParam.toString();
        businessCreateParam.hashCode();

        init(param);
        param.setId("123");
        assertFalse(param.equals(businessCreateParam));

        init(param);
        param.setRemark("tt");
        assertFalse(param.equals(businessCreateParam));

        init(param);
        param.setResponsiblePerson("tt");
        assertFalse(param.equals(businessCreateParam));

        init(param);
        param.setSystemName("tt");
        assertFalse(param.equals(businessCreateParam));
    }


}
