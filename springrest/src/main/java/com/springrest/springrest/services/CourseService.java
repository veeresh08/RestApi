package com.springrest.springrest.services;

import java.util.List;
import java.util.Optional;

import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.exception.CourseNotFoundException;

public interface CourseService {

	public List<Course> getCourses();
	
	public Optional<Course> getCourse(long courseId) throws CourseNotFoundException;
	
//	public Course updateCourse(Course course);
	
	public void deleteCourse(long parseLong);

	Course addCourse(CourseDto courseDto);

	Course updateCourse(CourseDto courseDto);
	
	
}
