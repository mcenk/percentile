package com.mcenk.percentileapi;

import com.mcenk.percentileapi.model.Role;
import com.mcenk.percentileapi.model.User;
import com.mcenk.percentileapi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
// security ekledigimiz icin butun endpointler secure oldu
// gecici olarak bu endpointlerden secure durumunu bu sekilde kaldirdik
public class PercentileApiApplication  {

	public static void main(String[] args) {
		SpringApplication.run(PercentileApiApplication.class, args);
	}


}
