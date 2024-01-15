package org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy;

import com.alibaba.fastjson2.JSONArray;
import org.talend.esbconsole.server.domain.api.model.ManifestInfo;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.tools.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 代码文件分析工具类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Slf4j
public class AnalysisUtil {

    private static final String LOG_INFO = "dom4j-读取xml文件";

    /**
     * 验证对比将要部署的服务是否与已部署服务存在冲突
     *
     * @param manifestList  将要部署的服务的MANIFEST.MF文件元数据对象的集合
     * @param serviceDOList 所有已部署服务的集合
     * @return 如果没有发生冲突，即验证通过，返回空字符串""。
     * 如果发生冲突，即验证未通过，返回与其冲突的服务名称
     */
    public static String verifyFile(List<ManifestInfo> manifestList, List<ServiceDO> serviceDOList) {
        for (ServiceDO serviceDO : serviceDOList) {
            List<ManifestInfo> serviceManifestList = JSONArray.parseArray(serviceDO.getManifestInfo(), ManifestInfo.class);
            for (ManifestInfo serviceManifest : serviceManifestList) {
                for (ManifestInfo manifest : manifestList) {
                    if (serviceManifest.equalsValue(manifest)) {
                        return serviceDO.getName();
                    }
                }
            }
        }
        return "";
    }

    /**
     * 读取 jar 文件中的MANIFEST.MF文件元数据
     *
     * @param jarFile
     * @return
     */
    public static ManifestInfo readManifest4JarFile(File jarFile) {
        ManifestInfo manifestInfo = new ManifestInfo();
        try (JarFile tempJarFile = new JarFile(jarFile)) {
            Manifest manifest = tempJarFile.getManifest();
            if (manifest != null) {
                String bundleSymbolicName = manifest.getMainAttributes().getValue("Bundle-SymbolicName");
                String bundleVersion = manifest.getMainAttributes().getValue("Bundle-Version");
                manifestInfo.setBundleSymbolicName(bundleSymbolicName);
                manifestInfo.setBundleVersion(bundleVersion);
            }
        } catch (IOException e) {
            log.error("readMANIFEST.MF文件异常：" + e.getMessage());
        }
        return manifestInfo;
    }

    /**
     * 将临时文件写入到特定的文件目录下
     *
     * @param zipEntry   文件条目对象
     * @param zip        原始代码包文件
     * @param serviceDir 临时目录的地址
     * @return
     */
    public static File writeTempFile(ZipEntry zipEntry, ZipFile zip, String serviceDir) {
        String fileName = "temp-" + zipEntry.getName().substring(zipEntry.getName().lastIndexOf("/") + 1);
        File tempZipFile = new File(serviceDir + fileName);
        try (OutputStream tempZipFileOS = new FileOutputStream(tempZipFile);
             InputStream zipFileIS = zip.getInputStream(zipEntry);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFileIS);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempZipFileOS)) {
            // 定义一个缓冲区，这里使用了 8KB 的缓冲区大小
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            log.error("writeTempFile异常：", e);
        }
        return tempZipFile;
    }

    /**
     * 从指定的xml中读取 指定元素 elementName 上的指定属性 attributeName
     *
     * @param xmlFile       xml文件对象
     * @param elementName   读取的元素名
     * @param attributeName 读取的属性名
     * @return
     */
    public static String readXML(File xmlFile, String elementName, String attributeName) {
        String attributeValue = null;
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(xmlFile);
            Element rootElement = document.getRootElement();
            Element element = rootElement.element(elementName);
            Attribute attribute = element.attribute(attributeName);
            attributeValue = attribute.getValue();
        } catch (DocumentException e) {
            log.error(LOG_INFO + xmlFile.getName() + "异常", e);
        }
        return attributeValue;
    }

    /**
     * 从 xml 文件中获取是否开启了SAM监控，当且仅当返回值为 true 时为开启了SAM监控
     *
     * @param xmlFile xml文件对象
     * @return
     */
    public static Boolean isEnabledSAM(File xmlFile) {
        Boolean enable = false;
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(xmlFile);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements("reference");
            if (elements != null) {
                for (Element element : elements) {
                    if ("eventFeature".equals(element.attribute("id").getValue())
                            && Constants.ESB_SAM_PACKAGE.equals(element.attribute("interface").getValue())) {
                        enable = true;
                    }
                }
            }
        } catch (DocumentException e) {
            log.error(LOG_INFO + xmlFile.getName() + "异常", e);
        }
        return enable;
    }

    /**
     * 从 xml 文件中获取该feature的所有bundle
     *
     * @param xmlFile xml文件对象
     * @return
     */
    public static List<String> getBundleNameListForXML(File xmlFile) {
        List<String> bundleNameList = new ArrayList<>();
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(xmlFile);
            Element rootElement = document.getRootElement();
            Element featureElement = rootElement.element("feature");
            List<Element> bundleElements = featureElement.elements("bundle");
            if (bundleElements != null) {
                for (Element bundle : bundleElements) {
                    String text = bundle.getText();
                    if (StringUtil.isNotEmpty(text)) {
                        bundleNameList.add(text);
                    }
                }
            }
        } catch (DocumentException e) {
            log.error(LOG_INFO + xmlFile.getName() + "异常", e);
        }
        return bundleNameList;
    }

    /**
     * 处理bundleNameList，并过滤掉路由所属的bundle
     *
     * @param bundleNameList bundleName 的集合
     * @param filterString   路由所属的bundleName
     * @return
     */
    public static List<String> processAndFilterBundleNameListForRoute(List<String> bundleNameList, String filterString) {
        List<String> result = new ArrayList<>();

        for (String bundleName : bundleNameList) {
            String[] parts = bundleName.split("/");
            if (parts.length >= 2) {  // Ensure there are at least 3 parts separated by "/"
                String subString = parts[parts.length - 2];
                if (!subString.equals(filterString)) {
                    result.add(subString.substring(filterString.length() + 1));
                }
            }
        }
        return result;
    }

    /**
     * 处理bundleNameList，并过滤掉路由所属的bundle
     *
     * @param bundleNameList bundleName 的集合
     * @param filterString   路由所属的bundleName
     * @return
     */
    public static List<String> processAndFilterBundleNameListFroSOAP(List<String> bundleNameList, String filterString) {
        List<String> result = new ArrayList<>();

        for (String bundleName : bundleNameList) {
            String[] parts = bundleName.split("/");
            if (parts.length >= 2) {
                // ws_hellosPortType_ws_hellosOperation-bundle将-bundle截掉
                String subString = parts[parts.length - 2].substring(0, parts[parts.length - 2].length() - 7);
                if (!subString.equals(filterString + "-control")) {
                    result.add(subString.substring(filterString.length() + 1));
                }
            }
        }
        return result;
    }

    /**
     * 从restful的代码包中的job.xml文件中读取job
     *
     * @param jobXML job.xml文件
     * @return
     */
    public static String processAndFilterBundleNameListFroRestFul(File jobXML) {
        String attributeValue = null;
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(jobXML);
            Element rootElement = document.getRootElement();
            Element serviceElement = rootElement.element("service");
            Element servicePropertiesElement = serviceElement.element("service-properties");
            List<Element> entryElements = servicePropertiesElement.elements("entry");
            if (entryElements != null) {
                for (Element entry : entryElements) {
                    if (entry.attribute("key").getValue().equals("name")) {
                        Attribute attribute = entry.attribute("value");
                        attributeValue = attribute.getValue();
                        return attributeValue;
                    }
                }
            }
        } catch (DocumentException e) {
            log.error(LOG_INFO + jobXML.getName() + "异常", e);
        }
        return attributeValue;
    }
}
