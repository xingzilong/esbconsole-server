package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.RolePageQueryParam;
import org.talend.esbconsole.server.domain.api.query.RolePageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link RoleQueryConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RoleQueryConverterImplTest {

    @InjectMocks
    private RoleQueryConverterImpl roleQueryConverterImpl;

    @Test
    public void param2queryTest() {
        RolePageQueryParam param = new RolePageQueryParam();
        RolePageQuery rolePageQuery = roleQueryConverterImpl.param2query(param);
        assertNotNull(rolePageQuery);
        param = null;
        RolePageQuery rolePageQuery1 = roleQueryConverterImpl.param2query(param);
        assertNull(rolePageQuery1);
    }

}
