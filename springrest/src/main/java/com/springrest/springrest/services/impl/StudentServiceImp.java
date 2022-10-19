//package com.springrest.springrest.services.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.springrest.springrest.dao.CourseDao;
//import com.springrest.springrest.dao.StudentDao;
//import com.springrest.springrest.entity.Course;
//import com.springrest.springrest.entity.Student;
//import com.springrest.springrest.services.CourseService;
//import com.springrest.springrest.services.StudentService;
//
//public class StudentServiceImp implements StudentService{
//	
//	@Autowired
//	private CourseService courseService;
//	
//	@Autowired
//	private StudentDao studentDao;
//	
//	@Override
//	public Student getStudents(long studentId) {
//		
//		return studentDao.findAll();
//	}
//
//	@Override
//	public List<Student> getStudents() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
