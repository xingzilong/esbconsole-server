package org.talend.esbconsole.server.web.api.controller.joblog.converter;

import org.talend.esbconsole.server.domain.api.model.FlowMeterCatcherDTO;
import org.talend.esbconsole.server.domain.api.model.JobLogAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.JobLogDTO;
import org.talend.esbconsole.server.domain.api.model.LogCatcherDTO;
import org.talend.esbconsole.server.domain.api.model.StatCatcherDTO;
import org.talend.esbconsole.server.domain.api.param.JobLogPageQueryParam;
import org.talend.esbconsole.server.web.api.controller.joblog.request.JobLogPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.FlowMeterCatcherVO;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.JobLogAnalysisVO;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.JobLogVO;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.LogCatcherVO;
import org.talend.esbconsole.server.web.api.controller.joblog.vo.StatCatcherVO;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class JobLogWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract JobLogPageQueryParam req2param(JobLogPageQueryRequest request);


    /**
     * 参数转换
     *
     * @param jobLogAnalysisDTO
     * @return
     */
    public abstract JobLogAnalysisVO dto2vo(JobLogAnalysisDTO jobLogAnalysisDTO);

    /**
     * 参数转换
     *
     * @param jobLogDTO
     * @return
     */
    @Mapping(source = "executTime", target = "executTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract JobLogVO dto2vo(JobLogDTO jobLogDTO);


    /**
     * 参数转换
     *
     * @param jobLogDTOS
     * @return
     */
    public abstract List<JobLogVO> dto2vo(List<JobLogDTO> jobLogDTOS);

    /**
     * 参数转换
     *
     * @param flowMeterCatcherDTO
     * @return
     */
    @Mapping(source = "moment", target = "moment", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract FlowMeterCatcherVO dto2vo(FlowMeterCatcherDTO flowMeterCatcherDTO);

    /**
     * 参数转换
     *
     * @param statCatcherDTO
     * @return
     */
    @Mapping(source = "moment", target = "moment", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract StatCatcherVO dto2vo(StatCatcherDTO statCatcherDTO);

    /**
     * 参数转换
     *
     * @param logCatcherDTO
     * @return
     */
    @Mapping(source = "moment", target = "moment", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract LogCatcherVO dto2vo(LogCatcherDTO logCatcherDTO);
}
