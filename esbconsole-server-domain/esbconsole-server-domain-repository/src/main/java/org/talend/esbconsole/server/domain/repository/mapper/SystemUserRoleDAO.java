package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.repository.entity.SystemUserRoleDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * system_user表相应的DAO
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Repository
public interface SystemUserRoleDAO {
    /**
     * 根据userId获取该用户拥有的所有角色的角色id
     *
     * @param userId
     * @return
     */
    List<String> listRoleIdByUserId(String userId);

    /**
     * 根据roleId获取拥有该角色的用户ID
     *
     * @param roleId
     * @return
     */
    List<String> listUserIdsRoleByRoleId(String roleId);

    /**
     * 批量插入
     *
     * @param userRoleList
     * @return
     */
    int saveSystemUserRoleList(List<SystemUserRoleDO> userRoleList);

    /**
     * 根据 user_id删除数据
     *
     * @param userId
     * @return
     */
    int removeSystemUserRoleByUserId(String userId);

    /**
     * 根据 role_id删除数据
     *
     * @param roleId
     * @return
     */
    int removeSystemUserRoleByRoleId(String roleId);

    /**
     * 根据 user_id和role_id删除数据
     *
     * @param userId、roleId
     * @return
     */
    int removeSystemUserRoleByUserIdAndRoleId(String userId, String roleId);
}
