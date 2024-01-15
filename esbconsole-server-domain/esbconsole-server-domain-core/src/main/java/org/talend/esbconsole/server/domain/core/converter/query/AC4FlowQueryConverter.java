package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.AC4FlowPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.AC4FlowPageQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AC4FlowQueryConverter {

    /**
     * 参数转换
     *
     * @param ac4FlowPageQueryParam
     * @return
     */
    public abstract AC4FlowPageQuery param2query(AC4FlowPageQueryParam ac4FlowPageQueryParam);
}
