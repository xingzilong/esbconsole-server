package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.model.RouteAndAuthorityModel;
import org.talend.esbconsole.server.domain.api.model.RouteModel;
import org.talend.esbconsole.server.domain.repository.entity.SystemAuthorityDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * system_authority表相应的DAO
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Repository
public interface SystemAuthorityDAO {

    /**
     * 根据 userId 查询该 userId 对应的用户的所有的路由信息
     *
     * @param userId
     * @return 一个Set集合，元素是String类型，每个元素就是一项路由
     */
    List<RouteModel> listRouteByUserId(String userId);

    /**
     * 获取所有的路由信息
     *
     * @return
     */
    List<RouteModel> listAllRoutes();

    /**
     * 根据 userId 查询该 userId 对应的用户的所有的权限信息 AuthorityKey
     *
     * @param userId
     * @return 一个Set集合，元素是String类型，每个元素就是一项权限
     */
    Set<String> listAuthorityKeyByUserId(String userId);

    /**
     * 根据 roleId 查询该 roleId 对应的角色的所有的权限信息 AuthorityCode
     *
     * @param roleId
     * @return 一个Set集合，元素是String类型，每个元素就是一项权限
     */
    Set<String> listAuthorityCodeByRoleId(Long roleId);

    /**
     * 获取所有的权限信息
     *
     * @return
     */
    List<SystemAuthorityDO> listAllSystemAuthorities();

    /**
     * 新增一条权限信息
     *
     * @param authority
     */
    void saveSystemAuthority(SystemAuthorityDO authority);

    /**
     * 删除一条权限信息
     *
     * @param id
     */
    void removeSystemAuthority(Long id);

    /**
     * 更新一条权限信息
     *
     * @param authority
     */
    void updateSystemAuthority(SystemAuthorityDO authority);

    /**
     * 根据请求的 id 查询该权限的 name
     *
     * @param id 权限ID
     * @return 权限名
     */
    String getNameById(Long id);

    /**
     * 根据请求的 id 查询该权限的所有信息
     *
     * @param id 权限ID
     * @return 权限信息
     */
    SystemAuthorityDO getSystemAuthorityById(Long id);

    /**
     * 根据 id 的集合获取相应数据项的 parent_id 的集合
     *
     * @param idList
     * @return
     */
    Set<Long> listParentIdByIdList(Set<Long> idList);

    /**
     * 获取所有的路由的 id
     *
     * @return
     */
    Set<Long> listIdForRoute();

    /**
     * 根据角色id获取该角色的权限id集合
     *
     * @return
     */
    Set<Long> listIdForAuthorityByRoleId(String roleId);

    /**
     * 获取所有的路由和权限
     *
     * @return
     */
    List<RouteAndAuthorityModel> listAllRouteAndAuthority();
}
