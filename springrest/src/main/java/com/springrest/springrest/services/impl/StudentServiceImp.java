package com.springrest.springrest.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.StudentDao;
import com.springrest.springrest.dto.StudentDto;
import com.springrest.springrest.entity.Student;
import com.springrest.springrest.exception.ErrorEnum;
import com.springrest.springrest.exception.StudentNotFoundException;
import com.springrest.springrest.services.StudentService;

@Service
public class StudentServiceImp implements StudentService{



	@Autowired
	private StudentDao studentDao;

	//	@Override
	//	public Student getStudents(long studentId) {
	//		
	//		return studentDao.findAll();
	//	}

	@Override
	public List<Student> getStudents() {
		return studentDao.findAll();
	}

	@Override
	public Student addStudent(StudentDto studentDto) {
		Student student = new Student();
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setEmailId(studentDto.getEmailId());
		return studentDao.save(student);
	}

	@Override
	public Student getStudent(long studentId) throws StudentNotFoundException {
	
		Optional <Student> optional = studentDao.findById(studentId);

		if (optional.isPresent()) {
			return  optional.get();
//			return Optional.empty();
		} else {
			throw new StudentNotFoundException(ErrorEnum.STUDENT_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Student updateStudent(StudentDto studentDto) {
		Student student = new Student();
		
		 if(Objects.nonNull(studentDto.getFirstName()) &&
			        !"".equalsIgnoreCase(studentDto.getFirstName())) {
			 student.setFirstName(studentDto.getFirstName());
			 
		 }
		 if(Objects.nonNull(studentDto.getLastName()) &&
			        !"".equalsIgnoreCase(studentDto.getLastName())) {
			 student.setLastName(studentDto.getLastName());
			 
		 }
		 
		 if(Objects.nonNull(studentDto.getEmailId()) &&
			        !"".equalsIgnoreCase(studentDto.getEmailId())) {
			 student.setEmailId(studentDto.getEmailId());
			 
		 }
		 return studentDao.save(student);
//		student.setFirstName(studentDto.getFirstName());
//		student.setLastName(studentDto.getLastName());
//		student.setEmailId(studentDto.getEmailId());
	}

	@Override
	public void deleteStudent(long parseLong) {
		Optional<Student> entity = studentDao.findById(parseLong);
		if (entity.isPresent()) {
			studentDao.delete(entity.get());
		}
	}

}
