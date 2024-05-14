package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.model.APICallAnalysisTableDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTop5DataDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.ServiceLogModel;
import org.talend.esbconsole.server.domain.api.query.ServiceLogPageQuery;
import org.talend.esbconsole.server.domain.repository.entity.EventsDO;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EVENTS 表相应的DAO
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Repository
public interface EventsDAO {

    /**
     * 根据查询条件分页获取所有的请求的服务调用日志的 MI_FLOW_ID
     *
     * @param serviceLogPageQuery 查询参数对象
     * @return
     */
    List<String> listREQMiFlowIdByConditions(ServiceLogPageQuery serviceLogPageQuery);

    /**
     * 根据MI_FLOW_ID集合获取的服务调用日志
     *
     * @param miFlowIdList MI_FLOW_ID集合
     * @return
     */
    List<ServiceLogModel> listEventsByMiFlowIdList(List<String> miFlowIdList);

    /**
     * 获取api调用总数
     *
     * @return
     */
    Long getCallTotal();

    /**
     * 获取api成功调用总数
     *
     * @return
     */
    Long getCallTotal4Success();

    /**
     * 获取api失败调用总数
     *
     * @return
     */
    Long getCallTotal4Failure();

    /**
     * 获取日期范围内的调用数据总数 （默认七天）
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    List<APICallTotalAnalysisDTO> listDataCallTotalByEiTimestamp(String startDate, String endDate);

    /**
     * 获取日期范围内的调用总数 （默认七天）
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    List<APICallTotalAnalysisDTO> listCallTotalByEiTimestamp(String startDate, String endDate);

    /**
     * 获取HTTP报文相关信息
     *
     * @return
     */
    EventsDO getHttpMessage(String messageType, String miFlowId);

    /**
     * 获取API调用总数的Top5
     *
     * @param serviceKey   api服务标识
     * @param timeInterval 时间区间
     * @return
     */
    List<APICallTop5DataDTO> getCallTotalTop5(String serviceKey, TimeInterval timeInterval);

    /**
     * 获取API调用失败总数的Top5
     *
     * @param serviceKey   api服务标识
     * @param timeInterval 时间区间
     * @return
     */
    List<APICallTop5DataDTO> getFailureTotalTop5(String serviceKey, TimeInterval timeInterval);

    /**
     * 获取api调用总数-消费方
     *
     * @param consumerIP
     * @param serviceKey
     * @param timeInterval
     * @return
     */
    List<APICallAnalysisTableDTO> getCallTotal4CallAnalysis(String consumerIP,
                                                            String serviceKey,
                                                            TimeInterval timeInterval);

    /**
     * 获取api调用失败总数-消费方
     *
     * @param consumerIPList
     * @param serviceKey
     * @param timeInterval
     * @return
     */
    List<APICallAnalysisTableDTO> getCallFailureTotal4CallAnalysis(List<String> consumerIPList,
                                                                   String serviceKey,
                                                                   TimeInterval timeInterval);

    /**
     * 获取api调用频次限制失败总数-消费方
     *
     * @param consumerIPList
     * @param serviceKey
     * @param timeInterval
     * @return
     */
    List<APICallAnalysisTableDTO> getCallFailureTotalFrequency4CallAnalysis(List<String> consumerIPList,
                                                                            String serviceKey,
                                                                            TimeInterval timeInterval);

    /**
     * 获取api调用流量限制失败总数-消费方
     *
     * @param consumerIPList
     * @param serviceKey
     * @param timeInterval
     * @return
     */
    List<APICallAnalysisTableDTO> getCallFailureTotalFlow4CallAnalysis(List<String> consumerIPList,
                                                                       String serviceKey,
                                                                       TimeInterval timeInterval);

    /**
     * 获取api调用IP限制失败总数-消费方
     *
     * @param consumerIPList
     * @param serviceKey
     * @param timeInterval
     * @return
     */
    List<APICallAnalysisTableDTO> getCallFailureTotalIP4CallAnalysis(List<String> consumerIPList,
                                                                     String serviceKey,
                                                                     TimeInterval timeInterval);

    /**
     * 获取api调用平均响应时长-消费方
     *
     * @param consumerIPList
     * @param serviceKey
     * @param timeInterval
     * @return
     */
    List<APICallAnalysisTableDTO> getAVGResponseTime4CallAnalysis(List<String> consumerIPList,
                                                                  String serviceKey,
                                                                  TimeInterval timeInterval);

    /**
     * 获取时间区间内的api的成功总数
     *
     * @param serviceKey   api服务标识
     * @param timeInterval 时间区间
     * @return
     */
    List<APICallTotalAnalysisDTO> getServiceSuccessTotalByDate(String serviceKey, TimeInterval timeInterval);

    /**
     * 获取时间区间内的api的失败总数
     *
     * @param serviceKey   api服务标识
     * @param timeInterval 时间区间
     * @return
     */
    List<APICallTotalAnalysisDTO> getServiceFailureTotalByDate(String serviceKey, TimeInterval timeInterval);

    /**
     * 获取时间区间内的api的平均响应耗时
     *
     * @param serviceKey   api服务标识
     * @param timeInterval 时间区间
     * @return
     */
    List<APICallTotalAnalysisDTO> getServiceResponseTimeByDate(String serviceKey, TimeInterval timeInterval);

    /**
     * 获取时间区间内的api的平均报文大小
     *
     * @param serviceKey   api服务标识
     * @param timeInterval 时间区间
     * @return
     */
    List<APICallTotalAnalysisDTO> getServiceMessageSizeByDate(String serviceKey, TimeInterval timeInterval);
}