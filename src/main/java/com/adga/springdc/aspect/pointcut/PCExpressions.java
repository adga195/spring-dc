package com.adga.springdc.aspect.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PCExpressions {

    @Pointcut("execution(* com.adga.springdc.service.*.*(..))")
    public void executeService() { }

    @Pointcut("execution(* com.adga.springdc.controller.*.*(..))")
    public void executeController() { }

    @Pointcut("execution(* com.adga.springdc.dao.*.*(..))")
    public void executeDao() { }

    @Pointcut("executeService() || executeController() || executeDao()")
    public void executeAppMethod() { }

}
