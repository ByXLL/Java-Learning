package com.example.springboot.controller;

import com.example.springboot.dao.DepartmentDao;
import com.example.springboot.dao.EmployeeDao;
import com.example.springboot.entities.Department;
import com.example.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

@Controller
public class EmployeeController {
    // 注入 dao
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    // 查询所有员工列表
    @GetMapping("/getEmps")
    public String getEmps(Model model) {
        // 调用dao里面的 getAll方法  接受数据
        Collection<Employee> employees = employeeDao.getAll();

        // 将查询 出来的数据 放到请求域中
        model.addAttribute("employees",employees);

        return "emp/list";
    }

    // 跳转员工添加页
    @GetMapping("/emp")
    public String toAddEmp(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    // 添加员工
    @PostMapping("/updateEmp")
    public String addEmp(Employee employee) {
        System.out.println("保存的员工信息："+employee);
        employeeDao.save(employee);
        // 添加成功后 重定向到列表页
        return "redirect:/getEmps";
    }

    // 通过id获取员工信息
    @GetMapping("/getEmpById")
//    @GetMapping("/getEmpById/{id}")
    public String getEmpById(@RequestParam("id") Integer id,Model model) {
        //  @PathVariable Integer id
        System.out.println("修改的员工信息的id"+id);
        // 向页面传递员工数据
        Employee employee = employeeDao.get(id);
        model.addAttribute("empInfo",employee);

        // 页面中需要显示的选项
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        System.out.println("返回给页面的数据"+employee);
        return "emp/add";
    }


    // 员工修改
    @PutMapping("/updateEmp")
    public String updateEmployee(Employee employee) {
        System.out.println("编辑提交的数据"+employee);
        employeeDao.save(employee);
        return "redirect:/getEmps";
    }

    // 员工删除
    @DeleteMapping("/deleteEmp")
    public String deleteEmpById(@RequestParam("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/getEmps";
    }
}
