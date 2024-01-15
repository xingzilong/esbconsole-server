package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.SystemUserDTO;
import org.talend.esbconsole.server.domain.api.model.UserRolesDTO;
import org.talend.esbconsole.server.domain.api.param.*;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.domain.api.param.AssignRoleParam;
import org.talend.esbconsole.server.domain.api.param.ResetPasswordParam;
import org.talend.esbconsole.server.domain.api.param.UserCreateParam;
import org.talend.esbconsole.server.domain.api.param.UserModifyParam;
import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;

import java.util.List;

/**
 * 用户相关的功能服务接口
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public interface SystemUserService {
    /**
     * 新增一条用户信息
     *
     * @param userCreateParam
     */
    void addUser(UserCreateParam userCreateParam);

    /**
     * 删除一条用户信息（逻辑删除）
     *
     * @param id
     */
    void removeUser(String id);

    /**
     * 修改一条用户信息
     *
     * @param userModifyParam
     */
    void modifyUser(UserModifyParam userModifyParam);

    /**
     * 停用用户
     *
     * @param id
     */
    void disableUser(String id);

    /**
     * 启用用户
     *
     * @param id
     */
    void enableUser(String id);

    /**
     * 获取全部的用户
     *
     * @return 所有用户信息的 {@link List} 集合
     */
    List<SystemUserDTO> getAllUsers();

    /**
     * 分页获取用户(不包含密码)
     *
     * @param userPageQueryParam
     * @return
     */
    PageResult<SystemUserDTO> getUsers(UserPageQueryParam userPageQueryParam);

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    SystemUserDTO getUser(String id);

    /**
     * 根据用户名称获取用户
     *
     * @param userName 用户名称
     * @return
     */
    SystemUserDTO getUserByUserName(String userName);

    /**
     * 重置用户密码
     *
     * @param resetPasswordParam
     */
    void resetPassword(ResetPasswordParam resetPasswordParam);

    /**
     * 为用户分配角色
     *
     * @param assignRoleParam
     */
    void assignRoleForUser(AssignRoleParam assignRoleParam);

    /**
     * 获取用户的角色
     *
     * @param id
     */
    UserRolesDTO getAllRolesAndUserRoles(String id);

    /**
     * 根据用户ID删除对应的权限信息
     *
     * @param id 权限ID
     */
    void deleteAuthorityByUserId(String id);

    /**
     * 验证用户密码
     *
     * @param id       用户id
     * @param password 用户密码
     */
    void verifyPassword(String id, String password);

    /**
     * 修改用户密码
     *
     * @param id       用户id
     * @param password 用户密码
     */
    void changePassword(String id, String password);
}
