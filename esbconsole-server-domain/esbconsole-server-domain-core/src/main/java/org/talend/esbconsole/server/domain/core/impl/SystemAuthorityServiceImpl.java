package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.RouteAndAuthorityModel;
import org.talend.esbconsole.server.domain.api.model.SystemAuthorityDTO;
import org.talend.esbconsole.server.domain.api.service.SystemAuthorityService;
import org.talend.esbconsole.server.domain.api.service.SystemRoleService;
import org.talend.esbconsole.server.domain.core.converter.SystemAuthorityConverter;
import org.talend.esbconsole.server.domain.repository.entity.SystemAuthorityDO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemAuthorityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限相关的功能服务接口实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Service
public class SystemAuthorityServiceImpl implements SystemAuthorityService {

    @Autowired
    private SystemAuthorityDAO systemAuthorityDAO;

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemAuthorityConverter systemAuthorityConverter;

    @Override
    public List<RouteAndAuthorityModel> getAllAuthoritiesTree() {
        List<RouteAndAuthorityModel> allRouteAndAuthorityModelToTree = systemRoleService.getAllRouteAndAuthorityToTree();
        return allRouteAndAuthorityModelToTree;
    }

    @Override
    public List<SystemAuthorityDTO> getAllAuthorities() {
        List<SystemAuthorityDO> systemAuthorityDOS = systemAuthorityDAO.listAllSystemAuthorities();
        List<SystemAuthorityDTO> systemAuthorities = systemAuthorityConverter.do2dto(systemAuthorityDOS);
        return systemAuthorities;
    }


    @Override
    public SystemAuthorityDTO getAuthority(Long id) {
        SystemAuthorityDO systemAuthorityDO = systemAuthorityDAO.getSystemAuthorityById(id);
        SystemAuthorityDTO systemAuthorityDTO = systemAuthorityConverter.do2dto(systemAuthorityDO);
        return systemAuthorityDTO;
    }
}
