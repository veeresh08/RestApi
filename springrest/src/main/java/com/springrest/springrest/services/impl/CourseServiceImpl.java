package com.springrest.springrest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entity.Course;
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
	public Course getCourse(long courseId) {
		// TODO Auto-generated method stub
//		Course c = null;
//		for(Course course:list) {
//			if(course.getId()==courseId) {
//				c = course;
//				break;
//			}
//		}
		if(courseDao.findById(courseId).isPresent())
		 return courseDao.findById(courseId).get();
		return null;
	}

	@Override
	public Course addCourse(Course course) {
//		list.add(course);
		return courseDao.save(course);
	
	}

	@Override
	public Course updateCourse(Course course) {

//		list.forEach(e->{
//			if(e.getId()==course.getId()) {
//				e.setTitle(course.getTitle());
//				e.setDescription(course.getDescription());
//			}
//		});

		courseDao.save(course);
		return course;
	}

	public void createCourse(Course course) {

	}

	@Override
	public void deleteCourse(long parseLong) {
//		list=this.list.stream().filter(e->e.getId()!=parseLong).collect(Collectors.toList());
		Optional<Course> entity = courseDao.findById(parseLong);
		if(entity.isPresent()) {
			courseDao.delete(entity.get());
		}

	}

}
