package org.talend.esbconsole.server.web.api.controller.dictionary.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * 业务系统字典信息分页查询请求参数
 *
 * @author xingzilong
 * @date 2023/08/22
 */
@Data
@NoArgsConstructor
public class BusinessPageQueryRequest extends BasePageQueryRequest {
    /**
     * 所属业务系统
     */
    private String systemName;

    /**
     * 状态码
     */
    private String status;

    /**
     * 创建时间的区间条件
     */
    @Valid
    private TimeInterval createTime;

    /**
     * 创建时间的排序关键字
     */
    private String createTimeSort;
}
