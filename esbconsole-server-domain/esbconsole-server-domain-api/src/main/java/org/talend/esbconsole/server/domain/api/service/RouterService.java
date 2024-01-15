package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.RouteModel;

import java.util.List;

/**
 * 系统菜单相关的功能服务接口
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public interface RouterService {

    /**
     * 根据用户ID获取此用户的路由集合
     *
     * @param uerId 用户ID
     * @return 数组结构
     */
    List<RouteModel> listRouteByUserId(String uerId);

    /**
     * 路由表的数组结构转换为树状结构
     *
     * @param routeModelList 路由表
     * @return 树状结构
     */
    List<RouteModel> routeListToTree(List<RouteModel> routeModelList);

    /**
     * 根据用户ID获取此用户的树状路由表
     *
     * @param uerId 用户ID
     * @return 树状结构
     */
    List<RouteModel> getRouteListToTreeByUserId(String uerId);

}
