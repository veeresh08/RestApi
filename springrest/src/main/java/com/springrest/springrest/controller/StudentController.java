package com.springrest.springrest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dao.StudentDao;
import com.springrest.springrest.dto.StudentDto;
import com.springrest.springrest.entity.Student;
import com.springrest.springrest.exception.CourseNotFoundException;
import com.springrest.springrest.services.StudentService;


@RestController
public class StudentController {
	

		
	    
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/shome")
	public String Studenthome() {
		return "Welcome to courses-student application";
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return this.studentService.getStudents();
	}
	
	@GetMapping("/students/{studentId}")
	public Optional<Student> getCourse(@PathVariable String courseId) throws CourseNotFoundException {
		return this.studentService.getStudent(Long.parseLong(courseId));
	}
	
	@PostMapping("/students")
	public Student addStudent(@RequestBody StudentDto student) {
		return this.studentService.addStudent(student);
	}
	
	@PutMapping("/students")
	public Student updateStudent(@RequestBody StudentDto student) {
		return this.studentService.updateStudent(student);
	}
	
	@DeleteMapping("/students/{studentId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String studentId){
		try {
			this.studentService.deleteStudent(Long.parseLong(studentId));
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
