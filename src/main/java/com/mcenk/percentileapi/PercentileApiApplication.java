package com.mcenk.percentileapi;

import com.mcenk.percentileapi.model.Role;
import com.mcenk.percentileapi.model.User;
import com.mcenk.percentileapi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// security ekledigimiz icin butun endpointler secure oldu
// gecici olarak bu endpointlerden secure durumunu bu sekilde kaldirdik
public class PercentileApiApplication implements CommandLineRunner {
	private final UserService userService;

	public PercentileApiApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(PercentileApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userService.createUser(User.builder().username("serra").email("abc@gmail.com").password("pass").role(Role.ADMIN).build());
		userService.createUser(User.builder().username("merve").email("abc@gmail.com").password("pass").role(Role.USER).build());
	}
}
