package org.talend.esbconsole.server.domain.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户详细信息
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户的权限集合（原始数据）
     */
    private Set<String> authorities;

}
