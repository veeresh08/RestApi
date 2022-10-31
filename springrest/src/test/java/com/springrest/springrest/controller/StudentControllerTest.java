package com.springrest.springrest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.springrest.SpringrestApplication;
import com.springrest.springrest.dao.StudentDao;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.entity.Student;
import com.springrest.springrest.services.StudentService;

@WebAppConfiguration
@SpringBootTest(classes = SpringrestApplication.class)
class StudentControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private StudentDao studentDao;

	@Autowired
	protected ObjectMapper objectMapper;

	//	private List<String> course = new ArrayList<>();


	private List<Student> students  = new ArrayList<>();

	@Autowired
	protected WebApplicationContext context;

	@BeforeEach
	public void data() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}



	@Test
	@Order(1)
	void getStudent_whenHitTheWApi() throws Exception {
		//mock
		List<Student> student = getStudent();
		Mockito.when(studentDao.findAll()).thenReturn(student);

		//test
		MvcResult result = this.mockMvc.perform(get("/students")).andReturn();
		System.out.println("------------->"+result.getResponse().getContentAsString());

		//assert
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);

	}

	private ArrayList<Student> getStudent() {

		List<Course> course = getCourse();
		students.add(new Student(1L, "veeresh", "naik", "veeresh@gmail.com", course));
		return (ArrayList<Student>) students;
	}

	private ArrayList<Course> getCourse(){
		List<Course> course = new ArrayList<>();
		course.add(new Course(1L,"title", "desc", 1L,students));
		return (ArrayList<Course>) course;	
	}




	@Test
	@Order(2)
	void postStudent_whenHitTheApi() throws Exception {
		//mock
		List<Course> course = getCourse();
		Student students = new Student(1L, "veeresh", "naik", "veeresh@gmail.com", course);
		Mockito.when(studentDao.save(Mockito.any())).thenReturn(students);

		//test
		MvcResult result = this.mockMvc.perform(post("/students")
				.content(objectMapper.writeValueAsString(students))
				.contentType("application/json")) //.andExpect(status().isOk())
				.andReturn();

		System.out.println("------------->"+result.getResponse().getContentAsString());

		//assert
		//		assertSame(course, result);
		//		assertEquals(students,result);
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);


	}


	@Test
	@Order(3)
	void getStudentByid_whenHitTheApi() throws Exception {

		List<Course> course = getCourse();
		Student students = new Student(1L, "veeresh", "naik", "veeresh@gmail.com", course);
		doReturn(Optional.of(students)).when(studentDao).findById(1l);
		

		MvcResult result  = this.mockMvc.perform(get("/students/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("getcoursebyid------------->"+result);
		
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);	
		
		// Execute the dao call
//		Optional<Student> result = studentDao.findById(1L);
//		System.out.println("get student by id------------->"+result);

		
//		assertTrue(result.isPresent());
	}

	@Test
	@Order(4)
	public void updateStudent_success() throws Exception{
		List<Course> course = getCourse();
		Student students = new Student(1L, "veeresh", "naik", "veeresh@gmail.com", course);

		Mockito.when(studentDao.save(Mockito.any())).thenReturn(students);

		MvcResult result = this.mockMvc.perform(put("/students")
				.content(objectMapper.writeValueAsString(students))
				.contentType("application/json")) //.andExpect(status().isOk())
				.andReturn();

		System.out.println("------------->"+result.getResponse().getContentAsString());

		//assert
		//		assertSame(course, result);
		//		assertEquals(courses,result);
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);
	}

	@Test
	@Order(4)
	void deleteStudent() throws Exception {
		//mock
		List<Course> course = getCourse();
		Student students = new Student(1L, "veeresh", "naik", "veeresh@gmail.com", course);

		//result
		doAnswer((arguments) -> {
			assertEquals(students, arguments.getArgument(0));
			return null;
		}).when(studentDao).delete(Mockito.any());

		//test

		MvcResult result = this.mockMvc.perform(delete("/students/1").contentType(MediaType.APPLICATION_JSON))//.andExpect(status().isOk())
		.andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);	

		System.out.println("45-----------"+result);
//		studentDao.delete(students);
//		this.studentService.deleteStudent(1L);
	}


	

	@Test
	@Order(6)
	void StudentNotFoundException() throws Exception {
//		List<Student> student = getStudent();
//		Course courses = new Course(1L,"title", "desc",1L,student);

		Mockito.when(studentDao.findById((long) 1L)).thenReturn(Optional.empty());

		MvcResult result = this.mockMvc.perform(get("/students/1")
				.contentType(MediaType.APPLICATION_JSON))//.andExpect(status().isOk())
				.andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_NOT_FOUND);	
		
	}


}
