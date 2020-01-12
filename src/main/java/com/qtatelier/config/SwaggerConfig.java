package com.qtatelier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author JazzXia
 * @description
 * @create 2019-11-24-14:17
 * @email jazzxiaw@qq.com
 * @since 2019
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("oa_system's server")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qtatelier.OASystem"))
                .paths(PathSelectors.any())
                .build();//build是工厂
    }


    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("JazzXia","https://www.qtatelier.com/","1104841692@qq.com");
        return new ApiInfo(
                "OA系统的swagger",
                "项目基于SpringBoot2.x",
                "v1.0",
                "https://www.qtatelier.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
