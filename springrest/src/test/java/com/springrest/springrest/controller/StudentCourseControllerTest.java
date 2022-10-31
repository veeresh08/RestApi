package com.springrest.springrest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dao.StudentDao;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.entity.Student;


@WebAppConfiguration
@SpringBootTest(classes = SpringrestApplication.class)
class StudentCourseControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private CourseDao courseDao;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	private StudentDao studentRepository;

	//	private List<String> course = new ArrayList<>();
	List<Course> courses = new ArrayList<>();

	//	private List<Student> student  = new ArrayList<>();

	@Autowired
	protected WebApplicationContext context;



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


	@BeforeEach
	public void data() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}


	@Test
	@Order(2)

	void postCourse_whenHitTheApi() throws Exception {
		//mock
		List<Course> courses = getCourses();
		Student student = new Student(1L,"veeresh", "naik","veeresh.nike@gmail.com",courses);
		//		List<Course> course = getCourses();
		StudentDao studentRepository = mock(StudentDao.class, Mockito.RETURNS_DEEP_STUBS); // to remove NPE

		Mockito.when(studentRepository.save(Mockito.any())).thenReturn(student);


		MvcResult result = this.mockMvc.perform(post("/students/courses")
				.content(objectMapper.writeValueAsString(student))
				.contentType("application/json")) //.andExpect(status().isOk())
				.andReturn();

		System.out.println("------------->"+result.getResponse().getContentAsString());


		//assert
		//		assertSame(course, result);
		//				assertEquals(courses,result);
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);
	}


}




