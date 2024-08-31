package com.Security.FullStack.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration implements WebMvcConfigurer {
	
	@Bean
	 WebMvcConfigurer MvcConfigurer() {
			
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					// TODO Auto-generated method stub
					registry.addMapping("/**") .allowedOrigins("http://localhost:5173").allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "Accept")
					.allowCredentials(true)
					.exposedHeaders("Access-Control-Allow-Origin")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
						
				}
			};	
	}
		
}
