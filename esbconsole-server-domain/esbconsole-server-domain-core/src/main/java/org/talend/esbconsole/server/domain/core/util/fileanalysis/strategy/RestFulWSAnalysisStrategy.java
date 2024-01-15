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
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * restfulwebservice 分析策略
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Slf4j
public class RestFulWSAnalysisStrategy implements AnalysisStrategy {

    private String serviceDir;

    public RestFulWSAnalysisStrategy() {
    }

    public RestFulWSAnalysisStrategy(String serviceDir) {
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
     * @param zipFile       源文件 .jar 文件
     * @param serviceDOList 所有服务的描述对象 {@link ServiceDO}
     * @throws ServiceVerifyException 产生此异常则代表service服务包验证未通过
     */
    public void verifyFile(File zipFile, ServiceDO service, List<ServiceDO> serviceDOList) throws ServiceVerifyException {
        List<ManifestInfo> manifestList = new ArrayList<ManifestInfo>();
        manifestList.add(AnalysisUtil.readManifest4JarFile(zipFile));
        String rs = AnalysisUtil.verifyFile(manifestList, serviceDOList);
        if (!rs.equals("")) {
            throw new ServiceVerifyException(rs);
        }
        // 验证通过，写入MANIFEST.MF文件的元数据信息
        service.setManifestInfo(JSONObject.toJSONString(manifestList));
    }

    /**
     * 读取代码包中的 serviceaddress 属性值，当作服务（webservice接口）的唯一标识，并且当且仅当service代码包为接口服务是存在serviceaddress属性值。
     * 判断该服务（webservice接口）是否开启了SAM监控
     *
     * @param zipFile 源文件 .jar 文件
     * @param service 服务（webservice接口）描述对象 {@link ServiceDO}
     */
    public void analysisFile(File zipFile, ServiceDO service) {
        try (ZipFile zip = new ZipFile(zipFile, Charset.forName(Constants.UTF8));
             InputStream inputStream = new BufferedInputStream(new FileInputStream(zipFile));
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                // 文件名的后缀过滤，读取serviceaddress和isEnabledSAM
                if (zipEntry.getName().endsWith("job.xml")) {
                    // 文件名的后缀过滤，读取serviceaddress和isEnabledSAM
                    File tempFile = AnalysisUtil.writeTempFile(zipEntry, zip, serviceDir);
                    service.setServiceKey(AnalysisUtil.readXML(tempFile, "server", "address"));
                    if (AnalysisUtil.isEnabledSAM(tempFile)) {
                        service.setEnabledSAM("1");
                    } else {
                        service.setEnabledSAM("0");
                    }
                    service.setJob(JSONObject.toJSONString(Arrays.asList(AnalysisUtil.processAndFilterBundleNameListFroRestFul(tempFile))));
                    tempFile.delete();
                    return;
                }
            }
        } catch (IOException e) {
            log.error("verifyFile异常：", e);
        }
    }
}
