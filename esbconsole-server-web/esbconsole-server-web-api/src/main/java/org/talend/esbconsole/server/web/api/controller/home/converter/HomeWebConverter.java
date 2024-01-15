package org.talend.esbconsole.server.web.api.controller.home.converter;

import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.web.api.controller.home.vo.CallTotal4DateVO;
import org.talend.esbconsole.server.web.api.controller.home.vo.DataCallTotal4DateVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class HomeWebConverter {

    /**
     * 模型转换
     *
     * @param apiCallTotalAnalysisDTO
     * @return
     */
    @Mapping(source = "date", target = "date", dateFormat = "MM-dd")
    @Mapping(target = "total", expression = "java(String.format(\"%.6f\", apiCallTotalAnalysisDTO.getBestData()))")
    public abstract DataCallTotal4DateVO dto2vo(APICallTotalAnalysisDTO apiCallTotalAnalysisDTO);

    /**
     * 模型转换
     *
     * @param apiCallTotalAnalysisDTOS
     * @return
     */
    public abstract List<DataCallTotal4DateVO> dto2vo(List<APICallTotalAnalysisDTO> apiCallTotalAnalysisDTOS);

    /**
     * 模型转换
     *
     * @param apiCallTotalAnalysisDTO
     * @return
     */
    @Mapping(source = "date", target = "date", dateFormat = "MM-dd")
    public abstract CallTotal4DateVO dto2ct4dvo(APICallTotalAnalysisDTO apiCallTotalAnalysisDTO);

    /**
     * 模型转换
     *
     * @param apiCallTotalAnalysisDTOS
     * @return
     */
    public abstract List<CallTotal4DateVO> dto2ct4dvo(List<APICallTotalAnalysisDTO> apiCallTotalAnalysisDTOS);


}
