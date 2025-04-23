package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //作用对象：方法
@Retention(RetentionPolicy.RUNTIME) //保留策略：运行时有效
public @interface LogOperation {
}
