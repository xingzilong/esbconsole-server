package org.talend.esbconsole.server.start.expression;

import org.talend.esbconsole.server.domain.core.util.LoginUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * SpringSecurity自定义认证鉴权的规则
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Component("AE")
public class AuthorityExpressionRoot {

    public boolean hasAuthority(String authority) {
        // 获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        Set<String> authoritiesRaw = loginUserDetails.getAuthoritiesRaw();
        // 判断用户权限集合中是否存在authority
        return authoritiesRaw.contains(authority);
    }
}
