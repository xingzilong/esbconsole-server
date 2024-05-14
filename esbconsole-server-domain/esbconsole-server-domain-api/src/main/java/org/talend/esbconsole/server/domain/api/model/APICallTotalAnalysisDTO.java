package org.talend.esbconsole.server.domain.api.model;

import org.talend.esbconsole.server.tools.base.bean.ChartData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 七天api传输总量数据
 *
 * @author xingzilong
 * @date 2021/08/17
 **/
@Data
@NoArgsConstructor
public class APICallTotalAnalysisDTO implements ChartData {

    /**
     * 日期
     */
    private LocalDate date;

    /**
     * 次数 或  数据量
     */
    private Long total;

    /**
     * 次数 或  数据量
     */
    private double bestData;

    /**
     * 单位 数据量 B、 KB、 MB、 GB
     * 次数 “万”、 ”亿“
     */
    private String unit;

    public APICallTotalAnalysisDTO(LocalDate date, Long total) {
        this.date = date;
        this.total = total;
    }
}
