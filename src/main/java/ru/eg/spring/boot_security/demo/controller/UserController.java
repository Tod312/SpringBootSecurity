package ru.eg.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.eg.spring.boot_security.demo.dao.UserRepository;
import ru.eg.spring.boot_security.demo.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserRepository userRepository;
	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@GetMapping
	public String getUser(ModelMap model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByLogin(name).get();
		model.addAttribute("user", user);
		return "userWelcome";
	}
}
