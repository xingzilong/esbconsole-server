package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.query.RolePageQuery;
import org.talend.esbconsole.server.domain.repository.entity.SystemRoleDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * system_role表相应的DAO
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Repository
public interface SystemRoleDAO {
    /**
     * 新增一条角色信息
     *
     * @param role
     */
    void saveSystemRole(SystemRoleDO role);

    /**
     * 删除一条角色信息
     *
     * @param id
     */
    void removeSystemRole(String id);

    /**
     * 更新一条角色信息
     *
     * @param role
     */
    void updateSystemRole(SystemRoleDO role);

    /**
     * 根据请求的 roleId 查询该角色的 roleName
     *
     * @param roleId 角色ID
     * @return 角色名
     */
    String getSystemRoleNameByRoleId(Long roleId);

    /**
     * 根据请求的 roleName 查询角色
     *
     * @param roleName 角色ID
     * @return 角色
     */
    SystemRoleDO getSystemRoleByRoleName(String roleName);

    /**
     * 获取所有的角色信息
     *
     * @return 返回所有的角色
     */
    List<SystemRoleDO> listAllSystemRoles();

    /**
     * 根据查询条件获取所有的用户信息
     *
     * @return 返回所有的用户
     */
    List<SystemRoleDO> listAllSystemRolesByConditions(RolePageQuery rolePageQuery);


    /**
     * 根据请求的 id 查询该角色的所有信息
     *
     * @param id 角色ID
     * @return 角色信息
     */
    SystemRoleDO getSystemRoleById(String id);
}
