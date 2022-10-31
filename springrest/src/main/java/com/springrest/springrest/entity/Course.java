package com.springrest.springrest.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "COURSE_TBL")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//(strategy = GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Long id;
		

	private String title; //TODO string should be between 3 to 15

	private String description;//TODO it need to accept only 30 chars
	
	private Long credits;
	
	//	@ManyToMany(mappedBy = "students")
		@ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
	 	@JsonBackReference
	    private List<Student> students;
			
}
