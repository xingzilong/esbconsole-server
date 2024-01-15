package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.FlowMeterCatcherDTO;
import org.talend.esbconsole.server.domain.repository.entity.FlowMeterCatcherDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FlowMeterCatcherConverter {

    /**
     * 模型转换
     *
     * @param flowMeterCatcherDO
     * @return
     */
    public abstract FlowMeterCatcherDTO do2dto(FlowMeterCatcherDO flowMeterCatcherDO);

    /**
     * 模型转换
     *
     * @param flowMeterCatcherDOS
     * @return
     */
    public abstract List<FlowMeterCatcherDTO> do2dto(List<FlowMeterCatcherDO> flowMeterCatcherDOS);

}
