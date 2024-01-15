package org.talend.esbconsole.server.domain.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建业务系统字典信息所接受的参数
 *
 * @author xingzilong
 * @date 2023/08/22
 */
@Data
@NoArgsConstructor
public class BusinessCreateParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 系统名
     */
    private String systemName;

    /**
     * 责任人
     */
    private String responsiblePerson;

    /**
     * 备注
     */
    private String remark;

}
