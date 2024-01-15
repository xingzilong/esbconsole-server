package org.talend.esbconsole.server.domain.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * 权限信息
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityOtherInfoDTO {

    /**
     * 所有权限和路由的树形结构数据
     */
    private List<RouteAndAuthorityModel> allAuthoritiesTree;

    /**
     * 所有的路由的ID
     */
    private Set<Long> allRoteIdList;

    /**
     * 用户拥有的权限的id
     */
    private Set<Long> authorityIdListByRoleId;
}
