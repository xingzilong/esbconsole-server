package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 路由显示信息
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class MetaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 路由层级 菜单层级 路径层级
     */
    private Integer routeLevel;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    private boolean noCache;

    /**
     * 内链地址（http(s)://开头）
     */
    private String link;

    public MetaDTO(String title, Integer routeLevel, boolean noCache, String icon) {
        this.title = title;
        this.routeLevel = routeLevel;
        this.noCache = noCache;
        this.icon = icon;
    }

}
