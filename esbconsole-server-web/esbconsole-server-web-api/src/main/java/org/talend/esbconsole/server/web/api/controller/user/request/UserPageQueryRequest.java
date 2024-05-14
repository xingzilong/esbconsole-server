package org.talend.esbconsole.server.web.api.controller.user.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * 用户分页查询的接收参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class UserPageQueryRequest extends BasePageQueryRequest {

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
    @Pattern(regexp = "^(13\\d|14[5|7]|15\\d|166|17[3|6|7]|18\\d|19[8|9])\\d{8}$",
            message = "手机号格式不正确")
    private String phoneNumber;

    /**
     * 状态：0-禁用，1-正常
     */
    @Pattern(regexp = "^[01]$", message = "状态码不正确")
    private String status;

    /**
     * 创建时间的排序关键字
     * xingzilong
     * 2021-07-31
     */
    private String createTimeSort;

    /**
     * 创建时间的区间条件
     */
    @Valid
    private TimeInterval createTime;

}
