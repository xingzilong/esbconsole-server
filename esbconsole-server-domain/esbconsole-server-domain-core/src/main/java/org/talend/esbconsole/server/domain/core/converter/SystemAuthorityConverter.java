package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.SystemAuthorityDTO;
import org.talend.esbconsole.server.domain.repository.entity.SystemAuthorityDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SystemAuthorityConverter {

    /**
     * 模型转换
     *
     * @param systemAuthorityDO
     * @return
     */
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract SystemAuthorityDTO do2dto(SystemAuthorityDO systemAuthorityDO);

    /**
     * 模型转换
     *
     * @param systemAuthorityDOs
     * @return
     */
    public abstract List<SystemAuthorityDTO> do2dto(List<SystemAuthorityDO> systemAuthorityDOs);

}
