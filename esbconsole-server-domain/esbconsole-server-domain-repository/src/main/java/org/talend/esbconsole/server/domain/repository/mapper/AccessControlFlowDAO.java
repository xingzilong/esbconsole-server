package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.model.FlowControlModel;
import org.talend.esbconsole.server.domain.api.query.AC4FlowPageQuery;
import org.talend.esbconsole.server.domain.repository.entity.AccessControlFlowDO;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * access_control_flow表相应的DAO
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Repository
public interface AccessControlFlowDAO {
    /**
     * 获取所有的控制信息
     *
     * @return
     */
    List<AccessControlFlowDO> listAllAccessControlFlow();

    /**
     * 分页获取的控制信息
     *
     * @return
     */
    List<FlowControlModel> getAccessControlFlow();

    /**
     * 根据查询条件分页获取的控制信息
     *
     * @param ac4FlowPageQuery
     * @return
     */
    List<FlowControlModel> getAccessControlFlowByConditions(AC4FlowPageQuery ac4FlowPageQuery);

    /**
     * 获取未绑定规则的服务
     *
     * @return
     */
    List<ServiceDO> getServiceNoBindFlowControl();

    /**
     * 根据id获取一条规则信息
     *
     * @param id
     * @return
     */
    FlowControlModel getFlowControlById(String id);
}