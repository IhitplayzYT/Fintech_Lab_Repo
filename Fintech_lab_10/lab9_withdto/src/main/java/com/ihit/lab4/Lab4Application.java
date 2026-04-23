package com.ihit.lab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
    "com.ihit.lab4.controller",
    "com.ihit.lab4.serviceimpl",
    "com.ihit.lab4.dao",
    "com.ihit.lab4.mapper"
})
public class Lab4Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab4Application.class, args);
	}

}
