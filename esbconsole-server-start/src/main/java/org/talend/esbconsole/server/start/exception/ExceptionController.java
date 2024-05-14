package org.talend.esbconsole.server.start.exception;

import org.talend.esbconsole.server.tools.common.constant.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Exception-过滤器异常捕捉", description = "测试相关接口")
@RestController
@RequestMapping("/api/exception")
public class ExceptionController {

    @Operation(summary = "认证异常处理接口")
    // 此处不能指定method属性的具体值，因为在springmvc重定向的过程中会根据原始请求method去匹配。
    // 如果此处指定了method属性的具体值，会报错。  xingzilong 2021-09-22
    @RequestMapping(path = "/authentication")
    public void authenticationException(HttpServletRequest request) {
        throw (RuntimeException) request.getAttribute(Constants.JWT_FILTER_EXCEPTION);
    }
}
