package com.itheima.stringboothelloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//标识为一个请求处理类
public class HelloController {
    @RequestMapping("/hello") //请求路径为 /hello时，调用该方法
    public String hello(String name){
        System.out.println("name : " + name);
        return "hello" + name + '~';
    }
}
