package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色分页查询参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class RolePageQueryParam extends BasePageQueryRequest {

    /**
     * 名称
     */
    private String name;

    /**
     * 角色名
     */
    private String roleName;

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
     *
     * @author xingzilong
     * @date 2021-07-31
     */
    private String createTimeSort;

}
