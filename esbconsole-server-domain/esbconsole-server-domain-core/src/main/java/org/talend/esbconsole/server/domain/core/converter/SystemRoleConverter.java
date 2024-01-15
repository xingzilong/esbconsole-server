package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.SystemRoleDTO;
import org.talend.esbconsole.server.domain.api.param.RoleCreateParam;
import org.talend.esbconsole.server.domain.api.param.RoleModifyParam;
import org.talend.esbconsole.server.domain.repository.entity.SystemRoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SystemRoleConverter {

    /**
     * 模型转换
     *
     * @param systemRoleDO
     * @return
     */
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract SystemRoleDTO do2dto(SystemRoleDO systemRoleDO);

    /**
     * 模型转换
     *
     * @param systemRoleDOs
     * @return
     */
    public abstract List<SystemRoleDTO> do2dto(List<SystemRoleDO> systemRoleDOs);

    /**
     * 模型转换
     *
     * @param userCreateParam
     * @return
     */
    public abstract SystemRoleDO param2do(RoleCreateParam userCreateParam);

    /**
     * 模型转换
     *
     * @param userModifyParam
     * @return
     */
    public abstract SystemRoleDO param2do(RoleModifyParam userModifyParam);

}
