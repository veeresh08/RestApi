package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.springrest.entity.Department;



public interface DepartmentDao extends JpaRepository<Department,Long>{
	
	public Department findByDepartmentName(String departmentName);
	
    public Department findByDepartmentNameIgnoreCase(String departmentName);
    
}
