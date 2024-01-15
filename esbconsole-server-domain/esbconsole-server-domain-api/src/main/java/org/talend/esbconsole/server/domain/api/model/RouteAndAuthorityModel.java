package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 路由和权限 实体类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class RouteAndAuthorityModel implements Serializable {
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
     * 权限（路由）名称
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
     * 权限代码
     */
    private String authorityCode;

    /**
     * 权限标识
     */
    private String authorityKey;

    /**
     * 类型，目前类型有两种，分别为路由权限和普通接口权限。 当值为“0”时表示为路由权限，为“1”时表示为普通接口权限
     */
    private String type;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 子元素
     */
    private List<RouteAndAuthorityModel> children = new ArrayList<RouteAndAuthorityModel>();
}
