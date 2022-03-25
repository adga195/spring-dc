package com.adga.springdc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class CRMLoggingAspect {

    final Logger logger = Logger.getLogger(this.getClass().getName());

    @Before("com.adga.springdc.aspect.pointcut.PCExpressions.executeAppMethod()")
    public void executeBeforeAppMethod(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        logger.info("@Before " + methodSignature);

        Object[] args = joinPoint.getArgs();

        for(Object arg : args)
            logger.info("arg: " + arg.getClass() + " " + arg);

    }

    @AfterReturning(
            pointcut = "com.adga.springdc.aspect.pointcut.PCExpressions.executeAppMethod()",
            returning = "result"
    )
    public void executeAfterReturningAppMethod(JoinPoint joinPoint, Object result) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        logger.info("@AfterReturning " + methodSignature);

        logger.info("result: " + result.getClass() + " " + result);

    }

}