package com.springrest.springrest.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private long id;
	
	@Size(min = 3, max = 15,message = "Invalid size input")
	private String title; //TODO string should be between 3 to 15
	
	@Pattern(regexp="^[A-Za-z\\s]*$",message = "Invalid pattern Input")
	private String description;//TODO it need to accept only 30 chars

	

	//	@Override
//	public String toString() {
//		return "Course [id=" + id + ", title=" + title + ", description=" + description + "]";
//	}
	
	
	
}
