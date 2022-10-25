package com.springrest.springrest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class CourseDto {

	@NotNull
	@Size(min = 3, max = 15,message = "Invalid size input")
	private String title; //TODO string should be between 3 to 15
	
	@Pattern(regexp="^[A-Za-z\\s]*$",message = "Invalid pattern Input")
	private String description;//TODO it need to accept only 30 chars
	
	private Long credits;
	
}
