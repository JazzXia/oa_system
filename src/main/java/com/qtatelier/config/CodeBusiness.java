package com.qtatelier.config;

import org.springframework.stereotype.Component;

/**
 * @description: 枚举类，枚举信息
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-16 8:48
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */
@Component
public class CodeBusiness {

    /** redis中图片验证码key */
    public static final String REDIS_CAPTCHA_KEY = "CAPTCHA:{0}";

    /** 图片验证码缓存KEY */
    public static String IMAGE_CAPTCHA_KEY = "IMAGE_CAPTCHA:{0}";

    /** 短信验证码缓存KEY */
    public static String PHONE_CAPTCHA_KEY = "PHONE_CAPTCHA:{0}";

    /** 角色下灯杆分组  */
    public static String ROLE_STEM_GROUP = "ROLE_STEM_GROUP:";

    /** token前缀 */
    public static final String TOKEN_ACCESS_KEY = "access-token:";

    /** 默认超时时间为60分钟 */
    public static final Long SESSTION_TIME = 60 * 60L;

    /** 登录时效为5分钟 */
    public static final Long PASSWORD_OUT_TIME = 5 * 60L;

    /**短信验证码超时时间 */
    public static final int FORGET_PASS_INFO_TIME = 300000;



    /**
     * 操作类型
     */
    public static interface OPT_TYPE{
        /** 登录操作 */
        String LOGIN_CODE = "0";
        /** 注销操作 */
        String LOGOUT_CODE = "1";
        /**新增操作*/
        String ADD_CODE = "2";
        /** 修改操作 */
        String UPDATE_CODE = "3";
        /** 查询操作 */
        String SEARCH_CODE = "4";
        /** 删除操作 */
        String DELETE_CODE = "5";
        /**系统异常*/
        String SYSTEM_CODE = "6";
    }



    /**
     * 用户类型
     */
    public static interface ROLE_ID{
        /** 超级管理员 */
        String SM_CODE = "1";
        /** 普通用户 */
        String N_CODE = "2";

    }


    /**
     * 单元名
     */
    public static interface MODULE_NAME{
        /**登录模块*/
        String LOGIN_MODULE = "登录模块";
        /**用户模块*/
        String USER_MODULE = "用户模块";
        /**博文模块*/
        String DEPT_MODULE = "部门模块";
        /**职位模块*/
        String DUTY_MODULE = "职位模块";
        /**日志模块*/
        String LOG_MODULE = "日志模块";
        /**客户模块*/
        String CUSTOMER_MODULE = "客户模块";
        /**回访*/
        String REVISIT_MODULE = "回访模块";
    }

}
