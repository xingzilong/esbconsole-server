package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.StatCatcherDTO;
import org.talend.esbconsole.server.domain.repository.entity.StatCatcherDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class StatCatcherConverter {

    /**
     * 模型转换
     *
     * @param statCatcherDO
     * @return
     */
    public abstract StatCatcherDTO do2dto(StatCatcherDO statCatcherDO);

    /**
     * 模型转换
     *
     * @param statCatcherDOS
     * @return
     */
    public abstract List<StatCatcherDTO> do2dto(List<StatCatcherDO> statCatcherDOS);

}
