package org.talend.esbconsole.server.domain.api.model.message;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * http 报文 行信息
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class Line implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 统一资源标志符
     */
    private String uri;

    /**
     * 协议
     */
    private String protocol;
}
