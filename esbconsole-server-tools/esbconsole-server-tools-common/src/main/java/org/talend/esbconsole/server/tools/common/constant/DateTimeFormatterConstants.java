package org.talend.esbconsole.server.tools.common.constant;

import java.time.format.DateTimeFormatter;

/**
 * 日期时间格式化 常量
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class DateTimeFormatterConstants {

    /**
     * 定义日期格式化模式
     */
    public static String pattern5yyyyMMdd = "yyyy-MM-dd"; // 例如：2021-09-06

    /**
     * 创建日期时间格式化器
     */
    public static DateTimeFormatter formatter5yyyyMMdd = DateTimeFormatter.ofPattern(pattern5yyyyMMdd);

}
