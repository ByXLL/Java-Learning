package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.Department;
import com.example.springbootdemo.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

// 直接返回json数据的controller
@RestController
public class DeptController {

    // 注入 mapper
    @Autowired
    DepartmentMapper departmentMapper;

    // 查询
    @GetMapping("/getDepartment")
    public Department getDepartment(@PathParam("id") Integer id) {
        // 使用 departmentMapper 里面声明的方法
        return departmentMapper.getDeptById(id);
    }

    // 添加
    @GetMapping("/addDepartment")
    public Department addDepartment(Department department) {
        departmentMapper.insertDept(department);
        return department;
    }
}
