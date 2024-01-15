package org.talend.esbconsole.server.start;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 程序的启动类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@SpringBootApplication
@Slf4j
@ComponentScan(value = {"org.talend.esbconsole.server"})
@MapperScan("org.talend.esbconsole.server.domain.repository.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("ESBWebConsoleApplication启动成功");
    }

}
