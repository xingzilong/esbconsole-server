package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.query.UserPageQuery;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * system_user表相应的DAO
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Repository
public interface SystemUserDAO {
    /**
     * 新增一条用户信息
     *
     * @param user
     */
    void saveSystemUser(SystemUserDO user);

    /**
     * 删除一条用户信息
     *
     * @param id
     */
    void removeSystemUser(String id);

    /**
     * 更新一条用户信息
     *
     * @param user
     */
    void updateSystemUser(SystemUserDO user);

    /**
     * 根据请求的 userName 查询该用户的所有信息（用于springsecurity的自定义认证）
     *
     * @param userName
     * @return 一个 {@link SystemUserDO} 的对象，可能为null
     */
    SystemUserDO getSystemUserByUserName(String userName);

    /**
     * 根据请求的 userId 查询该用户的 userName
     *
     * @param userId 用户ID
     * @return 用户名
     */
    String getUserNameById(String userId);

    /**
     * 获取所有的用户信息（不包含密码）
     *
     * @return 返回所有的用户
     */
    List<SystemUserDO> listAllSystemUsers();

    /**
     * 根据查询条件获取所有的用户信息（不包含密码）
     *
     * @return 返回所有的用户
     */
    List<SystemUserDO> listAllSystemUsersByConditions(UserPageQuery userPageQuery);

    /**
     * 根据请求的 userId 查询该用户的所有信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    SystemUserDO getSystemUserById(String userId);

    /**
     * 根据用户id查询用户密码
     *
     * @param id 用户id
     * @return
     */
    String getPasswordById(String id);
}
