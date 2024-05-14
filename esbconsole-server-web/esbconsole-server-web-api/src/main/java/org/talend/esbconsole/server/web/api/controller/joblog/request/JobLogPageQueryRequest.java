package org.talend.esbconsole.server.web.api.controller.joblog.request;

import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * 分页查询参数基础类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class JobLogPageQueryRequest extends BasePageQueryRequest {

    /**
     * 服务为名称
     */
    private String serviceName;

    /**
     * 任务
     */
    private String job;

    /**
     * 状态：成功(success) 或 失败(failure)
     */
    private String status;

    /**
     * 执行开始时间的区间条件
     */
    @Valid
    private TimeInterval executTime;

    /**
     * 按照执行时间 moment 进行升序（asc）或降序（desc）
     */
    private String executTimeSort;


}
