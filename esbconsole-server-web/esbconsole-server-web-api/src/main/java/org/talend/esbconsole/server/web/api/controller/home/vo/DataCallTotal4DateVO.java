package org.talend.esbconsole.server.web.api.controller.home.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调用总数据量响应对象
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class DataCallTotal4DateVO {
    /**
     * 日期
     */
    private String date;

    /**
     * 次数 或  数据量
     */
    private String total;

    /**
     * 单位 B、 KB、 MB、 GB
     */
    private String unit;
}
