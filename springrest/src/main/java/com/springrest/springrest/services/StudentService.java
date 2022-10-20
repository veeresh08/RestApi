package com.springrest.springrest.services;

import java.util.List;
import java.util.Optional;

import com.springrest.springrest.dto.StudentDto;
import com.springrest.springrest.entity.Student;
import com.springrest.springrest.exception.CourseNotFoundException;

public interface StudentService {
	
	public List<Student> getStudents();
	
	public Optional<Student> getStudent(long studentId) throws CourseNotFoundException;
	
	public Student addStudent(StudentDto student);
	
	Student updateStudent(StudentDto studentDto);
	
	public void deleteStudent(long parseLong);
	
}
