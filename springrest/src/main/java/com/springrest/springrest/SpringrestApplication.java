package com.springrest.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//(scanBasePackages={"com.springrest.springrest.entity.Course","com.springrest.springrest.services"})
//@ComponentScan({"com.springrest.springrest.configuration","com.springrest.springrest.service","com.springrest.springrest.controller"})
//@EntityScan("com.springrest.springrest.model","com.springrest.springrest.services.impl.CourseServiceImpl")
//@EnableJpaRepositories("com.springrest.springrest.repository")
@SpringBootApplication
public class SpringrestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringrestApplication.class, args);
		
	}

}
