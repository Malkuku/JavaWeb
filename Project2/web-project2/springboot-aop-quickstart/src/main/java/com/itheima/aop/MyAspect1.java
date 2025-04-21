package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect1 {
    @Before("execution(public void com.itheima.service.impl.DeptServiceImpl.*(..))")
    public void before() {
        log.info("doTest1");
    }

    @Before("@annotation(com.itheima.anno.LogOperation)")
    public void before2() {
        log.info("before2");
    }
}
