package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务系统字典分页查询参数
 *
 * @author xingzilong
 * @date 2021/08/22
 */
@Data
@NoArgsConstructor
public class BusinessPageQueryParam extends BasePageQueryRequest {
    /**
     * 所属消费方系统
     */
    private String systemName;

    /**
     * 状态码
     */
    private String status;

    /**
     * 创建时间的区间条件
     */
    private TimeInterval createTime;

    /**
     * 创建时间的排序关键字
     */
    private String createTimeSort;
}
