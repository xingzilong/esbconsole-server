package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.model.IPControlModel;
import org.talend.esbconsole.server.domain.api.query.AC4IPPageQuery;
import org.talend.esbconsole.server.domain.repository.entity.AccessControlIPDO;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * access_control_ip表相应的DAO
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Repository
public interface AccessControlIPDAO {
    /**
     * 获取所有的控制信息
     *
     * @return
     */
    List<AccessControlIPDO> listAllAccessControlIP();

    /**
     * 分页获取的控制信息
     *
     * @return
     */
    List<IPControlModel> getAccessControlIP();

    /**
     * 根据查询条件分页获取的控制信息
     *
     * @param ac4IPPageQuery
     * @return
     */
    List<IPControlModel> getAccessControlIPByConditions(AC4IPPageQuery ac4IPPageQuery);

    /**
     * 获取未绑定规则的服务
     *
     * @return
     */
    List<ServiceDO> getServiceNoBindIPControl();

    /**
     * 根据id获取一条规则信息
     *
     * @param id
     * @return
     */
    IPControlModel getIPControlById(String id);
}