package com.springrest.springrest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "tbl_student"//,
//        uniqueConstraints = @UniqueConstraint(
//                name = "emailid_unique",
//                columnNames = "email_address"
//        )
)
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	private String firstName;
	private String lastName;
	
	@Column(
            name = "email_address",
            nullable = false
    )
	private String emailId;
	
	

 	
 	@ManyToMany(
		 	fetch = FetchType.LAZY,
            cascade  = CascadeType.ALL
    )
    @JoinTable(
            name = "studen_course_mapping",
            joinColumns = {
            @JoinColumn(name = "student_id",referencedColumnName = "studentId")
            },
            inverseJoinColumns = {
            		@JoinColumn(name = "course_id", referencedColumnName = "id")
    })
 	@JsonBackReference
	private List<Course> courses;
	
}
