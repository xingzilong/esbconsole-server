package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户分页查询参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class UserPageQueryParam extends BasePageQueryRequest {

    /**
     * 名称
     */
    private String name;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 状态：0-禁用，1-正常
     */
    private String status;

    /**
     * 创建时间的排序关键字
     * xingzilong
     * 2023-07-31
     */
    private String createTimeSort;

    /**
     * 创建时间的区间条件
     */
    private TimeInterval createTime;

}
