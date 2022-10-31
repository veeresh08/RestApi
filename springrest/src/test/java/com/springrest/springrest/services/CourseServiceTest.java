package com.springrest.springrest.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.springrest.springrest.dao.CourseDao;


class CourseServiceTest {

	  	@MockBean
	    private CourseDao courseDao;


	    private CourseService courseService;

	    @BeforeEach
	    void setUp() {
//	        this.courseService = new CourseService();
	    }

	    @Test
	    void getAllCourse() {
	    	
	    	
//	    	courseService.getCourses();
//	        verify(courseDao).findAll();
	    }

}
