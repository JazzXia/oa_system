package com.qtatelier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
public class OaApplication {
    public static void main(String [] args) {
        SpringApplication.run(OaApplication.class,args);
    }
}
