package org.talend.esbconsole.server.start.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据源（数据库表）初始化相关配置
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Component
public class DBInitializer implements InitializingBean {

    @Value("${datasource.script.dir}")
    private String dbScriptDir;

    @Value("${datasource.script.name}")
    private String dbScriptName;

    @Value("${datasource.script.type}")
    private String dbScriptType;

    @Autowired
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {

        boolean createTables = true;

        try (Connection conn = dataSource.getConnection()) {

            ResultSet rs = conn.getMetaData().getTables(null, null, "system_authority", null);
            while (rs.next()) {
                createTables = false;
            }
            ResultSet rS = conn.getMetaData().getTables(null, null, "SYSTEM_AUTHORITY", null);
            while (rS.next()) {
                createTables = false;
            }
            if (createTables) {
                log.info("数据源初始化开始");
                ScriptRunner sr = new ScriptRunner(conn);
                sr.setAutoCommit(false);
                sr.setStopOnError(false);
                sr.setLogWriter(null);
                sr.setErrorLogWriter(null);
                // 置字符集编码，不然可能导致SQL执行完，查看到的注释中文乱码
                InputStream scriptReader = getScriptReader();
                if (scriptReader == null) {
                    log.warn("数据源初始化失败，未找到有效初始化脚本文件");
                    return;
                }
                sr.runScript(new InputStreamReader(scriptReader, "UTF-8"));
                log.info("数据源初始化完成");
            } else {
                log.info("数据源已存在相关表数据，无需初始化");
            }
        } catch (SQLException e) {
            log.error("数据源信息检查异常：", e);
        }
    }

    private InputStream getScriptReader() {
        InputStream scriptReader = null;
        try {
            if (dbScriptDir.startsWith("classpath:")) {
                scriptReader = new FileInputStream(ResourceUtils.getFile(dbScriptDir + File.separator + dbScriptName + "." + dbScriptType));
            } else {
                scriptReader = new FileInputStream(dbScriptDir + File.separator + dbScriptName + "." + dbScriptType);
            }
        } catch (FileNotFoundException e) {
            log.error("数据源初始化脚本未找到");
        }
        return scriptReader;
    }
}

