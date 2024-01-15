package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.UserPageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link UserQueryConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserQueryConverterImplTest {

    @InjectMocks
    private UserQueryConverterImpl userQueryConverterImpl;

    @Test
    public void param2queryTest() {
        UserPageQueryParam param = new UserPageQueryParam();
        UserPageQuery userPageQuery = userQueryConverterImpl.param2query(param);
        assertNotNull(userPageQuery);
        param = null;
        UserPageQuery userPageQuery1 = userQueryConverterImpl.param2query(param);
        assertNull(userPageQuery1);
    }

}
