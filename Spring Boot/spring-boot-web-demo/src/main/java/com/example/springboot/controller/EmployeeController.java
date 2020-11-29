package com.example.springboot.controller;

import com.example.springboot.dao.EmployeeDao;
import com.example.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    // 注入 dao
    @Autowired
    EmployeeDao employeeDao;

    // 查询所有员工列表
    @GetMapping("/getEmps")
    public String getEmps(Model model) {
        // 调用dao里面的 getAll方法  接受数据
        Collection<Employee> employees = employeeDao.getAll();

        // 将查询 出来的数据 放到请求域中
        model.addAttribute("employees",employees);

        return "/emp/list";
    }
}
