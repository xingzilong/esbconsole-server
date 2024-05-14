package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 删除消费方系统字典信息请求参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class ConsumerRemoveRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @NotNull
    @Size(max = 36)
    private String id;

}
