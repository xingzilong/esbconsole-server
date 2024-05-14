package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.repository.entity.LogCatcherDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * stat_catcher表相应的DAO
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Repository
public interface LogCatcherDAO {

    /**
     * 根据uuid查询记录的异常日志
     *
     * @param uuid
     * @return
     */
    List<LogCatcherDO> listLogCatcherByUUId(String uuid);

}
