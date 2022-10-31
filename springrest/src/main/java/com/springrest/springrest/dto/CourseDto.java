package com.springrest.springrest.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class CourseDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//(strategy = GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 15,message = "Invalid size input")
	private String title; //TODO string should be between 3 to 15
	
	@Pattern(regexp="^[A-Za-z\\s]*$",message = "Invalid pattern Input")
	private String description;//TODO it need to accept only 30 chars
	
	private Long credits;
	
}
