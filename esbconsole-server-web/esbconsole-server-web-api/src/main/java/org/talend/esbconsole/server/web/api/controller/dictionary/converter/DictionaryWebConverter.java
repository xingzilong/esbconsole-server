package org.talend.esbconsole.server.web.api.controller.dictionary.converter;

import org.talend.esbconsole.server.web.api.controller.dictionary.request.*;
import org.mapstruct.Mapper;
import org.talend.esbconsole.server.domain.api.param.BusinessCreateParam;
import org.talend.esbconsole.server.domain.api.param.BusinessModifyParam;
import org.talend.esbconsole.server.domain.api.param.BusinessPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerCreateParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerModifyParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerPageQueryParam;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessCreateRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessModifyRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerCreateRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerModifyRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerPageQueryRequest;

@Mapper(componentModel = "spring")
public abstract class DictionaryWebConverter {
    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract BusinessPageQueryParam req2param(BusinessPageQueryRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract BusinessCreateParam req2param(BusinessCreateRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract BusinessModifyParam req2param(BusinessModifyRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract ConsumerPageQueryParam req2param(ConsumerPageQueryRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract ConsumerCreateParam req2param(ConsumerCreateRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract ConsumerModifyParam req2param(ConsumerModifyRequest request);

}
