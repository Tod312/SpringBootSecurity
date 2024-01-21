package ru.eg.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.eg.spring.boot_security.demo.model.User;

@Controller
@RequestMapping("/registration")
public class RegistrationControllee {

	
	
	@GetMapping
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("user", new User());
		return "registration";
	}
	
	@PostMapping
	public String registrationForm(@ModelAttribute("user") User user) {
		
		return "registration";
	}
}
