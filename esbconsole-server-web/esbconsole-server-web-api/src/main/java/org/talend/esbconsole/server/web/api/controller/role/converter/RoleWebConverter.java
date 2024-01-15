package org.talend.esbconsole.server.web.api.controller.role.converter;

import org.talend.esbconsole.server.domain.api.param.AssignAuthorityParam;
import org.talend.esbconsole.server.domain.api.param.RoleCreateParam;
import org.talend.esbconsole.server.domain.api.param.RoleModifyParam;
import org.talend.esbconsole.server.domain.api.param.RolePageQueryParam;
import org.talend.esbconsole.server.web.api.controller.role.request.AssignAuthorityRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RoleCreateRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RoleModifyRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RolePageQueryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RoleWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract RolePageQueryParam req2param(RolePageQueryRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract RoleCreateParam req2param(RoleCreateRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract RoleModifyParam req2param(RoleModifyRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract AssignAuthorityParam req2param(AssignAuthorityRequest request);


}
