package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.core.util.LoginUserDetails;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemAuthorityDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * SpringSecurity的 {@link UserDetailsService} 接口的实现，自定义认证授权时必须实现该接口， 用于实现自定义的认证和鉴权的规则。
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SystemUserDAO systemUserDAO;
    @Autowired
    private SystemAuthorityDAO systemAuthorityDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUserDO systemUser = systemUserDAO.getSystemUserByUserName(username);
        // 如果没有查询到用户就抛出异常
        if (Objects.isNull(systemUser)) {
            log.warn("未找到用户：" + username);
            throw new UsernameNotFoundException("未找到用户：" + username);
        }
        // 查询用户对应的所有权限
        Set<String> authorities = systemAuthorityDAO.listAuthorityKeyByUserId(systemUser.getId());
        authorities.remove(null);
        LoginUserDetails loginUserDetails = new LoginUserDetails(systemUser, authorities);
        return loginUserDetails;
    }
}
