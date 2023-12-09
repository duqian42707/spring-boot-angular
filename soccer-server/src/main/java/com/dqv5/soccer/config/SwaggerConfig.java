package com.dqv5.soccer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


/**
 * @author admin
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    private static final String AUTH_TOKEN_NAME = "token";

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dqv5.soccer.web"))
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList((securityScheme())));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(defaultAuth()))
                //.forPaths(PathSelectors.regex("/*.*"))
                .build();
    }

    private SecurityReference defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{
                new AuthorizationScope("global", "accessEverything")
        };
        return SecurityReference.builder().reference(AUTH_TOKEN_NAME).scopes(authorizationScopes).build();
    }

    private SecurityScheme securityScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name(AUTH_TOKEN_NAME).description("JWT Token").build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-boot-angular")
                .description("基于Spring boot + angular 的前后端分离框架。")
                .version("1")
                .build();
    }

}

