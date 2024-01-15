package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.RouteAndAuthorityModel;
import org.talend.esbconsole.server.domain.api.model.SystemAuthorityDTO;

import java.util.List;

/**
 * 权限相关的功能服务接口
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public interface SystemAuthorityService {

    /**
     * 获取所有的权限（和路由）详细信息 树形结构
     *
     * @return
     */
    List<RouteAndAuthorityModel> getAllAuthoritiesTree();

    /**
     * 获取所有的权限详细信息
     *
     * @return
     */
    List<SystemAuthorityDTO> getAllAuthorities();


    /**
     * 根据权限ID获取权限信息
     *
     * @param id 权限ID
     * @return
     */
    SystemAuthorityDTO getAuthority(Long id);
}
