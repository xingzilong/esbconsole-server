package org.talend.esbconsole.server.web.api.controller.service.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xingzilong
 * @program: esbconsole-server
 * @description: 定时路由耗时统计表VO
 * @create: 2023-08-24 10:00
 **/
@Data
@NoArgsConstructor
public class TaskTimeConsumptionVO {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 执行时间
     */
    private List<String> executionTime;

    /**
     * 执行耗时
     */
    private List<Long> timeConsumed;
}
