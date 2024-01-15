package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.AC4FrequencyPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.AC4FrequencyPageQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AC4FrequencyQueryConverter {

    /**
     * 参数转换
     *
     * @param ac4FrequencyPageQueryParam
     * @return
     */
    public abstract AC4FrequencyPageQuery param2query(AC4FrequencyPageQueryParam ac4FrequencyPageQueryParam);
}
