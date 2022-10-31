package com.springrest.springrest.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

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

import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.dto.ErrorResponseDTO;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.exception.CustomException;
//import com.springrest.springrest.services.CourseService;
import com.springrest.springrest.services.impl.CourseServiceImpl;

@RestController
public class CourseController {

	@Autowired
	private CourseServiceImpl courseService;

	//	@GetMapping("/home")
	//	public String home() {
	//		return "Welcome to courses application";
	//	}

	// Get All courses when hit API
	@GetMapping("/courses")
	public List<Course> getCourses(){
		return this.courseService.getCourses();
	}

	//Get course By Id
	@GetMapping("/courses/{courseId}")
	public ResponseEntity<Object> getCourse(@PathVariable String courseId) {
		try {
			this.courseService.getCourse(Long.parseLong(courseId));
			return new ResponseEntity<>(HttpStatus.OK);

		}catch(CustomException e) {
			ErrorResponseDTO errorResponseDto = new ErrorResponseDTO(e.getHttpStatus().toString(),e.getHttpStatus().toString(), e.getError().toString());
			System.out.println(e.getHttpStatus());
			return new ResponseEntity<>(errorResponseDto,e.getHttpStatus());
		}

	}


	//Adding Course using post request
	@PostMapping("/courses")
	public Course addCourse(@RequestBody @Valid CourseDto course) {
		return this.courseService.addCourse(course);
	}


	//Update course using PUT Request
	@PutMapping("/courses")
	public Course updateCourse(@RequestBody CourseDto course) {
		return this.courseService.updateCourse(course);
	}


	//Delete the course using HTTP delete request by id
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<Object> deleteCourse(@PathVariable String courseId){
		try {
			this.courseService.deleteCourse(Long.parseLong(courseId));
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(CustomException e) {
			ErrorResponseDTO errorResponseDto = new ErrorResponseDTO(e.getHttpStatus().toString(),e.getHttpStatus().toString(), e.getError().toString());
			System.out.println(e.getHttpStatus());
			return new ResponseEntity<>(errorResponseDto,e.getHttpStatus());
		}
	}

	//	Getting Course By Name
	@GetMapping("/course/name/{name}")
	public Course fetchCourseByName(@PathVariable("name") String name) {
		return this.courseService.getCourseByName(name);
	}


	// demonstration of @Transactional annotation to RollBack and commit

	@Transactional(rollbackOn=NumberFormatException.class)
	@PostMapping("/setCourse")
	public Course setData(@RequestBody @Valid CourseDto courseDto) throws NumberFormatException{

		Course course = new Course();
		course.setTitle(courseDto.getTitle());
		course.setDescription(courseDto.getDescription());
		course.setCredits(courseDto.getCredits());

		Course course1 =courseService.saveCourse(course);
		System.out.println("Saved course"+course1);
		
		throwExceptionifExist(course);

		System.out.println("after number format exception");
		return course1;


	}

	public void throwExceptionifExist(Course course) throws NumberFormatException {
		if(course.getCredits()==0) {
			System.out.println("inside number format exception");
			throw new NumberFormatException();
		}
		
	}


}
