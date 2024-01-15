package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 任务日志分析返回数据
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class JobLogAnalysisDTO {

    /**
     * 南丁格尔玫瑰图的源数据
     */
    private List<ComponentTimeData> componentTimeDataList;

    /**
     * 任务名称
     */
    private String job;

    /**
     * 状态： 成功 失败
     */
    private String status;

    /**
     * 数据量
     */
    private Long dataVolume;

    /**
     * 异常日志记录
     */
    private List<LogCatcherDTO> logCatcherRecord;

    /**
     * 组件日志记录
     */
    private List<StatCatcherDTO> statCatcherRecord;

    /**
     * 组件日志记录
     */
    private List<FlowMeterCatcherDTO> flowMeterCatcherRecord;

}
