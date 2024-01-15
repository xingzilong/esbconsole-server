package org.talend.esbconsole.server.web.api.controller.authority.converter;

import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;
import org.talend.esbconsole.server.web.api.controller.user.request.UserPageQueryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link AuthorityWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AuthorityWebConverterImplTest {

    @InjectMocks
    private AuthorityWebConverterImpl authorityWebConverterImpl;

    @Test
    public void dto2voTest() {
        UserPageQueryRequest request = new UserPageQueryRequest();
        UserPageQueryParam userPageQueryParam = authorityWebConverterImpl.dto2vo(request);
        assertNotNull(userPageQueryParam);
        request = null;
        UserPageQueryParam userPageQueryParam1 = authorityWebConverterImpl.dto2vo(request);
        assertNull(userPageQueryParam1);
    }
}
