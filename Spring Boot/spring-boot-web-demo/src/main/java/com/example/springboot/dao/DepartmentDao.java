package com.example.springboot.dao;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository
public class DepartmentDao {

	private static Map<Integer, com.example.springboot.entities.Department> departments = null;
	
	static{
		departments = new HashMap<Integer, com.example.springboot.entities.Department>();
		
		departments.put(101, new com.example.springboot.entities.Department(101, "D-AA"));
		departments.put(102, new com.example.springboot.entities.Department(102, "D-BB"));
		departments.put(103, new com.example.springboot.entities.Department(103, "D-CC"));
		departments.put(104, new com.example.springboot.entities.Department(104, "D-DD"));
		departments.put(105, new com.example.springboot.entities.Department(105, "D-EE"));
	}
	
	public Collection<com.example.springboot.entities.Department> getDepartments(){
		return departments.values();
	}
	
	public com.example.springboot.entities.Department getDepartment(Integer id){
		return departments.get(id);
	}
	
}
