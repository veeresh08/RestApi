package com.springrest.springrest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.springrest.springrest.dao.DepartmentDao;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.entity.Department;
import com.springrest.springrest.entity.Student;


@WebAppConfiguration
@SpringBootTest(classes = SpringrestApplication.class)
class DepartmentControllerTest {

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
	@Order(1)
	void getDepartment_whenHitTheWApi() throws Exception {
		//mock
		List<Department> departments = getDepartments();
		Mockito.when(departmentDao.findAll()).thenReturn(departments);
		
		//test
		MvcResult result = this.mockMvc.perform(get("/departments")).andReturn();
		System.out.println("------------->"+result.getResponse().getContentAsString());
		
		//assert
			assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);

	}
	
	@Test
	void getCourseByName_whenHitTheApi() throws Exception {

		List<Course> courses = getCourses();
		Department department = new Department(1L,"ece", "block-d","ece08",courses);
		doReturn(department).when(departmentDao).findByDepartmentNameIgnoreCase("ece");

	

		MvcResult result  = this.mockMvc.perform(get("/departments/name/ece")
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("get Department by Name------------->"+result);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);	
		
	}
	
	@Test
	@Order(3)
	void getaDepartmentByid_whenHitTheApi() throws Exception {
		
		List<Course> courses = getCourses();
		Department department = new Department(1L,"ece", "block-d","ece08",courses);
        doReturn(Optional.of(department)).when(departmentDao).findById(1l);
       

        // Execute the dao call
        	MvcResult result  = this.mockMvc.perform(get("/departments/1")
    				.contentType(MediaType.APPLICATION_JSON))
    				.andReturn();
    		System.out.println("get Department by name------------->"+result);
    		
    		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);	
    		
        // Assert the response
//        	Optional<Department> result = departmentDao.findById(1l);

//        assertTrue(result.isPresent());
//        assertSame(result.get(), department, "The course course was not the same as the mock");
	}
	
	@Test
	@Order(2)
	void postDepartment_whenHitTheApi() throws Exception {
		//mock
		List<Course> courses = getCourses();
		Department department = new Department(1L,"cse", "block-b","cse08",courses);
//		List<Course> course = getCourses();
		Mockito.when(departmentDao.save(Mockito.any())).thenReturn(department);
		
		
	
		
		MvcResult result = this.mockMvc.perform(post("/departments")
				.content(objectMapper.writeValueAsString(department))
				.contentType("application/json")) //.andExpect(status().isOk())
				.andReturn();
		

		System.out.println("------------->"+result.getResponse().getContentAsString());
		
		//assert
//		assertSame(courses, result);
//		assertEquals(courses,result);
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);
	}
	
	
	@Test
	@Order(4)
	public void updateDepartment_success() throws Exception{
		//mock
		List<Course> courses = getCourses();
		Department department = new Department(1L,"cse", "block-b","cse08",courses);
//		List<Course> course = getCourses();
		
		doReturn(Optional.of(department)).when(departmentDao).findById(1l);
		Mockito.when(departmentDao.save(Mockito.any())).thenReturn(department);

		MvcResult result = this.mockMvc.perform(put("/departments/1")
				.content(objectMapper.writeValueAsString(department))
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
	void deleteCourse() throws Exception {
		
		List<Course> courses = getCourses();
		Department department = new Department(1L,"ece", "block-d","ece08",courses);
//		  System.out.println("dept deleted"+ departmentDao.findById(1l));
	    doAnswer((arguments) -> {
//	        System.out.println("Inside doAnswer block"+department.getDepartmentName()+" : "+arguments.getArgument(0));
	        assertEquals(department.getDepartmentId(), arguments.getArgument(0));
	        return null;
	    }).when(departmentDao).deleteById(Mockito.any());
	    
		MvcResult result = this.mockMvc.perform(delete("/departments/1").contentType(MediaType.APPLICATION_JSON))//.andExpect(status().isOk())
				.andReturn();
				assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_OK);	

	    
	   //test
	    departmentDao.deleteById(1l);
	  
	}
	

	@Test
	@Order(6)
	void CourseNotFoundException() throws Exception {
//		List<Student> student = getStudent();
//		Course courses = new Course(1L,"title", "desc",1L,student);
		
		Mockito.when(departmentDao.findById((long) 1L)).thenReturn(Optional.empty());
		
		MvcResult result = this.mockMvc.perform(get("/departments/1")
						.contentType(MediaType.APPLICATION_JSON))//.andExpect(status().isOk())
						.andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(HttpServletResponse.SC_BAD_REQUEST);	

	}
	
	
	
}
