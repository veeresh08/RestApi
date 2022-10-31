package com.springrest.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dao.DepartmentDao;
import com.springrest.springrest.entity.Department;

@RestController
@RequestMapping("department/courses")
public class DepartmentCourseController {

	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private CourseDao courseDao;
	
	public DepartmentCourseController(DepartmentDao departmentDao,CourseDao courseDao) {
		this.departmentDao = departmentDao;
		this.courseDao = courseDao;
	}
	//Posting department with course
	@PostMapping
	public Department saveDepartmentWithCourse(@RequestBody Department department){
		return departmentDao.save(department);
	}
}
