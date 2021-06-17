package com.mayblackcat.helloblog.controller.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//拦截所有控制器
@ControllerAdvice
public class ErrorController {

    private final Logger  logger= LoggerFactory.getLogger(this.getClass());

    //标识该方法可以做异常处理
    @ExceptionHandler(Exception.class)
    //处理异常信息
    public ModelAndView ErrorExceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //打印异常日志
        logger.error("url : {},exception : {}",request.getRequestURL(),e);
        //判断异常是否已经存在状态码
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        //将异常信息添加到视图中
        ModelAndView mav=new ModelAndView();
        mav.addObject("url",request.getRequestURL());
        mav.addObject("exception",e);
        //设置该异常返回的页面
        mav.setViewName("error/business");
        return mav;

    }
}
