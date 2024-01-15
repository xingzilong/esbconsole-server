package org.talend.esbconsole.server.domain.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户拥有的角色详情
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRolesDTO {

    /**
     * 所有的角色列表
     */
    private List<SystemRoleDTO> allRoles;

    /**
     * 用户已拥有的角色的ID列表
     */
    private List<String> roleIdList;
}
