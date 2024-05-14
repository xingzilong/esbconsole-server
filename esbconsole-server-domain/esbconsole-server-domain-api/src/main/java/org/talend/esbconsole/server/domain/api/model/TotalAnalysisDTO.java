package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 首页总数记录分析响应
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class TotalAnalysisDTO {

    /**
     * 服务总数
     */
    private Long serviceTotal;

    /**
     * 发布API总数
     */
    private Long apiTotal;

    /**
     * 定时路由
     */
    private Long timedRouteTotal;

    /**
     * 路由总数
     */
    private Long conventionalRouteTotal;

    /**
     * 调用API总数
     */
    private Long apiCallTotal;

    /**
     * 数据传输总数
     */
    private Long dataTransmissionTotal;
}
