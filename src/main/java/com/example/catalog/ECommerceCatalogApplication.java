package com.example.catalog;

import com.example.catalog.common.auth.entity.User;
import com.example.catalog.common.auth.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ECommerceCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceCatalogApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Create and save a user for testing purposes on application startup
			if (userRepository.findByUsername("admin").isEmpty()) {
				User adminUser = new User("admin", passwordEncoder.encode("Frankliu@12345!"));
				userRepository.save(adminUser);
			}
		};
	}

}
