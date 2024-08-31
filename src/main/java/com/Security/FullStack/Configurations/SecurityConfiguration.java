package com.Security.FullStack.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Security.FullStack.Repositories.StudentRepo;
import com.Security.FullStack.Service.JwtFilterService;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfiguration {
	
	private StudentRepo studentRepo;
		
	
	
	

	public SecurityConfiguration(StudentRepo studentRepo) {
		super();
		this.studentRepo = studentRepo;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http,JwtFilterService filterService) throws Exception {
		
		return http.csrf(c->c.disable()).cors(Customizer.withDefaults())
		.authorizeHttpRequests(req->req.requestMatchers("login","register").permitAll().anyRequest().authenticated())
		.sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//		.formLogin(Customizer.withDefaults())
		.addFilterBefore(filterService, UsernamePasswordAuthenticationFilter.class)
		.httpBasic(Customizer.withDefaults())
		.build();
//		
//		return httpReq.csrf(t -> t.disable())
//				.authorizeHttpRequests((req)->req.requestMatchers("login","register")
//				.permitAll()
//				.anyRequest().authenticated())
//			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//			.httpBasic(Customizer.withDefaults())
//			.build();
		
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return (username)-> (studentRepo.findStudentByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User is not registered !")));
	}
	
	
	@Bean
	AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(encoder());
		return provider;
	}
	
	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration conf) throws Exception {
		return conf.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}

















