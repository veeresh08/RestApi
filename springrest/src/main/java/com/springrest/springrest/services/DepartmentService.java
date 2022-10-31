package com.springrest.springrest.services;

import java.util.List;

import com.springrest.springrest.dto.DepartmentDto;
//import com.springrest.springrest.dto.DepartmentDto;
import com.springrest.springrest.entity.Department;
import com.springrest.springrest.exception.DepartmentNotFouncException;



public interface DepartmentService {
	
	public List<Department> getDepartments();
	
	public Department getDepartment(Long departmentId)throws DepartmentNotFouncException;
	
	public Department addDepartment(DepartmentDto departmentDto);
	
	public Department updateDepartment(Long departmentId, DepartmentDto departmentDto);
	
	public void deleteDepartment(Long departmentId);
	
	Department fetchDepartmentByName(String departmentName);
}
