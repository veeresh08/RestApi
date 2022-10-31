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

import com.springrest.springrest.dto.ErrorResponseDTO;
import com.springrest.springrest.dto.StudentDto;
import com.springrest.springrest.entity.Student;
import com.springrest.springrest.exception.StudentNotFoundException;
import com.springrest.springrest.services.StudentService;


@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

//	@GetMapping("/shome")
//	public String Studenthome() {
//		return "Welcome to courses-student application";
//	}

	//	get list of  Students when hit the api
	@GetMapping("/students")
	public List<Student> getStudents(){
		return this.studentService.getStudents();
	}

	//	Get student by id
	@GetMapping("/students/{studentId}")
	public ResponseEntity<Object> getStudent(@PathVariable String studentId){
		try{
			this.studentService.getStudent(Long.parseLong(studentId));
			return new ResponseEntity<>(HttpStatus.OK);
			
		}catch(StudentNotFoundException e) {
			ErrorResponseDTO errorResponseDto = new ErrorResponseDTO(e.getHttpStatus().toString(),e.getHttpStatus().toString(), e.getError().toString());
			System.out.println("this is statu"+e.getHttpStatus());
			return new ResponseEntity<>(errorResponseDto,e.getHttpStatus());
		}
	}

	//	Add student by post mapping request
	@PostMapping("/students")
	public Student addStudent(@RequestBody StudentDto student) {
		return this.studentService.addStudent(student);
	}

	//	Add student by HTTP put Request
	@PutMapping("/students")
	public Student updateStudent(@RequestBody StudentDto student) {
		return this.studentService.updateStudent(student);
	}

	//	Delete student with id
	@DeleteMapping("/students/{studentId}")
	public void deleteCourse(@PathVariable String studentId){
//		try {
			 this.studentService.deleteStudent(Long.parseLong(studentId));
//			return new ResponseEntity<>(HttpStatus.OK);
//		}catch(Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}


}
