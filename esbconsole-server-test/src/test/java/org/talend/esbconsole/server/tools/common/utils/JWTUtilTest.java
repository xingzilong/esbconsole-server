package org.talend.esbconsole.server.tools.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

/**
 * {@link JWTUtil} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class JWTUtilTest {

    @InjectMocks
    JWTUtil jWTUtil;

    @Test
    public void getUUIDTest() {
        jWTUtil.getUUID();
    }

    @Test
    public void createJWTTest() {
        jWTUtil.createJWT("admin");
        jWTUtil.createJWT("admin", 10L);
        jWTUtil.createJWT("12", "admin", 10L);
    }

    @Test
    public void parseJWTTest() throws Exception {
        MockedStatic<Jwts> jwStatic = mockStatic(Jwts.class);
        JwtParser jwtParser = mock(JwtParser.class);
        jwStatic.when(() -> Jwts.parser()).thenReturn(jwtParser);
        when(jwtParser.setSigningKey(Mockito.any(SecretKey.class))).thenReturn(jwtParser);
        Jws<Claims> jws = mock(Jws.class);
        when(jwtParser.parseClaimsJws(Mockito.any())).thenReturn(jws);

        Claims claims = mock(Claims.class);
        when(jws.getBody()).thenReturn(claims);

        jWTUtil.parseJWT("adminjwttoken");

        jwStatic.close();
    }

    @Test
    public void getTokenTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(Mockito.any())).thenReturn("");

        jWTUtil.getToken(request);

        when(request.getHeader(Mockito.any())).thenReturn("Bearer Test");
        jWTUtil.getToken(request);

        when(request.getHeader(Mockito.any())).thenReturn("Test");
        jWTUtil.getToken(request);

    }

}
