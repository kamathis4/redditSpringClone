package com.sample.redditbackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun redditClone():Docket{
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(getApiInfo())
    }

    fun getApiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Reddit Backend Clone")
            .version("1.0")
            .description("Reddit backend Clone using Spring-boot and Kotlin")
            .license("Apache License Version 2.0")
            .build()
    }
}