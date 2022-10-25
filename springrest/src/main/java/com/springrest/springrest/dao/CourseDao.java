package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springrest.springrest.entity.Course;


public interface CourseDao extends JpaRepository<Course, Long>{

	public Course findByTitle(String title);

	public Course findByTitleIgnoreCase(String title);

}
