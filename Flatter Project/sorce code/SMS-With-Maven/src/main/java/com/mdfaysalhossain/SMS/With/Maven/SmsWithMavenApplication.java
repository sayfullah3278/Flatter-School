package com.mdfaysalhossain.SMS.With.Maven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class SmsWithMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsWithMavenApplication.class, args);
	}

}
