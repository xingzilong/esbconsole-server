package org.talend.esbconsole.server.domain.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * system_role_authority表相应的实体类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemRoleAuthorityDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键id
     */
    private String id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限id
     */
    private Long authorityId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
