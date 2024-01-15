package org.talend.esbconsole.server.start.handler;

import com.alibaba.fastjson2.JSON;
import org.talend.esbconsole.server.start.util.WebUtils;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringSecurity认证异常的处理程序
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult<String> result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "没有权限");
        String json = JSON.toJSONString(result);
        // 处理异常
        WebUtils.renderString(response, HttpStatus.FORBIDDEN.value(), json);
    }
}
