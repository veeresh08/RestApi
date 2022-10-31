package com.springrest.springrest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.entity.Student;
import com.springrest.springrest.services.CourseService;



@WebAppConfiguration
@SpringBootTest(classes = SpringrestApplication.class)
class CourseControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private CourseDao courseDao;

	@Autowired
	protected CourseService courseService;

	@Autowired
	protected CourseController courseController;

	@Autowired
	protected ObjectMapper objectMapper;

	List<Course> courses = new ArrayList<>();

	@Autowired
	protected WebApplicationContext context;

	private ArrayList<Course> getCourses() {

		List<Student> students = getStudent();
		courses.add(new Course(1L,"title", "desc", 1L,students));
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
	@Order(1)
	void getCourse_whenHitTheWApi() throws Exception {
		//mock
		List<Course> courses = getCourses();
		Mockito.when(courseDao.findAll()).thenReturn(courses);

		//test
		MvcResult result = this.mockMvc.perform(get("/courses")).andReturn();
		System.out.println("------------->"+result.getResponse().getContentAsString());

		//assert
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);

	}

	@Test
	@Order(2)
	void getCourseByid_whenHitTheApi() throws Exception {

		List<Student> students = getStudent();
		Course course = new Course(1L,"title", "desc",1L,students);
		doReturn(Optional.of(course)).when(courseDao).findById(1l);

		MvcResult result  = this.mockMvc.perform(get("/courses/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("getcoursebyid------------->"+result);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);	

		// Assert the response
		//		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);

		//		assertTrue(result1.isPresent());
		//		assertSame(result1.get(), course, "The course course was not the same as the mock");
	}
	
	@Test
	void getCourseByName_whenHitTheApi() throws Exception {

		List<Student> students = getStudent();
		Course course = new Course(1L,"java", "desc",1L,students);
		doReturn(course).when(courseDao).findByTitleIgnoreCase("java");

		MvcResult result  = this.mockMvc.perform(get("/course/name/java")
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("getcoursebyName------------->"+result);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);	
	}

	@Test
	@Order(3)
	void postCourse_whenHitTheApi() throws Exception {
		//mock
		List<Student> student = getStudent();
		Course course = new Course(1L,"title", "desc",1L,student);
		//		List<Course> course = getCourses();
		Mockito.when(courseDao.save(Mockito.any())).thenReturn(course);

		MvcResult result = this.mockMvc.perform(post("/courses")
				.content(objectMapper.writeValueAsString(course))
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
	public void updateCourse_success() throws Exception{
		List<Student> students = getStudent();
		Course course = new Course(1L,"title", "desc",1L,students);

		Mockito.when(courseDao.save(Mockito.any())).thenReturn(course);

		MvcResult result = this.mockMvc.perform(put("/courses")
				.content(objectMapper.writeValueAsString(course))
				.contentType("application/json")) //.andExpect(status().isOk())
				.andReturn();

		System.out.println("------------->"+result.getResponse().getContentAsString());

		//assert
		//		assertSame(course, result);
		//		assertEquals(courses,result);
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);
	}

	@Test
	@Order(5)
	void deleteCourse() throws Exception {

		List<Student> student = getStudent();
		Course course = new Course(1L,"title", "desc",1L,student);


		doReturn(Optional.of(course)).when(courseDao).findById(1l);


		doAnswer((arguments) -> {
			System.out.println("Inside doAnswer block"+arguments.getArgument(0));
			assertEquals(course, arguments.getArgument(0));
			return null;
		}).when(courseDao).delete(Mockito.any());

		//test
		courseDao.delete(course);
		MvcResult result = this.mockMvc.perform(delete("/courses/1").contentType(MediaType.APPLICATION_JSON))//.andExpect(status().isOk())
				.andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);	


	}

	@Test
	@Order(6)
	void CourseNotFoundException() throws Exception {
		//		List<Student> students = getStudent();
		//		Course course = new Course(1L,"title", "desc",1L,students);
		//		doReturn(Optional.of(course)).when(courseDao).findById(1l);

		Mockito.when(courseDao.findById((long) 1L)).thenReturn(Optional.empty());

		MvcResult result = this.mockMvc.perform(get("/courses/1")
				.contentType(MediaType.APPLICATION_JSON))//.andExpect(status().isOk())
				.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_BAD_REQUEST);	
	}

	@Test
	@Order(6)
	void when_delete_CourseNotFoundException() throws Exception {
		//		List<Student> students = getStudent();
		//		Course course = new Course(1L,"title", "desc",1L,students);
		//		doReturn(Optional.of(course)).when(courseDao).findById(1l);

		List<Student> student = getStudent();
		Course course = new Course(1L,"title", "desc",1L,student);

		doReturn(Optional.of(course)).when(courseDao).findById(1l);
		doAnswer((arguments) -> {
			System.out.println("Inside doAnswer block"+arguments.getArgument(0));
			assertEquals(course, arguments.getArgument(0));
			return null;
		}).when(courseDao).delete(Mockito.any());

		Mockito.when(courseDao.findById((long) 1L)).thenReturn(Optional.empty());

		MvcResult result = this.mockMvc.perform(delete("/courses/1")
				.contentType(MediaType.APPLICATION_JSON))//.andExpect(status().isOk())
				.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_BAD_REQUEST);	
	}

	@Test
	void set_deta_demonstartionOfTransactional() throws Exception {
		List<Student> student = getStudent();
		Course courses = new Course(1L,"title", "desc",1L, student);

		Mockito.when(courseDao.save(Mockito.any())).thenReturn(courses);

		MvcResult result = this.mockMvc.perform(post("/setCourse")
				.content(objectMapper.writeValueAsString(courses))
				.contentType("application/json")) //.andExpect(status().isOk())
				.andReturn();

		System.out.println("------------->"+result.getResponse().getContentAsString());

		//assert
		//		assertSame(course, result);
		//		assertEquals(courses,result);
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);
	}


	@Test
	void when_delete_throwNumberFormatException() throws Exception {

		Course course = new Course();
		course.setTitle("java");
		course.setDescription("kuch bhe");
		course.setCredits((long)0);

		Mockito.when(courseService.saveCourse(course)).thenReturn(null);

		NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
			courseController.throwExceptionifExist(course);
		});

		String expectedMessage = "For input string";
		String actualMessage = exception.getMessage();

		System.out.println(actualMessage);
		assertEquals(NumberFormatException.class, exception.getClass());


	}	


}

