package org.talend.esbconsole.server.domain.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 平台应用分析响应
 *
 * @author xingzilong
 * @date 2021/08/15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationTotalDTO {

    /**
     * 应用名称
     */
    private String name;
    /**
     * 数量
     */
    private Long value;
}
