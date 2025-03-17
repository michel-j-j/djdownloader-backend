package com.djdownloader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
								.allowedOrigins("http://localhost:5173") // Puerto por defecto de Vite
								.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
								.allowedHeaders("*")
								.exposedHeaders("content-disposition", "x-original-title", "x-artist", "x-duration")
				;
			}
		};
	}
}
