package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect //AOP的注解
@Component
public class RecordTimeAspect { //类名命名规范：Aspect
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt(){}
    @Around("pt()") //定义作用的方法范围
    public Object recodeTime(ProceedingJoinPoint pjp) throws Throwable{
        long begin = System.currentTimeMillis();
        Object result = pjp.proceed(); //执行原生方法
        long end = System.currentTimeMillis();
        log.info("方法{}: 耗时{}ms",pjp.getSignature(),end-begin);
        return result;
    }
}
