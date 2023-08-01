package com.bootcamp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ServiceLoggingAspect {

    @Pointcut("within(com.bootcamp.service..*)")
    private void servicesClasses() {
    }

    @Pointcut("execution(public * *(..))")
    private void executePublicMethods() {
    }

    @Pointcut("servicesClasses() && executePublicMethods()")
    private void aroundServicePointcut() {
    }

    @Around("aroundServicePointcut()")
    public Object logAroundServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();

        log.warn("Method {} start with args {}.",
                signature.getName(), joinPoint.getArgs());

        Object proceed;
        try {

            proceed = joinPoint.proceed();
            log.warn("Method {} successful finished", signature.getName());
            return proceed;
        } catch (Throwable e) {
            log.error("Method {} threw an exception {} with massage {}",
                    signature.getName(), e.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
}
