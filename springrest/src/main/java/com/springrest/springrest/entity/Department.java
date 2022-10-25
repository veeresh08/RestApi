package com.springrest.springrest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long departmentId;
	
	
	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
	
	
	@OneToMany(targetEntity = Course.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="course_fk",referencedColumnName = "departmentId")
    private List<Course> courses;
	
}
