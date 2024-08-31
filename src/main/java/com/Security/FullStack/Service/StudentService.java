package com.Security.FullStack.Service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Security.FullStack.DTOs.StudentDto;
import com.Security.FullStack.Entity.StudentEntity;
import com.Security.FullStack.Repositories.StudentRepo;
import com.Security.FullStack.Utils.MapperUtils;

@Service
public class StudentService {

		private StudentRepo repo;
		private MapperUtils mapper;
		
		

		public StudentService(StudentRepo repo, MapperUtils mapper) {
			super();
			this.repo = repo;
			this.mapper = mapper;
		}

		public void addStudent(StudentDto stud) {
			
				StudentEntity student = mapper.mapDtoToEntity(stud);
						
				StudentEntity save = repo.save(student);
				
				System.out.println(save);
		}
		
		public StudentDto getStudentByEmail(String email) {
			
				StudentEntity student = repo.findStudentByEmail(email).orElseThrow(()->new UsernameNotFoundException("please register user!"));
				StudentDto mapEntityToDto = mapper.mapEntityToDto(student);
				return mapEntityToDto;
		}
		
}
