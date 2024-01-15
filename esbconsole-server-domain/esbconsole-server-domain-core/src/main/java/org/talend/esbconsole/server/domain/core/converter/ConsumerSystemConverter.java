package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.ConsumerSystemDTO;
import org.talend.esbconsole.server.domain.api.param.ConsumerCreateParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerModifyParam;
import org.talend.esbconsole.server.domain.repository.entity.ConsumerSystemDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ConsumerSystemConverter {

    /**
     * 模型转换
     *
     * @param consumerSystemDO
     * @return
     */
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract ConsumerSystemDTO do2dto(ConsumerSystemDO consumerSystemDO);

    /**
     * 模型转换
     *
     * @param consumerSystemDOs
     * @return
     */
    public abstract List<ConsumerSystemDTO> do2dto(List<ConsumerSystemDO> consumerSystemDOs);

    /**
     * 模型转换
     *
     * @param userCreateParam
     * @return
     */
    public abstract ConsumerSystemDO param2do(ConsumerCreateParam userCreateParam);

    /**
     * 模型转换
     *
     * @param userModifyParam
     * @return
     */
    public abstract ConsumerSystemDO param2do(ConsumerModifyParam userModifyParam);

}
