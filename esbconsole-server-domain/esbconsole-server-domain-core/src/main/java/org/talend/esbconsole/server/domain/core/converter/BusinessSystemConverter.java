package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.BusinessSystemDTO;
import org.talend.esbconsole.server.domain.api.param.BusinessCreateParam;
import org.talend.esbconsole.server.domain.api.param.BusinessModifyParam;
import org.talend.esbconsole.server.domain.repository.entity.BusinessSystemDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BusinessSystemConverter {

    /**
     * 模型转换
     *
     * @param businessSystemDO
     * @return
     */
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract BusinessSystemDTO do2dto(BusinessSystemDO businessSystemDO);

    /**
     * 模型转换
     *
     * @param businessSystemDOs
     * @return
     */
    public abstract List<BusinessSystemDTO> do2dto(List<BusinessSystemDO> businessSystemDOs);

    /**
     * 模型转换
     *
     * @param userCreateParam
     * @return
     */
    public abstract BusinessSystemDO param2do(BusinessCreateParam userCreateParam);

    /**
     * 模型转换
     *
     * @param userModifyParam
     * @return
     */
    public abstract BusinessSystemDO param2do(BusinessModifyParam userModifyParam);

}
