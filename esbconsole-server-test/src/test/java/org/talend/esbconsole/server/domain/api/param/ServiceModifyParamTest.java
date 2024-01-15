package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceModifyParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceModifyParamTest {

    public void init(ServiceModifyParam param) {
        param.setBusinessSystemId("test");
        param.setDescription("test");
        param.setId("1");
        param.setName("mp");
        param.setResponsiblePerson("penson");
    }

    @Test
    public void test() {
        ServiceModifyParam param = new ServiceModifyParam();
        ServiceModifyParam sr = param;
        param.hashCode();
        assertTrue(param.equals(sr));
        assertFalse(param.equals(null));
        sr = new ServiceModifyParam();
        assertTrue(param.equals(sr));
        init(param);
        assertFalse(param.equals(sr));
        param.hashCode();
        param.toString();
        init(sr);
        assertTrue(param.equals(sr));
    }

}
