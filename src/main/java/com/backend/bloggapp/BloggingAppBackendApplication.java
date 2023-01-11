package com.backend.bloggapp;

import com.backend.bloggapp.config.AppConstants;
import com.backend.bloggapp.entities.Role;
import com.backend.bloggapp.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication			// this annotation provides us configuration annotation as well
public class BloggingAppBackendApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppBackendApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("password"));
		try
		{
			Role role = new Role(AppConstants.ADMIN_USER, "ROLE_ADMIN");

			Role role1 = new Role(AppConstants.NORMAL_USER, "ROLE_NORMAL");

			List<Role> allRoles = this.roleRepository.saveAll(List.of(role, role1));
			allRoles.forEach(r-> System.out.println(r.getName()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
