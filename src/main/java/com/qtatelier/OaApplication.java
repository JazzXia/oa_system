package com.qtatelier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @descrpition 启动类
 * @author xiaweiwei
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-1-12 10:05
 */
@SpringBootApplication
//开启缓存(redis)
@EnableCaching
//开启事务管理
@EnableTransactionManagement
//开启swagger
@EnableSwagger2
@EnableScheduling
public class OaApplication {
    public static void main(String [] args) {
        SpringApplication.run(OaApplication.class,args);
    }


    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //System.getProperty("user.dir") +
        String location =  "E:\\APP";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
