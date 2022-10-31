package com.springrest.springrest.services;

import java.util.List;

import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.entity.Course;
//import com.springrest.springrest.entity.Department;
import com.springrest.springrest.exception.CourseNotFoundException;
import com.springrest.springrest.exception.CustomException;

public interface CourseService {

	public List<Course> getCourses();
	
	public Course getCourse(long courseId) throws CustomException;
	
	public void deleteCourse(long parseLong) throws CustomException;

	public Course saveCourse(Course course);
	
	Course addCourse(CourseDto courseDto);

	Course updateCourse(CourseDto courseDto) throws CourseNotFoundException;
	
	Course fetchCourseByTitle(String title);

		
}
