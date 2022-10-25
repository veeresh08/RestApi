package com.springrest.springrest.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.dto.DepartmentDto;
//import com.springrest.springrest.dto.DepartmentDto;
import com.springrest.springrest.entity.Department;
import com.springrest.springrest.services.DepartmentService;



@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	


	@GetMapping("/departments")
	public List<Department> getDepartments(){
		return departmentService.getDepartments();
	}
	
	@GetMapping("/departments/{id}")
	public Department getDepartment(@PathVariable("id")Long departmentId) {//throws DepartmentNotFouncException {
		return departmentService.getDepartment(departmentId);
	}
	
	
	@PostMapping("/departments")
	public Department addDepartment(@RequestBody DepartmentDto department) {
		
		return departmentService.addDepartment(department);
	}
	
	@PutMapping("/departments/{id}")
	public Department updateDepartment(@PathVariable("id") Long departmentId,
	                                       @RequestBody DepartmentDto department) {
	     return departmentService.updateDepartment(departmentId,department);
	}
	
	@DeleteMapping("/departments/{id}")
	public String deleteDepartment(@PathVariable("id") Long departmentId) {
		departmentService.deleteDepartment(departmentId);
	    return "Department deleted Successfully!!";
	}
		
	@GetMapping("/departments/name/{name}")
	 public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
	        return departmentService.fetchDepartmentByName(departmentName);
	 }
}
