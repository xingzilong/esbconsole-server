package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.AuthorityOtherInfoDTO;
import org.talend.esbconsole.server.domain.api.model.RouteAndAuthorityModel;
import org.talend.esbconsole.server.domain.api.model.SystemRoleDTO;
import org.talend.esbconsole.server.domain.api.model.SystemUserModel;
import org.talend.esbconsole.server.domain.api.param.AssignAuthorityParam;
import org.talend.esbconsole.server.domain.api.param.RoleCreateParam;
import org.talend.esbconsole.server.domain.api.param.RoleModifyParam;
import org.talend.esbconsole.server.domain.api.param.RolePageQueryParam;
import org.talend.esbconsole.server.tools.base.result.PageResult;

import java.util.List;

/**
 * 角色相关的功能服务接口
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public interface SystemRoleService {
    /**
     * 新增一条角色信息
     *
     * @param roleCreateParam
     */
    void addRole(RoleCreateParam roleCreateParam);

    /**
     * 删除一条角色信息（逻辑删除）
     *
     * @param id
     */
    void removeRole(String id);

    /**
     * 根据userId, roleId删除一条信息
     *
     * @param userId, roleId
     */
    void removeSystemUserRoleByUserIdAndRoleId(String userId, String roleId);

    /**
     * 修改一条角色信息
     *
     * @param roleModifyParam
     */
    void modifyRole(RoleModifyParam roleModifyParam);

    /**
     * 停用角色
     *
     * @param id
     */
    void disableRole(String id);

    /**
     * 启用角色
     *
     * @param id
     */
    void enableRole(String id);

    /**
     * 获取全部的角色
     *
     * @return 所有角色信息的 {@link List} 集合
     */
    List<SystemRoleDTO> getAllRoles();

    /**
     * 分页获取角色
     *
     * @param rolePageQueryParam
     * @return
     */
    PageResult<SystemRoleDTO> getRoles(RolePageQueryParam rolePageQueryParam);

    /**
     * 根据角色ID获取角色信息
     *
     * @param id 角色ID
     * @return
     */
    SystemRoleDTO getRole(String id);

    /**
     * 根据角色ID获取角色对应的用户信息
     *
     * @param roleId 角色ID
     * @return
     */
    List<SystemUserModel> listUserRoleByRoleId(String roleId);

    /**
     * 根据角色名称获取角色信息
     *
     * @param roleName 角色名称
     * @return
     */
    SystemRoleDTO getRoleByRoleName(String roleName);

    /**
     * 为角色分配权限
     *
     * @param assignAuthorityParam
     */
    void assignAuthorityForRole(AssignAuthorityParam assignAuthorityParam);

    /**
     * 获取路由和权限的树形结构
     *
     * @return
     */
    List<RouteAndAuthorityModel> getAllRouteAndAuthorityToTree();

    /**
     * 根据角色id获取相应的权限信息， 此信息包含1、所有权限和路由的树形结构数据。2、所有的路由的ID列表。3、角色的权限的ID列表。
     *
     * @param id
     * @return
     */
    AuthorityOtherInfoDTO getAuthorityOtherInfo(String id);

    /**
     * 根据角色ID删除对应的权限信息
     *
     * @param id 权限ID
     */
    void deleteAuthorityByRoleId(String id);
}
