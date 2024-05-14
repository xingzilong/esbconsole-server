package org.talend.esbconsole.server.domain.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定时任务界面任务完成情况图
 *
 * @author xingzilong
 * @date 2021/08/22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskExecutionDTO {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 成功数据
     */
    private int successTotal;

    /**
     * 失败数据
     */
    private int failureTotal;
}
