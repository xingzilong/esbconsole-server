package org.talend.esbconsole.server.web.api.controller.user.converter;

import org.talend.esbconsole.server.web.api.controller.user.request.*;
import org.mapstruct.Mapper;
import org.talend.esbconsole.server.domain.api.param.AssignRoleParam;
import org.talend.esbconsole.server.domain.api.param.ResetPasswordParam;
import org.talend.esbconsole.server.domain.api.param.UserCreateParam;
import org.talend.esbconsole.server.domain.api.param.UserModifyParam;
import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;
import org.talend.esbconsole.server.web.api.controller.user.request.AssignRoleRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.ResetPasswordRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserCreateRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserModifyRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserPageQueryRequest;

@Mapper(componentModel = "spring")
public abstract class UserWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract UserPageQueryParam req2param(UserPageQueryRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract UserCreateParam req2param(UserCreateRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract UserModifyParam req2param(UserModifyRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract ResetPasswordParam req2param(ResetPasswordRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract AssignRoleParam req2param(AssignRoleRequest request);


}
