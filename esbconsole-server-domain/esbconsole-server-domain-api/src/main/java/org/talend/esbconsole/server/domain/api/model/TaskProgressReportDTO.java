package org.talend.esbconsole.server.domain.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 近七天任务完成情况统计数据返回形式
 *
 * @author xingzilong
 * @date 2021/08/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskProgressReportDTO {
    /**
     * 日期
     */
    @JsonFormat(pattern = "MM-dd", timezone = "GMT+8")
    private LocalDate date;

    /**
     * 成功总数
     */
    private Long successTotal;

    /**
     * 失败总数
     */
    private Long failureTotal;
}
