package com.Security.FullStack.DTOs;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

		@NotBlank(message = "please give correct Name")
		private String name;
		
		@NotBlank
		@Email(message = "please give a valid email")
		private String email;
		
		@NotBlank
		@Length(min = 3, max = 50, message = "Password should be more than 7 words and leass than 50 words")
		private String password;
		
		private Integer totalMarks;
}
