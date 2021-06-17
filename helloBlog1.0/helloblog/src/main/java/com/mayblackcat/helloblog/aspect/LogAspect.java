package com.mayblackcat.helloblog.aspect;


import com.mayblackcat.helloblog.config.properties.LogProperties;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAspect {
    private  LogProperties logs;
    private final Logger logger= LoggerFactory.getLogger(this.getClass());



    //配置切入点
    @Pointcut("within(com.mayblackcat.helloblog.controller..*)")
    public void log(){}

    //前通知
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        setLog(joinPoint);
        logger.info("Request : {}",logs);
    }

    //返回后通知
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("result : {}",result);
    }

    //设置属性
    protected void setLog(JoinPoint joinPoint){
        //获取请求
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        //利用切点获取方法名，返回类型，参数
        String classMethod=joinPoint.getSignature().getDeclaringType()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        logs =new LogProperties(url,ip,classMethod,args);
    }
}
