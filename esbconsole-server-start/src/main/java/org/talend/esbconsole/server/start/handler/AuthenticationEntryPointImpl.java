package org.talend.esbconsole.server.start.handler;

import com.alibaba.fastjson2.JSON;
import org.talend.esbconsole.server.start.util.WebUtils;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringSecurity授权异常的处理程序
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.warn("认证鉴权异常处理>>>>>>" + authException.getMessage());
        ResponseResult<String> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "认证失败，无法访问系统资源", authException.getMessage());
        String json = JSON.toJSONString(result);
        // 处理异常
        WebUtils.renderString(response, HttpStatus.UNAUTHORIZED.value(), json);

    }
}
