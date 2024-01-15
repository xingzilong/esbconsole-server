package org.talend.esbconsole.server.domain.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 修改消费方系统字典信息所接受的参数
 *
 * @author xingzilong
 * @date 2023/08/22
 */
@Data
@NoArgsConstructor
public class ConsumerModifyParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 系统名称
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
