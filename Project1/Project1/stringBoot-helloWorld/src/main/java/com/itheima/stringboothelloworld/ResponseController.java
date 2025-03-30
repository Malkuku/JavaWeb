package com.itheima.stringboothelloworld;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ResponseController {
    @RequestMapping("/response")
    public void response(HttpServletResponse response) {
        //1.设置响应状态码
        response.setStatus(401);
        //2.设置响应头
        response.setHeader("name","heyi");
        //3.设置响应体
        try {
            response.getWriter().write("<h1>hello response<h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return;
    }
    //通过Spring的ResponseEntity来设置响应
    @RequestMapping("/response2")
    public ResponseEntity<String> response2() {
        return ResponseEntity
                .status(401)
                .header("name", "heyi")
                .body("<h1>hello response<h1>");
    }
}
