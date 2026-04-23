package com.example.myproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myproject {
	@GetMapping("/")
	public String index() {
		return "Hello MIT !";
	}
}
