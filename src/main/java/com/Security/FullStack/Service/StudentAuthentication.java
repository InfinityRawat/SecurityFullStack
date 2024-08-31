package com.Security.FullStack.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.Security.FullStack.DTOs.StudentLogin;

@Service
public class StudentAuthentication {
			
//		private StudentRepo repo;
		private AuthenticationManager authManager;
		private JwtService jwtService;
		
		
		
		public StudentAuthentication(AuthenticationManager authManager, JwtService jwtService) {
			super();
			this.authManager = authManager;
			this.jwtService = jwtService;
		}



		public String authenticateStudent(StudentLogin login) {
			String jwtToken=null;
				Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
				
				if(authenticate.isAuthenticated()) {
					 jwtToken = jwtService.generateJWTToken(login.getEmail());
				}
				return  jwtToken;
				
		}
		
}
