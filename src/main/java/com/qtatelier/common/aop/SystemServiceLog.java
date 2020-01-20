package com.qtatelier.common.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 自定义注解，拦截service
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-14 13:16
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
    String description() default "";
}

