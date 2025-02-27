package com.Library.mgmt.Aspects;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequestResponseLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingAspect.class);  //so , instead of using sout.println, I can simply use log.info

    @Pointcut("execution(* com.Library.mgmt.Controller..*(..))")
    public void controllerMethods(){}   // declaration, marker, I can use this for multiple advices

    @Before("controllerMethods()")   // we can use both ways, but this is best practice
//    @Before("execution(* com.Library.mgmt.Controller..*(..))")
    public void logRequest(){
       HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//       System.out.println("Request : "+ request.getMethod() +" "+request.getRequestURI());
       log.info("Request : "+ request.getMethod() +" "+request.getRequestURI());
//        System.out.println("Request Headers");
        log.info("Request Headers");
       request.getHeaderNames().asIterator().forEachRemaining(header->log.info(header+":"+request.getHeader(header)));

    }

    @AfterReturning(value = "controllerMethods()", returning = "response")
    public void logResponse(Object response){
        if (response instanceof HttpServletResponse){
            HttpServletResponse response1 = (HttpServletResponse) response;
//            System.out.println("response status :"+ response1.getStatus());
            log.info("response status :"+ response1.getStatus());
        }else {
//            System.out.println("Response : "+ response.toString());
            log.info("Response : "+ response.toString());

        }
    }

}
