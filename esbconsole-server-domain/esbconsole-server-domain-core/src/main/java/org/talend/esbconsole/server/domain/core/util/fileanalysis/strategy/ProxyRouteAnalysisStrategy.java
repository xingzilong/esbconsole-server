package org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy;

import com.alibaba.fastjson2.JSONObject;
import org.talend.esbconsole.server.domain.api.model.ManifestInfo;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.tools.base.exception.ServiceVerifyException;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 代理路由 分析策略
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Slf4j
public class ProxyRouteAnalysisStrategy implements AnalysisStrategy {

    private String serviceDir;

    public ProxyRouteAnalysisStrategy() {
    }

    public ProxyRouteAnalysisStrategy(String serviceDir) {
        this.serviceDir = serviceDir;
    }

    @Override
    public void verify(File codeFile, ServiceDO service, List<ServiceDO> serviceDOList) throws ServiceVerifyException {
        verifyFile(codeFile, service, serviceDOList);
    }

    @Override
    public void analysis(File codeFile, ServiceDO service) {
        analysisFile(codeFile, service);
    }

    /**
     * 验证此服务包是否能够正常部署。主要提取kar文件中的所有的一级jar文件中的MANIFEST.MF文件元数据。
     * 然后与数据库中的信息进行比对，如若未有数据重合，则验证通过，否则失败
     * <p/>
     *
     * @param zipFile       源文件 .kar 文件
     * @param serviceDOList 所有服务的描述对象 {@link ServiceDO}
     * @throws ServiceVerifyException 产生此异常则代表service服务包验证未通过
     */
    public void verifyFile(File zipFile, ServiceDO service, List<ServiceDO> serviceDOList) throws ServiceVerifyException {
        List<ManifestInfo> manifestList = new ArrayList<ManifestInfo>();
        try (ZipFile zip = new ZipFile(zipFile, Charset.forName(Constants.UTF8));
             InputStream inputStream = new BufferedInputStream(new FileInputStream(zipFile));
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {      // 不是目录的时候
                // 文件名的后缀过滤，读取 jar 文件
                if (zipEntry.getName().endsWith(".jar")) {
                    File tempFile = AnalysisUtil.writeTempFile(zipEntry, zip, serviceDir);
                    manifestList.add(AnalysisUtil.readManifest4JarFile(tempFile));
                    tempFile.delete();
                }
            }
        } catch (IOException e) {
            log.error("verifyFile异常：", e);
        }
        String rs = AnalysisUtil.verifyFile(manifestList, serviceDOList);
        if (!rs.equals("")) {
            throw new ServiceVerifyException(rs);
        }
        // 验证通过，写入MANIFEST.MF文件的元数据信息
        service.setManifestInfo(JSONObject.toJSONString(manifestList));
    }

    /**
     * 读取并向 service 设置featureName和bundleName
     *
     * @param zipFile 源文件 .kar 文件
     * @param service 服务（webservice接口）描述对象 {@link ServiceDO}
     */
    public void analysisFile(File zipFile, ServiceDO service) {
        try (ZipFile zip = new ZipFile(zipFile, Charset.forName(Constants.UTF8));
             InputStream inputStream = new BufferedInputStream(new FileInputStream(zipFile));
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                // 文件名的后缀过滤，读取BundleNameList
                if (zipEntry.getName().endsWith("features.xml")) {
                    File tempFile = AnalysisUtil.writeTempFile(zipEntry, zip, serviceDir);
                    service.setFeatureName(AnalysisUtil.readXML(tempFile, Constants.FEATURE, Constants.NAME));
                    service.setBundleName(JSONObject.toJSONString(AnalysisUtil.getBundleNameListForXML(tempFile)));
                    tempFile.delete();
                }
                // 文件名的后缀过滤，读取serviceaddress和isEnabledSAM
                if (zipEntry.getName().endsWith(".jar")) {
                    File tempFile = AnalysisUtil.writeTempFile(zipEntry, zip, serviceDir);
                    // 递归
                    analysisFile(tempFile, service);
                    tempFile.delete();
                } else if (zipEntry.getName().endsWith("blueprint.xml")) {
                    File tempFile = AnalysisUtil.writeTempFile(zipEntry, zip, serviceDir);
                    if (AnalysisUtil.isEnabledSAM(tempFile)) {
                        service.setEnabledSAM("1");
                    } else {
                        service.setEnabledSAM("0");
                    }
                    tempFile.delete();
                    return;
                }
            }
        } catch (IOException e) {
            log.error("analysisFile异常：", e);
        }
    }
}
