package org.talend.esbconsole.server.web.api.controller.role.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * 角色分页查询请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class RolePageQueryRequest extends BasePageQueryRequest {

    /**
     * 名称
     */
    private String name;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 状态：0-禁用，1-正常
     */
    @Pattern(regexp = "^[01]$", message = "状态码不正确")
    private String status;

    /**
     * 创建时间的区间条件
     */
    @Valid
    private TimeInterval createTime;

    /**
     * 创建时间的排序关键字
     *
     * @author xingzilong
     * @date 2023-07-31
     */
    private String createTimeSort;

}
