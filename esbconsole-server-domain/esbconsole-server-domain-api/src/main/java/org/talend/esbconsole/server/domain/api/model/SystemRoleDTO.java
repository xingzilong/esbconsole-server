package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * system_role的描述对象
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class SystemRoleDTO implements Serializable {
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
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}
