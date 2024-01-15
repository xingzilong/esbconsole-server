package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 路由 实体类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class RouteModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 路由地址
     */
    private String routePath;

    /**
     * 组件路径
     */
    private String routeComponent;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由层级 菜单层级 路径层级
     */
    private Integer routeLevel;

    /**
     * 图标名称
     */
    private String icon;

    /**
     * 状态：0-禁用，1-正常
     */
    private String status;

    /**
     * 子路由 非数据库字段
     */
    private List<RouteModel> children = new ArrayList<RouteModel>();

    public RouteModel(Long id, Long parentId, String name, String routeName, String routePath, String routeComponent,
                      Integer orderNum, Integer routeLevel, String icon, String status) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.routeName = routeName;
        this.routePath = routePath;
        this.routeComponent = routeComponent;
        this.orderNum = orderNum;
        this.routeLevel = routeLevel;
        this.icon = icon;
        this.status = status;
    }

}