package org.talend.esbconsole.server.domain.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 修改角色信息所接受的参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class RoleModifyParam implements Serializable {
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

}
