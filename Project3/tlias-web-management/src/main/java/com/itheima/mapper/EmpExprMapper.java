package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void insertBach(List<EmpExpr> exprList);

    void deleteByEmpId(List<Integer> list);
}
