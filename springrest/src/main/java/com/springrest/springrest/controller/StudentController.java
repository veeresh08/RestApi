package com.springrest.springrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {
	@GetMapping("/shome")
	public String Studenthome() {
		return "Welcome to courses application";
	}
}
