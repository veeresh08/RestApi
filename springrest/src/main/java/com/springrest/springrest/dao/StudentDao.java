package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springrest.springrest.entity.Student;

public interface StudentDao extends JpaRepository<Student,Long>{

}
