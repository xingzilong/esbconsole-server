package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.AC4IPPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.AC4IPPageQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AC4IPQueryConverter {

    /**
     * 参数转换
     *
     * @param ac4IPPageQueryParam
     * @return
     */
    public abstract AC4IPPageQuery param2query(AC4IPPageQueryParam ac4IPPageQueryParam);
}
