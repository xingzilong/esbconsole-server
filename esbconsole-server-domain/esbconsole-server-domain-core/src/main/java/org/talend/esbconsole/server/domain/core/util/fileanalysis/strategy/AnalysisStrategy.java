package org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy;


import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.tools.base.exception.ServiceVerifyException;

import java.io.File;
import java.util.List;

/**
 * 部署代码包的解析策略  策略模式
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public interface AnalysisStrategy {

    /**
     * 验证该服务文件是否可部署（防止重复部署）
     *
     * @param codeFile 代码文件
     * @param service  代码文件的描述对象
     * @param serviceDOList  所有服务集合
     * @throws ServiceVerifyException 产生此异常则代表service服务包验证未通过
     */
    public void verify(File codeFile, ServiceDO service, List<ServiceDO> serviceDOList) throws ServiceVerifyException;

    /**
     * 将分析得到的相关信息存储到 service 对象中去
     *
     * @param codeFile 代码文件
     * @param service  代码文件的描述对象
     */
    public void analysis(File codeFile, ServiceDO service);
}
