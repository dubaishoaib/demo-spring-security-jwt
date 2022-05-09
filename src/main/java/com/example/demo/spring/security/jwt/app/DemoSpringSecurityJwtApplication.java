package com.example.demo.spring.security.jwt.app;

import com.example.demo.spring.security.jwt.domain.Role;
import com.example.demo.spring.security.jwt.domain.User;
import com.example.demo.spring.security.jwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.example.demo.spring.security.jwt.config",
		"com.example.demo.spring.security.jwt.api",
		"com.example.demo.spring.security.jwt.domain",
		"com.example.demo.spring.security.jwt.repo",
		"com.example.demo.spring.security.jwt.service"
})
public class DemoSpringSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringSecurityJwtApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return  args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "John T", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Will Smith", "will", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Jim Carry", "jim", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Arnold Sch", "arnold", "1234", new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("john", "ROLE_MANAGER");
			userService.addRoleToUser("will", "ROLE_MANAGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_USER");


		};
	}
}
