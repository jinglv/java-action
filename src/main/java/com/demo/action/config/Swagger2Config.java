package com.demo.action.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 *
 * @author jingLv
 * @date 2020/11/27
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * Swagger2信息
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // API基本信息
                .apiInfo(apiInfo())
                // 设置允许暴露的接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.action.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API基本信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Java综合项目")
                .description("综合实战")
                .contact(new Contact("xiaohong", "", ""))
                .version("1.0.0")
                .build();
    }
}
