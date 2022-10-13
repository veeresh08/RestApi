package com.springrest.springrest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.springrest.SpringrestApplication;
import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.services.CourseService;

@WebAppConfiguration
@SpringBootTest(classes = SpringrestApplication.class)
class MyControllerTest {

	private MockMvc mockMvc;
	
	@MockBean
	private CourseDao courseDao;

	@Autowired
	protected ObjectMapper objectMapper;
	
	private List<String> course = new ArrayList<>();

	@Autowired
	protected WebApplicationContext context;
	
	@Test
	@Order(1)
	void getCourse_whenHitTheWApi() throws Exception {
		//mock
		List<Course> course = getCourses();
		Mockito.when(courseDao.findAll()).thenReturn(course);
		
		//test
		MvcResult result = this.mockMvc.perform(get("/courses")).andReturn();
		System.out.println("------------->"+result.getResponse().getContentAsString());
		
		//assert
			assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);

	}

	private ArrayList<Course> getCourses() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course(1,"title", "desc"));
		return (ArrayList<Course>) courses;
	}
	
	
	@BeforeEach
	public void data() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	@Order(2)
	void postCourse_whenHitTheApi() throws Exception {
		//mock
		
		Course courses = new Course(1,"title", "desc");
//		List<Course> course = getCourses();
		Mockito.when(courseDao.save(Mockito.any())).thenReturn(courses);

		
		MvcResult result = this.mockMvc.perform(post("/courses")
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
	@Order(3)
	void getCourseByid_whenHitTheApi() throws Exception {

		Course courses = new Course(1,"title", "desc");
        doReturn(Optional.of(courses)).when(courseDao).findById(1l);

        // Execute the dao call
        Optional<Course> result = courseDao.findById(1L);
        System.out.println("getcoursebyid------------->"+result);

        // Assert the response
        //assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);

        assertTrue(result.isPresent());
     //   assertSame(result.get(), courses, "The course course was not the same as the mock");
	}
	
	
	@Test
	@Order(4)
	void deleteCourse() throws Exception {
		
		Course courses = new Course(1,"title", "desc");
		
	    doAnswer((arguments) -> {
	        System.out.println("Inside doAnswer block");
	        assertEquals(courses, arguments.getArgument(0));
	        return null;
	    }).when(courseDao).delete(Mockito.any());
	    
	    
	   //test
	    courseDao.delete(courses);
	    
//		MvcResult result = this.mockMvc.perform(delete("/courses/1")).andReturn();
//		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);


	    
	    
	    
//		ResponseEntity<?> r=new ResponseEntity<>(HttpStatus.OK);
//		Mockito.doNothing().when(courseDao).delete(Mockito.any());
//		
//		   MvcResult mvcResult = this.mockMvc.perform(delete("/courses/2")).andReturn();
//		   int status = mvcResult.getResponse().getStatus();
//		   String result = mvcResult.getResponse().getContentAsString();
//		   assertEquals(status, r);
//		
//		//assert
//		 ResponseEntity<?> r=new ResponseEntity<>(HttpStatus.OK);
//		 //doReturn(r).when(courseDao.delete(courses));
//		Mockito.when(courseDao.delete(Mockito.any())).thenReturn(r);
//		courseDao.delete(entity);
//		Mockito.doNothing().when(courseDao.delete(courses));

	}

}
