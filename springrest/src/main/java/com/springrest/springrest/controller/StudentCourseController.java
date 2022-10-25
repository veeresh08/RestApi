package com.springrest.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dao.StudentDao;
import com.springrest.springrest.entity.Student;

@RestController
@RequestMapping("students/courses")
public class StudentCourseController {
	@Autowired
    private StudentDao studentRepository;
	
	@Autowired
    private CourseDao courseRepository;

	
    public StudentCourseController(StudentDao studentRepository,
    		CourseDao courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    
    @PostMapping
    public Student saveStudentWithCourse(@RequestBody Student student){
      return studentRepository.save(student);
    }
}