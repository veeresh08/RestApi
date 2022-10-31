package com.springrest.springrest.services;

import java.util.List;

import com.springrest.springrest.dto.StudentDto;
import com.springrest.springrest.entity.Student;
import com.springrest.springrest.exception.StudentNotFoundException;

public interface StudentService {
	
	public List<Student> getStudents();
	
	public Student getStudent(long studentId) throws StudentNotFoundException;
	
	public Student addStudent(StudentDto student);
	
	Student updateStudent(StudentDto studentDto);
	
	public void deleteStudent(long parseLong);
	
}
