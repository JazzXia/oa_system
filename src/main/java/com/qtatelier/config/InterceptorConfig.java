package com.qtatelier.config;

import com.qtatelier.dev_util.commons.Access.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-16 15:15
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");
    }

    /**
     * 静态不过滤
     * @param registry
     */
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry )
    {
/*        registry.addResourceHandler( "/" ).addResourceLocations( "classpath:/public/index.html" );
        registry.addResourceHandler( "/index.html" ).addResourceLocations( "classpath:/META-INF/resources/index.html" );
        registry.addResourceHandler( "/index.html" ).addResourceLocations( "classpath:/public/index.html" );
        registry.addResourceHandler( "/static/**" ).addResourceLocations( "classpath:/public/static/" );
        registry.addResourceHandler( "/**" ).addResourceLocations( "classpath:/META-INF/resources/**" );*/

        registry.addResourceHandler("/index.html").addResourceLocations("classpath:/META-INF/resources/index.html");
        registry.addResourceHandler("/").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers( registry );
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
