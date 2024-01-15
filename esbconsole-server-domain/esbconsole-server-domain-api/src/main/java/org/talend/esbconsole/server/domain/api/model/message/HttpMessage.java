package org.talend.esbconsole.server.domain.api.model.message;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * http 报文信息描述对象
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class HttpMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private String httpStatus;

    /**
     * 行信息
     */
    private Line line;

    /**
     * 头信息
     */
    private Map<String, String> header;

    /**
     * 负载内容
     */
    private String body;
}
