package org.talend.esbconsole.server.domain.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * system_user_role表相应的实体类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserRoleDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 自增主键ID
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}