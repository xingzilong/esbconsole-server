package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.JobLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.JobLogPageQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class JobLogQueryConverter {

    /**
     * 参数转换
     *
     * @param jobLogPageQueryParam
     * @return
     */
    public abstract JobLogPageQuery param2query(JobLogPageQueryParam jobLogPageQueryParam);
}
