package com.Security.FullStack.Utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Security.FullStack.DTOs.StudentDto;
import com.Security.FullStack.Entity.StudentEntity;

@Service
public class MapperUtils {
	
	private PasswordEncoder encoder;

	
		public MapperUtils(PasswordEncoder encoder) {
		super();
		this.encoder = encoder;
	}

		public StudentEntity mapDtoToEntity(StudentDto student) {
			
			return StudentEntity.builder().email(student.getEmail()).password(encoder.encode(student.getPassword())).totalMarks(student.getTotalMarks())
					.studentName(student.getName()).build();
		}
		
	public StudentDto mapEntityToDto(StudentEntity student) {
			
			return StudentDto.builder().email(student.getEmail()).totalMarks(student.getTotalMarks())
					.name(student.getStudentName()).build();
		}
}
