package com.Security.FullStack.Service;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtFilterService  extends OncePerRequestFilter {
	
	private JwtService jwtService;
	private StudentAuthentication studentService; 
	private UserDetailsService userDetailsService;
	

	public JwtFilterService(JwtService jwtService, StudentAuthentication studentService,
			UserDetailsService userDetailsService) {
		super();
		this.jwtService = jwtService;
		this.studentService = studentService;
		this.userDetailsService = userDetailsService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
		String header = request.getHeader("Authorization");
		
		if(header!=null&&header.startsWith("Bearer ")) {
			
			String jwt = header.substring(7);
			String userNameFromToken = jwtService.extractUserNameFromToken(jwt);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(userNameFromToken);
			
			if(jwtService.validateToken(jwt, userDetails)&&SecurityContextHolder.getContext().getAuthentication()==null) {
				
					UsernamePasswordAuthenticationToken authenticated = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					authenticated.setDetails(request);
					SecurityContextHolder.getContext().setAuthentication(authenticated);
			}
		}
		
		
		filterChain.doFilter(request, response);
	}

		
}
