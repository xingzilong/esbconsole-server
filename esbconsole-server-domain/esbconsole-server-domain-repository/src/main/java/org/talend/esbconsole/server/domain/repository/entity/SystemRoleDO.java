package org.talend.esbconsole.server.domain.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * system_role表相应的实体类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemRoleDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态：0-禁用，1-正常
     */
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String deleteFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
