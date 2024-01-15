package org.talend.esbconsole.server.domain.api.query;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询条件对象
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class JobLogPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务为名称
     */
    private String serviceName;

    /**
     * 任务
     */
    private String job;

    /**
     * 状态：成功 或 失败
     */
    private String status;

    /**
     * 调用开始（请求）时间的区间条件
     */
    private TimeInterval executTime;

    /**
     * 按照 moment 进行升序（asc）或降序（desc）
     */
    private String executTimeSort;

}