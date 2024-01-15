package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.repository.entity.SystemRoleAuthorityDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * system_role_authority表相应的DAO
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Repository
public interface SystemRoleAuthorityDAO {

    /**
     * 根据 role_id删除数据
     *
     * @param roleId
     * @return
     */
    int removeSystemRoleAuthorityByRoleId(String roleId);

    /**
     * 批量插入
     *
     * @param roleAuthorityList
     * @return
     */
    int saveSystemRoleAuthorityList(List<SystemRoleAuthorityDO> roleAuthorityList);

//    /**
//     * 批量插入
//     *
//     * @query roleAuthorityList
//     * @return
//     */
//    int saveSystemRoleAuthorityList(Long roleId, List<Long> roleAuthorityList);

}