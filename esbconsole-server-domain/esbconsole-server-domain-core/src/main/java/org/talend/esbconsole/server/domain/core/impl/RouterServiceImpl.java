package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.RouteModel;
import org.talend.esbconsole.server.domain.api.service.RouterService;
import org.talend.esbconsole.server.domain.repository.mapper.SystemAuthorityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 路由相关功能服务接口 {@link RouterService} 的实现类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Service
public class RouterServiceImpl implements RouterService {

    @Autowired
    private SystemAuthorityDAO systemAuthorityDAO;

    @Override
    public List<RouteModel> listRouteByUserId(String uerId) {
        List<RouteModel> routeModelList = systemAuthorityDAO.listRouteByUserId(uerId);
        return routeModelList;
    }

    @Override
    public List<RouteModel> routeListToTree(List<RouteModel> routeModelList) {
        return getTreeStruct(routeModelList, 0L);
    }

    @Override
    public List<RouteModel> getRouteListToTreeByUserId(String uerId) {
        // 一、先根据用户ID查询改用户拥有的路由列表
        List<RouteModel> routeModelList = systemAuthorityDAO.listRouteByUserId(uerId);
        // 二、将路由列表构建为树形结构
        List<RouteModel> treeStruct = routeListToTree(routeModelList);
        return treeStruct;
    }

    /**
     * 获取树状结构
     *
     * @param allList 分类表
     * @param topId   传入的父节点ID
     * @return List<SystemAuthorityDO>
     */
    public List<RouteModel> getTreeStruct(List<RouteModel> allList, Long topId) {
        List<RouteModel> returnList = new ArrayList<RouteModel>();
        for (Iterator<RouteModel> childNode = allList.iterator(); childNode.hasNext(); ) {
            RouteModel node = childNode.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId().longValue() == topId.longValue()) {
                recursionFn(allList, node);
                returnList.add(node);
            }
        }
        return returnList;
    }

    /**
     * 递归列表，设置node的子节点列表
     *
     * @param allList 分类表
     * @param node    子节点
     */
    private void recursionFn(List<RouteModel> allList, RouteModel node) {
        // 得到子节点列表
        List<RouteModel> childList = getChildList(allList, node);
        node.setChildren(childList);
        for (RouteModel tChild : childList) {
            if (hasChild(allList, tChild)) {
                recursionFn(allList, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<RouteModel> getChildList(List<RouteModel> allist, RouteModel currentNode) {
        List<RouteModel> tlist = new ArrayList<RouteModel>();
        Iterator<RouteModel> it = allist.iterator();
        while (it.hasNext()) {
            RouteModel n = it.next();
            if (n.getParentId().longValue() == currentNode.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<RouteModel> allist, RouteModel currentNode) {
        return getChildList(allist, currentNode).size() > 0;
    }

}
