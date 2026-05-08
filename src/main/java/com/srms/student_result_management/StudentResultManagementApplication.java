package com.srms.student_result_management;

import com.srms.student_result_management.model.User;
import com.srms.student_result_management.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentResultManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentResultManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner createAdmin(UserService userService) {
		return args -> {
			if (userService.findByUsername("admin") == null) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword("admin123");
				admin.setRole("ADMIN");
				userService.saveUser(admin);
				System.out.println("✅ Admin created: admin / admin123");
			}
		};
	}
}