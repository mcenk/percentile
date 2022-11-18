package com.mcenk.percentileapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
// security ekledigimiz icin butun endpointler secure oldu
// gecici olarak bu endpointlerden secure durumunu bu sekilde kaldirdik
public class PercentileApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PercentileApiApplication.class, args);
	}

}
