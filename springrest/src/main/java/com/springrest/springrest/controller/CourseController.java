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

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.entity.Course;
//import com.springrest.springrest.entity.Department;
import com.springrest.springrest.exception.CourseNotFoundException;
//import com.springrest.springrest.services.CourseService;
import com.springrest.springrest.services.impl.CourseServiceImpl;

@RestController
public class CourseController {
	
	@Autowired
	private CourseServiceImpl courseService;
	

//	private Course course;
	
	@Autowired
	private CourseDao courseDao;
	
	@GetMapping("/home")
	public String home() {
		return "Welcome to courses application";
	}
	
	//get the courses
	@GetMapping("/courses")
	public List<Course> getCourses(){
		return this.courseService.getCourses();
	}
	
	@GetMapping("/courses/{courseId}")
	public Course getCourse(@PathVariable String courseId) throws CourseNotFoundException {
		return this.courseService.getCourse(Long.parseLong(courseId));
	}
	
	//course add
	@PostMapping("/courses")
	public Course addCourse(@RequestBody @Valid CourseDto course) {
		return this.courseService.addCourse(course);
	}
	
	@Transactional(rollbackOn=NumberFormatException.class)
	@PostMapping("/setCourse")
	public String setData(@RequestBody @Valid CourseDto courseDto) {
//		return this.courseService.addCourse(course);
		
		Course course = new Course();
		course.setTitle(courseDto.getTitle());
		course.setDescription(courseDto.getDescription());
		course.setCredits(courseDto.getCredits());
		
		courseService.saveCourse(course);
		
		if(courseDto.getCredits()==0) {
			throw new NumberFormatException();
		}
		return "Data Inserted sucessfully";
	}
	
	
	
	//update course using PUT Request
	@PutMapping("/courses")
	public Course updateCourse(@RequestBody CourseDto course) {
		return this.courseService.updateCourse(course);
	}
	
	
	//delete the course
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId){
		try {
			this.courseService.deleteCourse(Long.parseLong(courseId));
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/course/name/{name}")
	 public Course fetchCourseByName(@PathVariable("name") String title) {
	        return courseDao.findByTitleIgnoreCase(title);
	 }
	
	
}
