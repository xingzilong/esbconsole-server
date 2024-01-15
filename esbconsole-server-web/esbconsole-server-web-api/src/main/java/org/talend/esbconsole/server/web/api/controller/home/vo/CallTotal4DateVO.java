package org.talend.esbconsole.server.web.api.controller.home.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调用总次数响应对象
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class CallTotal4DateVO {
    /**
     * 日期
     */
    private String date;

    /**
     * 次数 或  数据量
     */
    private String total;

    /**
     * 单位 ”万”、 “亿“
     */
    private String unit;
}
