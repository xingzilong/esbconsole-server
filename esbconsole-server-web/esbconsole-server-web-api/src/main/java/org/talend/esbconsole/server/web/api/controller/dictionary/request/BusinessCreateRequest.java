package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 创建业务系统字典信息请求参数
 *
 * @author xingzilong
 * @date 2021/08/22
 */
@Data
@NoArgsConstructor
public class BusinessCreateRequest implements Serializable {
    private static final long serialVersionUID = 1L;

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
