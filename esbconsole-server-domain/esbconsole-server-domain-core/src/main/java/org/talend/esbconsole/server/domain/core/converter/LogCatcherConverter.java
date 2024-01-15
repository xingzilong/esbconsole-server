package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.LogCatcherDTO;
import org.talend.esbconsole.server.domain.repository.entity.LogCatcherDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class LogCatcherConverter {

    /**
     * 模型转换
     *
     * @param logCatcherDO
     * @return
     */
    public abstract LogCatcherDTO do2dto(LogCatcherDO logCatcherDO);

    /**
     * 模型转换
     *
     * @param logCatcherDOS
     * @return
     */
    public abstract List<LogCatcherDTO> do2dto(List<LogCatcherDO> logCatcherDOS);

}
