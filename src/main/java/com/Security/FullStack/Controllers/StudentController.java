package com.Security.FullStack.Controllers;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Security.FullStack.DTOs.LoginResponse;
import com.Security.FullStack.DTOs.StudentDto;
import com.Security.FullStack.DTOs.StudentLogin;
import com.Security.FullStack.Service.StudentAuthentication;
import com.Security.FullStack.Service.StudentService;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
	
		private AtomicInteger integer = new AtomicInteger(9);

		private StudentService studentService;
		private StudentAuthentication authStudent;
		
		
		
		public StudentController(StudentService studentService, StudentAuthentication authStudent) {
			super();
			this.studentService = studentService;
			this.authStudent = authStudent;
		}

		@PostMapping("/register")
		public ResponseEntity<String> registerStudent(@RequestBody StudentDto student) {
			studentService.addStudent(student);
			return ResponseEntity.ok("Student saved!!");
		}
		
		@PostMapping("/login")
		public LoginResponse login(@RequestBody StudentLogin login) {
				
			String token = authStudent.authenticateStudent(login);
			
			StudentDto studentByEmail = studentService.getStudentByEmail(login.getEmail());
			
			return LoginResponse.builder().token(token)
					.studentName(studentByEmail.getName()).build();
					
		}
		
		@GetMapping("/getStudentDetail")
		public StudentDto getStudentDetails(@RequestParam String email) {
			return studentService.getStudentByEmail(email);
					}
		
		@GetMapping("/student")
		@CrossOrigin(value = "http://localhost:5173",allowedHeaders = "*")
		public String getStudent() {
			return "Student";
		}
}
