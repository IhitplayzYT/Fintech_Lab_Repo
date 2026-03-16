package com.mit;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.mit.*")
@EnableScheduling
public class MitDemoApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(MitDemoApplication.class, args);
	}
}
