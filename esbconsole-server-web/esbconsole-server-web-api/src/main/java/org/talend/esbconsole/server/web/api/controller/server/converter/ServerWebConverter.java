package org.talend.esbconsole.server.web.api.controller.server.converter;

import org.talend.esbconsole.server.domain.api.model.JVMInfo;
import org.talend.esbconsole.server.domain.api.model.JVMMemoryInfo;
import org.talend.esbconsole.server.tools.common.utils.Arith;
import org.talend.esbconsole.server.web.api.controller.server.vo.JVMInfoVO;
import org.talend.esbconsole.server.web.api.controller.server.vo.JVMMemoryInfoVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ServerWebConverter {
    /**
     * 参数转换
     *
     * @param jvmInfo
     * @return
     */
    public abstract JVMInfoVO dto2vo(JVMInfo jvmInfo);

    /**
     * 参数转换
     *
     * @param jvmMemoryInfo
     * @return
     */
//    @Mapping(target = "committed", expression = "java(Arith.div(jvmMemoryInfo.getCommitted(), (1024 * 1024 * 1024), 2))")
//    @Mapping(target = "init", expression = "java(Arith.div(jvmMemoryInfo.getInit(), (1024 * 1024 * 1024), 2))")
//    @Mapping(target = "max", expression = "java(Arith.div(jvmMemoryInfo.getMax(), (1024 * 1024 * 1024), 2))")
//    @Mapping(target = "used", expression = "java(Arith.div(jvmMemoryInfo.getUsed(), (1024 * 1024 * 1024), 2))")
    public JVMMemoryInfoVO dto2vo(JVMMemoryInfo jvmMemoryInfo) {
        if (jvmMemoryInfo == null) {
            return null;
        }

        JVMMemoryInfoVO jVMMemoryInfoVO = new JVMMemoryInfoVO();

        jVMMemoryInfoVO.setName(jvmMemoryInfo.getName());
        jVMMemoryInfoVO.setType(jvmMemoryInfo.getType());

        jVMMemoryInfoVO.setCommitted(Arith.div(jvmMemoryInfo.getCommitted(), (1024 * 1024), 2));
        jVMMemoryInfoVO.setInit(Arith.div(jvmMemoryInfo.getInit(), (1024 * 1024), 2));
        jVMMemoryInfoVO.setMax(Arith.div(jvmMemoryInfo.getMax(), (1024 * 1024), 2));
        jVMMemoryInfoVO.setUsed(Arith.div(jvmMemoryInfo.getUsed(), (1024 * 1024), 2));

        return jVMMemoryInfoVO;
    }
}
