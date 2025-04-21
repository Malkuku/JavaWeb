package com.itheima.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.utils.CurrentHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Around("@annotation(com.itheima.anno.LogOperation)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result =  joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        // 获取方法信息
        OperateLog log = new OperateLog();
        log.setOperateEmpId(CurrentHolder.getCurrentId());
        log.setOperateTime(LocalDateTime.now());
        log.setClassName(joinPoint.getTarget().getClass().getName());
        log.setMethodName(joinPoint.getSignature().getName());
        log.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        log.setReturnValue(result != null ? result.toString() : "void");
        log.setCostTime(costTime);

        //保存日志
        operateLogMapper.insert(log);

        return result;
    }
}
