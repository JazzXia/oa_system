package com.qtatelier.common.aop;

import com.alibaba.fastjson.JSONObject;

import com.auth0.jwt.JWT;
import com.qtatelier.OASystem.basics.optlog.model.OptLog;
import com.qtatelier.OASystem.basics.optlog.service.OptLogService;
import com.qtatelier.config.IpAdrressUtil;
import com.qtatelier.config.ToolRandom;
import com.qtatelier.config.ToolRedis;
import com.qtatelier.config.ToolTime;
import com.qtatelier.dto.BlogUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-14 13:17
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */

@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {
    //注入Service用于把日志保存数据库，实际项目入库采用队列做异步
    @Autowired
    private OptLogService optLogService;

    @Autowired
    private ToolRedis toolRedis;

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Service层切点
    @Pointcut("@annotation(com.qtatelier.common.aop.SystemServiceLog)")
    public void serviceAspect(){
    }

    //Controller层切点
    @Pointcut("@annotation(com.qtatelier.common.aop.SystemControllerLog)")
    public void controllerAspect(){
    }


    /**
     * @Description  前置通知  用于拦截Controller层记录用户的操作
     */
    @Before("controllerAspect()")
    public void doBefore( JoinPoint joinPoint) throws Exception {
        OptLog optLog= new OptLog();
        try {
            //if (object instanceof ResultView) {
                //*========控制台输出=========*//
                System.out.println("==============前置通知开始==============");
                System.out.println("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
                System.out.println("方法描述：" + getControllerMethodDescription(joinPoint));

                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();

                 String token = request.getHeader("token");
                //*========数据库日志=========*//

               /* String token = request.getHeader("token");
                if (StringUtils.isNotBlank(token)) {
                    Object _user = this.redis.get(token);
                    if (_user != null) {
                        BlogUser user = (BlogUser) this.redis.get(token);
                        //设置请求用户的id
                        optLog.setAccountId(user.getUserId());
                    }
                }*/
                //设置描述
                optLog.setRemark(getControllerMethodDescription(joinPoint));
                //设置请求的方法
                optLog.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
                //设置请求操作类型
                optLog.setOperation(getControllerMethodOptType(joinPoint));
                //设置请求用户的id
                 optLog.setAccountId(JWT.decode(token).getAudience().get(0));
                //设置操作日志id [随机id]
                optLog.setLogId( ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase());
                //设置操作请求ip
                optLog.setIp(IpAdrressUtil.getIpAdrress(request));
                //设置模块名
                optLog.setModuleName(getControllerMethodmoduleName(joinPoint));
                //设置操作结果
                optLog.setOptResult(getControllerMethodDescription(joinPoint) + "成功");

                //请求的参数
                Object[] args = joinPoint.getArgs();
                //将参数所在的数组转换成json
                String params = null;
                try {
                    params = JSONObject.toJSONString(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(params);
                optLog.setOperTime(ToolTime.getStringByAllTimeSpacing( new Date(),"yyyy-MM-dd HH:mm:ss" ));
                //保存数据库
                optLogService.saveLog(optLog);
            //}
        }catch (Exception e){
            //记录本地异常日志
            //设置操作结果
            optLog.setOptResult(getControllerMethodDescription(joinPoint)+"失败");
            logger.error("==前置通知异常==");
            logger.error("异常信息：{}",e.getMessage());
        }
    }


    /**
     * @Description  获取注解中对方法的描述信息 用于Controller层注解
     * @date
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


    /**
     * @Description  获取注解中对方法的操作类型 用于Controller层注解
     * @date
     */
    public static String getControllerMethodOptType(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String optType = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    optType = method.getAnnotation(SystemControllerLog.class).optType();
                    break;
                }
            }
        }
        return optType;
    }



    /**
     * @Description  获取注解中对方法的模块名 用于Controller层注解
     * @date
     */
    public static String getControllerMethodmoduleName(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String moduleName = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    moduleName = method.getAnnotation(SystemControllerLog.class).moduleName();
                    break;
                }
            }
        }
        return moduleName;
    }






    /**
     * @Description  异常通知 用于拦截service层记录异常日志
     * @date
     */
    @AfterThrowing(pointcut = "serviceAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String params = "";
        if (joinPoint.getArgs()!=null&&joinPoint.getArgs().length>0){
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params+= JSONObject.toJSON(joinPoint.getArgs()[i])+";";
            }
        }
        try{
            /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));
            System.out.println("请求参数:" + params);
            /*==========数据库日志=========*/
            OptLog optLog= new OptLog();
            optLog.setRemark(getControllerMethodDescription(joinPoint));
            optLog.setOperation("1");
            optLog.setIp( IpAdrressUtil.getIpAdrress(request));
            optLog.setAccountId("1");
            optLog.setLogId("1");
            optLog.setOperTime(ToolTime.getNowStringByAllTime());
            //保存到数据库
            optLogService.saveLog(optLog);
        }catch (Exception ex){
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }


    /**
     * @Description  获取注解中对方法的描述信息 用于service层注解
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
