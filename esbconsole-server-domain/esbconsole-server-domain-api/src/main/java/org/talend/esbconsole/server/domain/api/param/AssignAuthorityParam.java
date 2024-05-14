package org.talend.esbconsole.server.domain.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * 为角色分配权限所接受的参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class AssignAuthorityParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 给该角色分配的权限
     */
    private Set<Long> authorityIdList;

}
