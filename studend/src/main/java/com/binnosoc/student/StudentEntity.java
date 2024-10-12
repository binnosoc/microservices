package com.binnosoc.student;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class StudentEntity {
	
	@Id
	private Integer id;
	private String name;
	private String email;
	private Integer schoolId; 
	
}
