package org.talend.esbconsole.server.web.api.controller.joblog.vo;

import org.talend.esbconsole.server.domain.api.model.ComponentTimeData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 运行记录分析返回数据
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class JobLogAnalysisVO {

    /**
     * 南丁格尔玫瑰图的源数据
     */
    private List<ComponentTimeData> componentTimeDataList;

    /**
     * 任务名称
     */
    private String job;

    /**
     * 状态：成功(success) 或 失败(failure)
     */
    private String status;

    /**
     * 数据量
     */
    private Long dataVolume;

    /**
     * 异常日志记录
     */
    private List<LogCatcherVO> logCatcherRecord;

    /**
     * 组件日志记录
     */
    private List<StatCatcherVO> statCatcherRecord;

    /**
     * 组件日志记录
     */
    private List<FlowMeterCatcherVO> flowMeterCatcherRecord;

}
