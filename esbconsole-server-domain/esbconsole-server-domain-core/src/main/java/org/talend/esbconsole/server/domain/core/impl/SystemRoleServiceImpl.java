package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.AuthorityOtherInfoDTO;
import org.talend.esbconsole.server.domain.api.model.RouteAndAuthorityModel;
import org.talend.esbconsole.server.domain.api.model.RouteModel;
import org.talend.esbconsole.server.domain.api.model.SystemRoleDTO;
import org.talend.esbconsole.server.domain.api.model.SystemUserModel;
import org.talend.esbconsole.server.domain.api.param.AssignAuthorityParam;
import org.talend.esbconsole.server.domain.api.param.RoleCreateParam;
import org.talend.esbconsole.server.domain.api.param.RoleModifyParam;
import org.talend.esbconsole.server.domain.api.param.RolePageQueryParam;
import org.talend.esbconsole.server.domain.api.query.RolePageQuery;
import org.talend.esbconsole.server.domain.api.service.SystemRoleService;
import org.talend.esbconsole.server.domain.core.converter.SystemRoleConverter;
import org.talend.esbconsole.server.domain.core.converter.query.RoleQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.SystemRoleAuthorityDO;
import org.talend.esbconsole.server.domain.repository.entity.SystemRoleDO;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.talend.esbconsole.server.tools.base.exception.DataOperationException;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.common.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.talend.esbconsole.server.domain.repository.mapper.SystemAuthorityDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemRoleAuthorityDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemRoleDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserRoleDAO;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 角色相关的功能服务接口实现类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    @Autowired
    private SystemRoleDAO systemRoleDAO;

    @Autowired
    private SystemUserDAO systemUserDAO;

    @Autowired
    private SystemUserRoleDAO systemUserRoleDAO;

    @Autowired
    private SystemRoleAuthorityDAO systemRoleAuthorityDAO;

    @Autowired
    private SystemAuthorityDAO systemAuthorityDAO;

//    @Autowired
//    private GuavaCacheUtil guavaCacheUtil;

    @Autowired
    private SystemRoleConverter systemRoleConverter;

    @Autowired
    private RoleQueryConverter roleQueryConverter;

    @Override
    public void addRole(RoleCreateParam roleCreateParam) {
        SystemRoleDO systemRoleDO = systemRoleConverter.param2do(roleCreateParam);
        systemRoleDO.setId(UUIDUtil.getUUID());
        systemRoleDO.setCreateTime(LocalDateTime.now());
        systemRoleDO.setUpdateTime(LocalDateTime.now());
        systemRoleDAO.saveSystemRole(systemRoleDO);
    }

    @Override
    @Transactional
    public void removeRole(String id) {
        // 判断该角色是否已经被用户使用，若已被使用，则不能删除
        List<String> systemUserIds = systemUserRoleDAO.listUserIdsRoleByRoleId(id);
        if (systemUserIds.size() > 0) {
            // 该角色已被用户使用，无法删除
            throw new DataOperationException("数据被使用");
        }
        // 该角色未被用户使用
        // 删除该角色在用户角色表的数据
        deleteAuthorityByRoleId(id);
        // 删除角色相应的表信息，即清空权限
        systemRoleAuthorityDAO.removeSystemRoleAuthorityByRoleId(id);
        systemRoleDAO.removeSystemRole(id);
    }

    @Override
    @Transactional
    public void removeSystemUserRoleByUserIdAndRoleId(String userId, String roleId) {
        systemUserRoleDAO.removeSystemUserRoleByUserIdAndRoleId(userId, roleId);
        SystemRoleDO systemRoleDO = SystemRoleDO
                .builder()
                .id(roleId)
                .updateTime(LocalDateTime.now())
                .build();
        systemRoleDAO.updateSystemRole(systemRoleDO);
    }

    @Override
    public void modifyRole(RoleModifyParam roleModifyParam) {
        SystemRoleDO systemRoleDO = systemRoleConverter.param2do(roleModifyParam);
        systemRoleDO.setUpdateTime(LocalDateTime.now());
        systemRoleDAO.updateSystemRole(systemRoleDO);
    }

    @Override
    public void disableRole(String id) {
        SystemRoleDO systemRoleDO = SystemRoleDO
                .builder()
                .id(id)
                .status("0")
                .updateTime(LocalDateTime.now())
                .build();
        systemRoleDAO.updateSystemRole(systemRoleDO);
    }

    @Override
    public void enableRole(String id) {
        SystemRoleDO systemRoleDO = SystemRoleDO
                .builder()
                .id(id)
                .status("1")
                .updateTime(LocalDateTime.now())
                .build();
        systemRoleDAO.updateSystemRole(systemRoleDO);
    }

    @Override
    public List<SystemRoleDTO> getAllRoles() {
        List<SystemRoleDO> systemRoleDOS = systemRoleDAO.listAllSystemRoles();
        List<SystemRoleDTO> systemRoleDTOS = systemRoleConverter.do2dto(systemRoleDOS);
        return systemRoleDTOS;
    }

    @Override
    public PageResult<SystemRoleDTO> getRoles(RolePageQueryParam rolePageQueryParam) {
        RolePageQuery rolePageQuery = roleQueryConverter.param2query(rolePageQueryParam);
        PageHelper.startPage(rolePageQueryParam.getPageNum(), rolePageQueryParam.getPageSize());
        List<SystemRoleDO> systemRoleDOS = systemRoleDAO.listAllSystemRolesByConditions(rolePageQuery);
        PageInfo<SystemRoleDO> pageInfo = new PageInfo<SystemRoleDO>(systemRoleDOS);
        List<SystemRoleDTO> systemRoleDTOS = systemRoleConverter.do2dto(systemRoleDOS);
        return PageResult.of(systemRoleDTOS, pageInfo.getTotal());
    }

    @Override
    public SystemRoleDTO getRole(String id) {
        SystemRoleDO systemRoleDO = systemRoleDAO.getSystemRoleById(id);
        SystemRoleDTO systemRoleDTO = systemRoleConverter.do2dto(systemRoleDO);
        return systemRoleDTO;
    }

    @Override
    public List<SystemUserModel> listUserRoleByRoleId(String roleId) {
        List<SystemUserModel> systemUserModels = new ArrayList<>();
        List<String> systemUserIds = systemUserRoleDAO.listUserIdsRoleByRoleId(roleId);
        for (String id : systemUserIds) {
            SystemUserDO systemUserDO = systemUserDAO.getSystemUserById(id);
            SystemUserModel systemUserModel = new SystemUserModel(systemUserDO.getId(), systemUserDO.getName(), systemUserDO.getUserName());
            systemUserModels.add(systemUserModel);
        }
        return systemUserModels;
    }

    @Override
    public SystemRoleDTO getRoleByRoleName(String roleName) {
        SystemRoleDO systemRoleDO = systemRoleDAO.getSystemRoleByRoleName(roleName);
        SystemRoleDTO systemRoleDTO = systemRoleConverter.do2dto(systemRoleDO);
        return systemRoleDTO;
    }

    @Override
    @Transactional
    public void assignAuthorityForRole(AssignAuthorityParam assignAuthorityParam) {
        LocalDateTime now = LocalDateTime.now();
        List<SystemRoleAuthorityDO> systemRoleAuthorityList = new ArrayList<>();
        String roleId = assignAuthorityParam.getRoleId();
        // 用户的权限的id集合
        Set<Long> authorityIdList = assignAuthorityParam.getAuthorityIdList();
        if (authorityIdList.size() == 0) {
            return;
        }
        // 所有的路由信息
        List<RouteModel> allRouteModels = systemAuthorityDAO.listAllRoutes();
        // 获取用户权限对应的路由的id
        Set<Long> routeIdList = systemAuthorityDAO.listParentIdByIdList(authorityIdList);
        // 递归查找补全多级路由id
        findRouteIdForAuthority(allRouteModels, routeIdList);
        // 合并 权限id和路由id
        authorityIdList.addAll(routeIdList);
        // 一定会添加首页的路由，但是首页包含的接口的权限由用户决定  xingzilong 2023-09-06  待优化
        authorityIdList.add(6L);
        // 构件system_role_authority表的数据项
        for (Long authorityId : authorityIdList) {
            systemRoleAuthorityList.add(new SystemRoleAuthorityDO(UUIDUtil.getUUID(), roleId, authorityId, now, now));
        }
        // 删除角色相应的表信息，即清空权限
        systemRoleAuthorityDAO.removeSystemRoleAuthorityByRoleId(roleId);
        // 插入信息，设置权限
        systemRoleAuthorityDAO.saveSystemRoleAuthorityList(systemRoleAuthorityList);

    }

    @Override
    public List<RouteAndAuthorityModel> getAllRouteAndAuthorityToTree() {
        // 一、查询所有的路由和权限列表
        List<RouteAndAuthorityModel> allRouteAndAuthorityModelList = systemAuthorityDAO.listAllRouteAndAuthority();
        // 二、将路由列表构建为树形结构
        List<RouteAndAuthorityModel> treeStruct = routeListToTree(allRouteAndAuthorityModelList);
        return treeStruct;
    }

    @Override
    public AuthorityOtherInfoDTO getAuthorityOtherInfo(String id) {
        List<RouteAndAuthorityModel> allRouteAndAuthorityModel = getAllRouteAndAuthorityToTree();
        Set<Long> allRoteIdList = systemAuthorityDAO.listIdForRoute();
        Set<Long> authorityIdListByRoleId = systemAuthorityDAO.listIdForAuthorityByRoleId(id);
        AuthorityOtherInfoDTO authorityOtherInfoDTO = new AuthorityOtherInfoDTO(allRouteAndAuthorityModel, allRoteIdList, authorityIdListByRoleId);
        return authorityOtherInfoDTO;
    }

    @Override
    public void deleteAuthorityByRoleId(String id) {
        systemUserRoleDAO.removeSystemUserRoleByRoleId(id);
    }

    public List<RouteAndAuthorityModel> routeListToTree(List<RouteAndAuthorityModel> routeList) {
        return getTreeStruct(routeList, 0);
    }

    /**
     * 获取树状结构
     *
     * @param allList 分类表
     * @param topId   传入的父节点ID
     * @return List<SystemAuthorityDO>
     */
    public List<RouteAndAuthorityModel> getTreeStruct(List<RouteAndAuthorityModel> allList, int topId) {
        List<RouteAndAuthorityModel> returnList = new ArrayList<RouteAndAuthorityModel>();
        for (Iterator<RouteAndAuthorityModel> childNode = allList.iterator(); childNode.hasNext(); ) {
            RouteAndAuthorityModel node = childNode.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == topId) {
                recursionFn(allList, node);
                returnList.add(node);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param allList 分类表
     * @param node    子节点
     */
    private void recursionFn(List<RouteAndAuthorityModel> allList, RouteAndAuthorityModel node) {
        // 得到子节点列表
        List<RouteAndAuthorityModel> childList = getChildList(allList, node);
        node.setChildren(childList);
        for (RouteAndAuthorityModel tChild : childList) {
            if (hasChild(allList, tChild)) {
                recursionFn(allList, tChild);
            }
        }
    }

    /**
     * 得到子当前节点的所有节点列表
     *
     * @param allist      所有节点
     * @param currentNode 当前节点
     * @return
     */
    private List<RouteAndAuthorityModel> getChildList(List<RouteAndAuthorityModel> allist, RouteAndAuthorityModel currentNode) {
        List<RouteAndAuthorityModel> tlist = new ArrayList<RouteAndAuthorityModel>();
        Iterator<RouteAndAuthorityModel> it = allist.iterator();
        while (it.hasNext()) {
            RouteAndAuthorityModel n = it.next();
            if (n.getParentId().longValue() == currentNode.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断当前节点是否拥有子节点
     *
     * @param allist      所有界节点
     * @param currentNode 当前节点
     * @return
     */
    private boolean hasChild(List<RouteAndAuthorityModel> allist, RouteAndAuthorityModel currentNode) {
        return getChildList(allist, currentNode).size() > 0;
    }


    /**
     * 获取权限对应的路由
     *
     * @param allRouteModels
     * @param parentIdList
     */
    public void findRouteIdForAuthority(List<RouteModel> allRouteModels, Set<Long> parentIdList) {
        Set<Long> newParentIdList = new HashSet<>();
        for (Long id : parentIdList) {
            for (RouteModel routeModel : allRouteModels) {
                if (routeModel.getId().longValue() == id.longValue()) {
                    newParentIdList.add(routeModel.getParentId());
                }
            }
        }
        newParentIdList.remove(0L);
        if (newParentIdList.size() > 0) {
            findRouteIdForAuthority(allRouteModels, newParentIdList);
        }
        parentIdList.addAll(newParentIdList);

    }

}
