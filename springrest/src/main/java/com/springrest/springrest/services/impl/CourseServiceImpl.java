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

//	List<Course> list;
	public CourseServiceImpl() {
	}

	public CourseServiceImpl(Course course) {
//		list = new ArrayList<>();
//		list.add(new Course(145,"Java Core Course", "this course containts basics of java"));
//		list.add(new Course(145,"Spring boot course", "Creating rest api using spring boot"));
		courseDao.save(course);
	}

	@Override
	public List<Course> getCourses() {

		return courseDao.findAll();
	}

	@Override
	public Course getCourse(long courseId) throws CourseNotFoundException {

		if (courseDao.findById(courseId).isPresent()) {
			Course course = courseDao.findById(courseId).get();
			return course;

		}else {
			throw new CourseNotFoundException("Course not found with id "+courseId);
		}
		
	}

	@Override
	public Course addCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setDescription(courseDto.getDescription());
		course.setTitle(courseDto.getTitle());
		return courseDao.save(course);

	}

	@Override
	public Course updateCourse(Course course) {
		courseDao.save(course);
		return course;
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

}
