package org.talend.esbconsole.server.start.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * Swagger3相关配置
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Configuration
// @EnableSwagger2 //swagger3版本不需要使用这个注解，当然写上也无所谓~
public class Swagger3Config {
    @Bean
    public OpenAPI openApi(@Value("${spring.application.name}") String applicationName,
                           ObjectProvider<BuildProperties> buildProperties) {
        OpenAPI openAPI = new OpenAPI();
        // 基本信息
        openAPI.info(new Info().title(applicationName).description("API开发文档").version(
                Optional.ofNullable(buildProperties.getIfAvailable()).map(BuildProperties::getVersion).orElse("1.0.0")));
        openAPI
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")));
        return openAPI;
    }
}
