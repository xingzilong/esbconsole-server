package org.talend.esbconsole.server.start.filter;

import org.talend.esbconsole.server.domain.core.util.GuavaCacheUtil;
import org.talend.esbconsole.server.domain.core.util.LoginUserDetails;
import org.talend.esbconsole.server.tools.base.exception.authentication.InvalidTokenException;
import org.talend.esbconsole.server.tools.base.exception.authentication.TokenExpiredException;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.tools.common.utils.JWTUtil;
import org.talend.esbconsole.server.tools.common.utils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 为实现用户认证和鉴权功能的token过滤器，用于对请求的token进行校验
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Component
// @WebFilter(urlPatterns = "/api/*")
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private GuavaCacheUtil guavaCacheUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 非登录请求，获取token
        String token = JWTUtil.getToken(request);
        // token为null或“”
        if (StringUtil.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String cacheId = null;
        try {
            Claims claims = JWTUtil.parseJWT(token);
            cacheId = claims.getSubject();
        } catch (ExpiredJwtException e) {
            log.warn("JWT过期：" + cacheId);
            request.setAttribute(Constants.JWT_FILTER_EXCEPTION, new TokenExpiredException("身份认证信息过期"));
            request.getRequestDispatcher(Constants.JWT_FILTER_EXCEPTION_FORWARD_PATH).forward(request, response);
            return;
        } catch (Exception e) {
            log.warn("无效token，cacheid：" + cacheId);
            request.setAttribute(Constants.JWT_FILTER_EXCEPTION, new InvalidTokenException("无效身份认证信息"));
            request.getRequestDispatcher(Constants.JWT_FILTER_EXCEPTION_FORWARD_PATH).forward(request, response);
            return;
        }
        // 从Guava Cache中获取用户信息
        LoginUserDetails loginUserDetails = guavaCacheUtil.getUserDetails(cacheId);
        if (Objects.isNull(loginUserDetails)) {
            log.warn("Guava Cache缓存过期，cacheid：" + cacheId);
            request.setAttribute(Constants.JWT_FILTER_EXCEPTION, new TokenExpiredException("身份认证信息过期"));
            request.getRequestDispatcher(Constants.JWT_FILTER_EXCEPTION_FORWARD_PATH).forward(request, response);
            return;
        }
        // 存入SecurityContextHolder
        // 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDetails, null, loginUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }
}
