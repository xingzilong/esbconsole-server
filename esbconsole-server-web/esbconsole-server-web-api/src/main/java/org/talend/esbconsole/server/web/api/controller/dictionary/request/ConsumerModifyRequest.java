package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 修改消费方系统字典信息请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ConsumerModifyRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @NotNull
    @Size(max = 36)
    private String id;

    /**
     * ip地址
     */
    @NotNull
    private String ip;

    /**
     * 系统名称
     */
    @NotNull
    private String systemName;

    /**
     * 责任人
     */
    @NotNull
    private String responsiblePerson;

    /**
     * 描述
     */
    private String remark;

}
