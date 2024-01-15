package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模型中组件运行时间描述对象
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ComponentTimeData {

    /**
     * 数据的name，指组件的名称
     */
    private String name;

    /**
     * 数据的value，指组件的耗时
     */
    private Long value;

}
