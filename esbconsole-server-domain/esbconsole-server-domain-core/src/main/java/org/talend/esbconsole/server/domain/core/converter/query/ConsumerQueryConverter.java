package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.ConsumerPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.ConsumerPageQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ConsumerQueryConverter {
    /**
     * 参数转换
     *
     * @param consumerPageQueryParam
     * @return
     */
    public abstract ConsumerPageQuery param2query(ConsumerPageQueryParam consumerPageQueryParam);
}
