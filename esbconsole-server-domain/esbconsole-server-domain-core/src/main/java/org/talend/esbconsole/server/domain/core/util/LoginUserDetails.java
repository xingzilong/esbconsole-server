package org.talend.esbconsole.server.domain.core.util;

import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * SpringSecurity的 {@link UserDetails} 接口的实现，自定义认证授权时必须实现该接口， 用于存储自定义的用户的详细信息
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class LoginUserDetails implements UserDetails {
    /**
     * 系统用户信息
     */
    private SystemUserDO systemUser;

    /**
     * 用户的权限集合（原始数据）
     */
    private Set<String> authoritiesRaw;

    /**
     * 用户的权限集合
     */
    private Set<SimpleGrantedAuthority> authorities;

    public LoginUserDetails(SystemUserDO systemUser, Set<String> authoritiesRaw) {
        this.systemUser = systemUser;
        this.authoritiesRaw = authoritiesRaw;
    }

    public SystemUserDO getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUserDO systemUser) {
        this.systemUser = systemUser;
    }

    public Set<String> getAuthoritiesRaw() {
        return authoritiesRaw;
    }

    public void setAuthoritiesRaw(Set<String> authoritiesRaw) {
        this.authoritiesRaw = authoritiesRaw;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        // 把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
        // authorities = new ArrayList<>();
        // for (String permission : permissions) {
        // SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
        // authorities.add(authority);
        // }
        authorities = authoritiesRaw.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return authorities;
    }

    @Override
    public String getPassword() {
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
