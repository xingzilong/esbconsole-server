package org.talend.esbconsole.server.domain.core.util.fileanalysis;


import org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy.AnalysisStrategy;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.tools.base.exception.ServiceVerifyException;

import java.io.File;
import java.util.List;

/**
 * 文件分析处理器 策略模式的上下文
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class AnalysisProcessor {

    /**
     * 分析策略
     */
    private AnalysisStrategy analysisStrategy;

    /**
     * 所有服务的信息
     */
    private List<ServiceDO> serviceDOList;

    public AnalysisProcessor() {
    }

    public AnalysisProcessor(AnalysisStrategy analysisStrategy) {
        this.analysisStrategy = analysisStrategy;
    }

    public AnalysisProcessor(AnalysisStrategy analysisStrategy, List<ServiceDO> serviceDOList) {
        this.analysisStrategy = analysisStrategy;
        this.serviceDOList = serviceDOList;
    }

    public AnalysisStrategy getAnalysisStrategy() {
        return analysisStrategy;
    }

    public void setAnalysisStrategy(AnalysisStrategy analysisStrategy) {
        this.analysisStrategy = analysisStrategy;
    }

    public List<ServiceDO> getServiceDOList() {
        return serviceDOList;
    }

    public void setServiceDOList(List<ServiceDO> serviceDOList) {
        this.serviceDOList = serviceDOList;
    }

    /**
     * 执行分析处理器，将分析得到的相关信息存储到 service 对象中去
     *
     * @param codeFile 代码文件
     * @param service  代码文件的描述对象
     * @throws ServiceVerifyException 产生此异常则代表service服务包验证未通过，应停止部署动作
     */
    public void execute(File codeFile, ServiceDO service) throws ServiceVerifyException {
        analysisStrategy.verify(codeFile, service, serviceDOList);
        analysisStrategy.analysis(codeFile, service);
    }
}
