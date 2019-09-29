package com.LearningSecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormController {
	
	@GetMapping("/everyone")
	String getEveryOne() {
		return "Hello everone";
	}
	
	@GetMapping("/admin")
	String getAdmin() {
		return "The admin is here man ";
	}
	

}
