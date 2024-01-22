package ru.eg.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.eg.spring.boot_security.demo.model.User;
import ru.eg.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationControllee {
	
	
	private final UserService userService;
	
	private final Validator userValidator;
	
	public RegistrationControllee(UserService userService, Validator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}

	@GetMapping
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("user", new User());
		return "registration";
	}
	
	@PostMapping
	public String registrationForm(@ModelAttribute("user") User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		userService.create(user);
		return "redirect:/login";
	}
	
}
