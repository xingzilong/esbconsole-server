package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FeaturePageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FeaturePageQueryParamTest {

    public void init(FeaturePageQueryParam param) {

        param.setPageNum(1);
        param.setPageSize(10);
        param.setInstalled(false);
        param.setName("test");
        param.setVersion("v1");
    }

    @Test
    public void test() {

        FeaturePageQueryParam param = new FeaturePageQueryParam();
        FeaturePageQueryParam ar = param;
        param.hashCode();
        assertTrue(param.equals(ar));
        assertFalse(param.equals(null));
        ar = new FeaturePageQueryParam();
        assertTrue(param.equals(ar));
        init(param);
        assertFalse(param.equals(ar));
        param.hashCode();
        param.toString();
        init(ar);
        assertTrue(param.equals(ar));

    }
}
