package org.talend.esbconsole.server.domain.api.query;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询条件对象
 *
 * @author xingzilong
 * @date 2021/08/22
 */
@Data
@NoArgsConstructor
public class ConsumerPageQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * IP地址
     */
    private String ip;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 状态：0-禁用，1-正常
     */
    private String status;

    /**
     * 创建时间的区间条件
     */
    private TimeInterval createTime;

    /**
     * 创建时间的排序关键字
     * xingzilong
     * 2021-07-31
     */
    private String createTimeSort;
}
