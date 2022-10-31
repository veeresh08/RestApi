package com.springrest.springrest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.springrest.SpringrestApplication;
import com.springrest.springrest.dao.DepartmentDao;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.entity.Department;
import com.springrest.springrest.entity.Student;


@WebAppConfiguration
@SpringBootTest(classes = SpringrestApplication.class)
class DepartmentCourseControllerTest {
	

	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentDao departmentDao;

	@Autowired
	protected ObjectMapper objectMapper;
	
	List<Department> departments = new ArrayList<>();
	List<Course> courses = new ArrayList<>();
	
	@Autowired
	protected WebApplicationContext context;
	
	
	@BeforeEach
	public void data() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	
	private ArrayList<Department> getDepartments() {	
		List<Course> courses = getCourses();
		departments.add(new Department(1L,"cse", "block-b","cse08",courses));
		return (ArrayList<Department>) departments;
	}
	
	private ArrayList<Course> getCourses() {	
		List<Student> student = getStudent();
		courses.add(new Course(1L,"title", "desc", 1L,student));
		return (ArrayList<Course>) courses;
	}
	
	private ArrayList<Student> getStudent(){
		List<Student> students = new ArrayList<>();
		students.add(new Student(1L, "veeresh", "naik", "veeresh@gmail.com", courses));
		return (ArrayList<Student>) students;	
	}

	
	
	
	@Test
	void postDepartment_whenHitTheApi() throws Exception {
		//mock
		List<Course> courses = getCourses();
		Department department = new Department(1L,"cse", "block-b","cse08",courses);
//		List<Course> course = getCourses();
		Mockito.when(departmentDao.save(Mockito.any())).thenReturn(department);
					
		
		MvcResult result = this.mockMvc.perform(post("/department/courses")
				.content(objectMapper.writeValueAsString(department))
				.contentType("application/json")) //.andExpect(status().isOk())
				.andReturn();
		
			
		
		System.out.println("------------->"+result.getResponse().getContentAsString());
		               
		//assert
//		assertSame(courses, result);
//		assertEquals(result,department);
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);
	}

}
