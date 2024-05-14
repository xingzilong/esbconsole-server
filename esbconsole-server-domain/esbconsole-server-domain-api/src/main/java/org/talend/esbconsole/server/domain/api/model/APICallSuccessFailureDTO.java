package org.talend.esbconsole.server.domain.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API调用成功与失败信息
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APICallSuccessFailureDTO {

    /**
     * 成功总数
     */
    private Long successTotal;

    /**
     * 失败总数
     */
    private Long failureTotal;
}
