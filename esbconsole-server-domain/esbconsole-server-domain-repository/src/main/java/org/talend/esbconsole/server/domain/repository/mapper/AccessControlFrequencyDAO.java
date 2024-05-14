package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.model.FrequencyControlModel;
import org.talend.esbconsole.server.domain.api.query.AC4FrequencyPageQuery;
import org.talend.esbconsole.server.domain.repository.entity.AccessControlFrequencyDO;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * access_control_frequency表相应的DAO
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Repository
public interface AccessControlFrequencyDAO {
    /**
     * 获取所有的控制信息
     *
     * @return
     */
    List<AccessControlFrequencyDO> listAllAccessControlFrequency();

    /**
     * 分页获取的控制信息
     *
     * @return
     */
    List<FrequencyControlModel> getAccessControlFrequency();

    /**
     * 根据查询条件分页获取的控制信息
     *
     * @param ac4FrequencyPageQuery
     * @return
     */
    List<FrequencyControlModel> getAccessControlFrequencyByConditions(AC4FrequencyPageQuery ac4FrequencyPageQuery);

    /**
     * 获取未绑定规则的服务
     *
     * @return
     */
    List<ServiceDO> getServiceNoBindFrequencyControl();

    /**
     * 根据id获取一条规则信息
     *
     * @param id
     * @return
     */
    FrequencyControlModel getFrequencyControlById(String id);
}