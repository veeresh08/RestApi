package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.dto.DepartmentDto;
import com.springrest.springrest.dto.ErrorResponseDTO;
//import com.springrest.springrest.dto.DepartmentDto;
import com.springrest.springrest.entity.Department;
import com.springrest.springrest.exception.DepartmentNotFouncException;
import com.springrest.springrest.services.DepartmentService;



@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	//Gets department when hit api
	@GetMapping("/departments")
	public List<Department> getDepartments(){
		return departmentService.getDepartments();
	}

	//Posting departments using Http post request
	@PostMapping("/departments")
	public Department addDepartment(@RequestBody DepartmentDto department) {

		return departmentService.addDepartment(department);
	}

	//Get department by id 
	@GetMapping("/departments/{id}")
	public ResponseEntity<Object>  getDepartment(@PathVariable("id")Long departmentId) throws DepartmentNotFouncException {
		try{
			this.departmentService.getDepartment(departmentId);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(DepartmentNotFouncException e) {
			ErrorResponseDTO errorResponseDto = new ErrorResponseDTO(e.getHttpStatus().toString(),e.getHttpStatus().toString(), e.getError().toString());
			System.out.println(e.getHttpStatus());
			return new ResponseEntity<>(errorResponseDto,e.getHttpStatus());
		}

	}


	//Update department  by id
	@PutMapping("/departments/{id}")
	public Department updateDepartment(@PathVariable("id") Long departmentId,
			@RequestBody DepartmentDto department) {
		return departmentService.updateDepartment(departmentId,department);
	}

	//Deleting courses by department Id
	@DeleteMapping("/departments/{id}")
	public String deleteDepartment(@PathVariable("id") Long departmentId) {
		departmentService.deleteDepartment(departmentId);
		return "Department deleted Successfully!!";
	}

	// get Department by department name
	@GetMapping("/departments/name/{name}")
	public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
		return departmentService.fetchDepartmentByName(departmentName);
	}
}
