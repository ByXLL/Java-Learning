package com.example.springbootdemo.mapper;

import com.example.springbootdemo.bean.Department;
import org.apache.ibatis.annotations.*;

// 指定这是一个操作数据库的mapper
@Mapper
public interface DepartmentMapper {
    @Select("select * from department where id=#{id}")
    Department getDeptById(Integer id);

    @Delete("delete from department where id=${id}")
    int deleteDeptById(Integer id);

    // 配置 是否使用自动生成的组件  并告诉 id 属性是封装组件的 在插入完成后返回
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    int insertDept(Department department);

    @Update("update department set departmentName=#{department_name} where id=#{id}")
    int updateDept(Department department);

}
