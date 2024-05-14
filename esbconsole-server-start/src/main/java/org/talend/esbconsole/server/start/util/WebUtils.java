package org.talend.esbconsole.server.start.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Web相关的工具类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, int status, String string) {
        try {
            response.setStatus(status);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            log.error("response异常：", e);
        }
        return null;
    }
}