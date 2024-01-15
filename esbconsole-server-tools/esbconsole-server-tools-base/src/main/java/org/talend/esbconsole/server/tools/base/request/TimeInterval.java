package org.talend.esbconsole.server.tools.base.request;

import org.talend.esbconsole.server.tools.base.annotation.TimeFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 时间区间
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class TimeInterval implements Serializable {
    private static final long serialVersionUID = 1L;

    @TimeFormat
    private String startTime = null;

    @TimeFormat
    private String endTime = null;

}
