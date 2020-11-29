package com.example.springboot.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeDao {

	private static Map<Integer, com.example.springboot.entities.Employee> employees = null;
	
	@Autowired
	private com.example.springboot.dao.DepartmentDao departmentDao;
	
	static{
		employees = new HashMap<Integer, com.example.springboot.entities.Employee>();

		employees.put(1001, new com.example.springboot.entities.Employee(1001, "E-AA", "aa@163.com", 1, new com.example.springboot.entities.Department(101, "D-AA")));
		employees.put(1002, new com.example.springboot.entities.Employee(1002, "E-BB", "bb@163.com", 1, new com.example.springboot.entities.Department(102, "D-BB")));
		employees.put(1003, new com.example.springboot.entities.Employee(1003, "E-CC", "cc@163.com", 0, new com.example.springboot.entities.Department(103, "D-CC")));
		employees.put(1004, new com.example.springboot.entities.Employee(1004, "E-DD", "dd@163.com", 0, new com.example.springboot.entities.Department(104, "D-DD")));
		employees.put(1005, new com.example.springboot.entities.Employee(1005, "E-EE", "ee@163.com", 1, new com.example.springboot.entities.Department(105, "D-EE")));
	}
	
	private static Integer initId = 1006;
	
	public void save(com.example.springboot.entities.Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
	}
	
	public Collection<com.example.springboot.entities.Employee> getAll(){
		return employees.values();
	}
	
	public com.example.springboot.entities.Employee get(Integer id){
		return employees.get(id);
	}
	
	public void delete(Integer id){
		employees.remove(id);
	}
}
