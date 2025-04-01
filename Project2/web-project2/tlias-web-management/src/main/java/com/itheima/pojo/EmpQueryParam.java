package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpQueryParam {
    private Integer page;
    private Integer pageSize;
    private String name;
    private Integer gender;
    private @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin;
    private @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate end;
}
