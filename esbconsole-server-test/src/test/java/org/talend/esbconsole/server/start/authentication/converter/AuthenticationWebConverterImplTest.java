package org.talend.esbconsole.server.start.authentication.converter;

import org.talend.esbconsole.server.domain.api.param.LoginUserParam;
import org.talend.esbconsole.server.start.authentication.request.LoginUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link AuthenticationWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationWebConverterImplTest {

    @InjectMocks
    private AuthenticationWebConverterImpl authenticationWebConverterImpl;

    @Test
    public void req2param4LoginUserRequestTest() {
        LoginUserRequest request = new LoginUserRequest();
        LoginUserParam loginUserParam = authenticationWebConverterImpl.req2param(request);
        assertNotNull(loginUserParam);
        request = null;
        LoginUserParam loginUserParam1 = authenticationWebConverterImpl.req2param(request);
        assertNull(loginUserParam1);
    }

}
