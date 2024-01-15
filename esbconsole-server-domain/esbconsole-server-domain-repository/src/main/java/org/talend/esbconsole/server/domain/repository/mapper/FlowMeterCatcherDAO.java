package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.repository.entity.FlowMeterCatcherDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * stat_catcher表相应的DAO
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Repository
public interface FlowMeterCatcherDAO {

    /**
     * 根据uuid查询任务的数据流量日志信息
     *
     * @param uuid
     * @return
     */
    List<FlowMeterCatcherDO> listFlowMeterCatcherByUUId(String uuid);

    /**
     * 根据uuid查询任务的数据流量日志信息
     *
     * @param uuid
     * @return
     */
    Long getCountSUMByUUId(String uuid);

    /**
     * 获取传输数据总数
     *
     * @return
     */
    Long getFlowMeterTotal();
}
