package com.example.securityproject;

import com.example.securityproject.domain.Role;
import com.example.securityproject.domain.User;
import com.example.securityproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityProjectApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args->{
			userService.saveRole(new Role("ROLE_USER"));
			userService.saveRole(new Role("ROLE_MANAGER"));
			userService.saveRole(new Role("ROLE_ADMIN"));
			userService.saveRole(new Role("ROLE_SUPER_ADMIN"));


			userService.saveUser(new User("John travski", "john","1234", new ArrayList<>()));
			userService.saveUser(new User("kumara travski", "kumara","1234", new ArrayList<>()));
			userService.saveUser(new User("chathuri travski", "chaturi","1234", new ArrayList<>()));
			userService.saveUser(new User("gune travski", "gune","1234", new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("kumara", "ROLE_USER");
			userService.addRoleToUser("chaturi", "ROLE_MANAGER");
			userService.addRoleToUser("gune", "ROLE_MANAGER");
			userService.addRoleToUser("john", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("john", "ROLE_ADMIN");
		};
	}
}
