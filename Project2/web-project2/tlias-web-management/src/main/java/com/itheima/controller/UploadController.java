package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
//    @PostMapping("/upload")
//    public Result handleFileUpload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("文件上传:{}{}{}",name,age,file);
//        //获取原始文件名
//        String fileName =  file.getOriginalFilename();
//
//        //UUID生成新的文件名
//        String extension = fileName.substring(fileName.lastIndexOf("."));
//        fileName = UUID.randomUUID().toString()+extension;
//
//        //保存在本地目录
//        file.transferTo(new File("C:\\Desktop\\" + fileName));
//
//        return Result.success();
//    }

    @Autowired
    private AliyunOSSOperator ossClient;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result handleFileUpload(MultipartFile file) throws Exception {
        log.info("文件上传:{}",file.getOriginalFilename());

        String url = ossClient.upload(file.getBytes(), file.getOriginalFilename());
        log.info(url);
        return Result.success(url);
    }
}
