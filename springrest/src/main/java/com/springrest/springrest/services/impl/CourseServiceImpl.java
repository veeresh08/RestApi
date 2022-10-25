package com.springrest.springrest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.exception.CourseNotFoundException;
import com.springrest.springrest.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	

	private Course course;

	public CourseServiceImpl() {
	}

	public CourseServiceImpl(Course course) {

		courseDao.save(course);
	}

	@Override
	public List<Course> getCourses() {

		return courseDao.findAll();
	}

//	@Override
//	public Course getCourse(long courseId) throws CourseNotFoundException {
//		
//		 Optional <Course> optional = CourseService.findById(courseId);
//		
//		
//		if (courseDao.findById(courseId).isPresent()) {
//			Course course = courseDao.findById(courseId).get();
//			return course;
//
//		}else {
//			throw new CourseNotFoundException("Course not found with id "+courseId);
//		}
//		
//	}
	
	@Override
	public Course getCourse(long courseId) throws CourseNotFoundException {
//        return courseDao.findById(courseId);
		  	  
		Optional <Course> optional = courseDao.findById(courseId);
		
		if (optional.isPresent()) {
//			return courseDao.findById(courseId);
			return optional.get();
			} else {
        	throw new CourseNotFoundException("Course not found with id "+courseId);
        }
      
    }

	@Override
	public Course addCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setTitle(courseDto.getTitle());
		course.setDescription(courseDto.getDescription());
		course.setCredits(courseDto.getCredits());
		return courseDao.save(course);

	}
	
	public void saveCourse(Course course) {
		courseDao.save(course);
	}
	@Override
	public Course updateCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setDescription(courseDto.getDescription());
		course.setTitle(courseDto.getTitle());;
		return courseDao.save(course);
	}

	public void createCourse(Course course) {

	}

	@Override
	public void deleteCourse(long parseLong) {
		Optional<Course> entity = courseDao.findById(parseLong);
		if (entity.isPresent()) {
			courseDao.delete(entity.get());
		}

	}

	@Override
	public Course fetchCourseByTitle(String title) {
		return courseDao.findByTitleIgnoreCase(title);
	}

}
