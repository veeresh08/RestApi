package com.springrest.springrest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.exception.CustomException;
import com.springrest.springrest.exception.ErrorEnum;
import com.springrest.springrest.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	public CourseServiceImpl() {
	}



	@Override
	public List<Course> getCourses() {

		return courseDao.findAll();
	}


	@Override
	public Course getCourse(long courseId) throws CustomException {

		Optional<Course> optional = courseDao.findById(courseId);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomException(ErrorEnum.COURSE_NOT_FOUND, HttpStatus.BAD_REQUEST);
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

	public Course saveCourse(Course course) {
		return courseDao.save(course);
	}
	@Override
	public Course updateCourse(CourseDto courseDto)  {
		Course course = new Course();
		course.setDescription(courseDto.getDescription());
		course.setTitle(courseDto.getTitle());;
		return courseDao.save(course);
	}

	public void createCourse(Course course) {

	}

	@Override
	public void deleteCourse(long parseLong) throws CustomException {
		Optional<Course> entity = courseDao.findById(parseLong);
		if (entity.isPresent()) {
			courseDao.delete(entity.get());
		}else {
			throw new CustomException(ErrorEnum.COURSE_NOT_FOUND, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public Course fetchCourseByTitle(String title) {
		
		return courseDao.findByTitleIgnoreCase(title);
	}



	public Course getCourseByName(String title) {
		return courseDao.findByTitleIgnoreCase(title);
	}
//	public Course getCourseByName(String title) throws CustomException{
//		
//		Optional<Course> optional = Optional.of(courseDao.findByTitleIgnoreCase(title));
//
//		if (optional.isPresent()) {	
//			return optional.get();
//		} else {
//			throw new CustomException(ErrorEnum.COURSE_NOT_FOUND, HttpStatus.NOT_FOUND);
//		}
//	}

}
