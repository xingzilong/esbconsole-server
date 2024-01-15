package org.talend.esbconsole.server.domain.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * system_authority表相应的实体类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class SystemAuthorityDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 权限（路由）名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;

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
     * 权限代码
     */
    private String authorityCode;

    /**
     * 权限标识
     */
    private String authorityKey;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String deleteFlag;

    /**
     * 类型，目前类型有两种，分别为路由权限和普通接口权限。 当值为“0”时表示为路由权限，为“1”时表示为普通接口权限
     */
    private String type;

    /**
     * 状态：0-禁用，1-正常
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
