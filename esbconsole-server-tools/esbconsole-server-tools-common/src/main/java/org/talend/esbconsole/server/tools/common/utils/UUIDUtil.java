package org.talend.esbconsole.server.tools.common.utils;

import java.util.UUID;

/**
 * 获取UUID工具类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
