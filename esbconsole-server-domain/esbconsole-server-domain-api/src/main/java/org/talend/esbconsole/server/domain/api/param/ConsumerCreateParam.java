package org.talend.esbconsole.server.domain.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建消费方系统字典信息所接受的参数
 *
 * @author xingzilong
 * @date 2021/08/22
 */
@Data
@NoArgsConstructor
public class ConsumerCreateParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * IP地址
     */
    private String ip;

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
