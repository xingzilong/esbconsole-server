package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.model.TaskProgressReportDTO;
import org.talend.esbconsole.server.domain.api.query.JobLogPageQuery;
import org.talend.esbconsole.server.domain.repository.entity.StatCatcherDO;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * stat_catcher表相应的DAO
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Repository
public interface StatCatcherDAO {

    /**
     * 根据查询条件分页获取所有的任务运行日志
     *
     * @param jobLogPageQuery 查询参数对象
     * @return
     */
    List<StatCatcherDO> listStatCatchersByConditions(JobLogPageQuery jobLogPageQuery);

    /**
     * 根据查询条件分页获取所有的任务日志的 uuid
     *
     * @param jobLogPageQuery 查询参数对象
     * @return
     */
    List<String> listUUIdByConditions(JobLogPageQuery jobLogPageQuery);

    /**
     * 根据uuid集合获取的任务运行日志
     *
     * @param uuIdList uuid集合
     * @return
     */
    List<StatCatcherDO> listStatCatchersByUUIdList(List<String> uuIdList);

    /**
     * 根据uuid查询任务运行日志
     *
     * @param uuid
     * @return
     */
    List<StatCatcherDO> listStatCatcherByUUId(String uuid);

    /**
     * 获取日期范围内的任务执行分析数据 （默认七天）
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    List<TaskProgressReportDTO> listTaskProgressReportTotal(String startDate, String endDate);

    /**
     * 获取定时路由成功次数
     *
     * @return
     */
    int getTaskExecuteSuccess(String taskJobName, String taskName, String startTime, String endTime);

    /**
     * 获取定时路由执行失败次数
     *
     * @return
     */
    int getTaskExecuteFailure(String taskJobName, String taskName, String startTime, String endTime);

    /**
     * 获取定时路由执行耗时时间
     *
     * @param taskName
     * @return
     */
    List<StatCatcherDO> getTimedTask(String taskJobName, String taskName, String startTime, String endTime);

    /**
     * 根据查询条件分页获取所有的任务日志的 uuid
     *
     * @param jobNames   任务名称的集合
     * @param executTime 时间区间
     * @return
     */
    List<String> listUUIdByConditions4Analysis(List<String> jobNames, TimeInterval executTime);

    /**
     * 根据uuid集合获取的任务运行日志
     *
     * @param uuIdList uuid集合
     * @return
     */
    List<StatCatcherDO> listStatCatchersByUUIdList4Analysis(List<String> uuIdList);

}
