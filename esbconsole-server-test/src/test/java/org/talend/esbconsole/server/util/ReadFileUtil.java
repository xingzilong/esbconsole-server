package org.talend.esbconsole.server.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 读取文件工具类
 *
 * @author xingzilong
 * @date 2023/10/11
 */
public class ReadFileUtil {

    /**
     * 读取指定文件中的内容
     *
     * @param filePath resource目录下的问及那路径。 如：mockdata/joblog.json
     * @return
     */
    public static String readJson(String filePath) {
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        StringBuffer joblog4Json = new StringBuffer();
        try (InputStream inputStream = classPathResource.getInputStream()) {
            int count = 0;
            byte[] buffer = new byte[1024];
            while ((count = inputStream.read(buffer)) != -1) {
                joblog4Json.append(new String(buffer, 0, count));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joblog4Json.toString();

    }
}
