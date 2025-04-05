package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {

    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
    //        "order by e.update_time desc")
    List<Emp> list(EmpQueryParam empQueryParam);

    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    Long count();

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "    values(#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime}) ")

    void save(Emp emp);

    void deleteById(List<Integer> list);

    Emp getInfoById(Integer id);

    void update(Emp emp);

    Emp selectByUsernameAndPassword(Emp emp);
}
