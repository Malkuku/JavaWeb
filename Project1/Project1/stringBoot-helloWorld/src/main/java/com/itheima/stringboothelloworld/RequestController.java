package com.itheima.stringboothelloworld;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @RequestMapping("/request")
    public String request(HttpServletRequest request) {
        //1.获取请求方式
        String method = request.getMethod();
        System.out.println("method : " + method);
        //2.获取请求路径
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        System.out.printf("url : %s, uri : %s\n", url, uri);
        //3.获取请求协议
        String protocol = request.getProtocol();
        System.out.printf("protocol : %s\n", protocol);
        //4.获取请求参数
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.printf("name : %s, age : %s\n", name, age);
        //5.获取请求头
        String header = request.getHeader("Accept");
        System.out.printf("header : %s\n", header);

        return "ok";
    }

}
