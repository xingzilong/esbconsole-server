package org.talend.esbconsole.server.web.api.controller.role.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * 为角色分配权限所接受的参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class AssignAuthorityRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @NotNull
    private String roleId;

    /**
     * 给该角色分配的权限，对应的元素为权限表中的authority_code字段
     */
    @NotNull
    private Set<Long> authorityIdList;

}
