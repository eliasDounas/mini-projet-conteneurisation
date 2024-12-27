package com.notes_etudiants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class NotesEtudiantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesEtudiantsApplication.class, args);
	}

   @Bean
WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://aseds.inpt.com")  // Your frontend domain
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Include OPTIONS
                    .allowCredentials(true);
        }
    };
}

}
