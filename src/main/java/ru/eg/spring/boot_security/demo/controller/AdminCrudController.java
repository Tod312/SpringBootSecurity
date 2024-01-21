package ru.eg.spring.boot_security.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.eg.spring.boot_security.demo.model.User;
import ru.eg.spring.boot_security.demo.service.UserService;
import ru.eg.spring.boot_security.demo.util.UserValidator;

@Controller
@RequestMapping("/admin")
public class AdminCrudController {
	
	private final Validator userValidator;
	
	private final UserService service;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	
	@Autowired
	public AdminCrudController(UserService service, Validator userValidator) {
		this.userValidator = userValidator;
		this.service = service;
	}
	
	@GetMapping
	public String users(@ModelAttribute("user") User user, ModelMap modelMap) {
		List<User> users = service.findAll();
		modelMap.addAttribute("users", users);
		return "users";
	}
	
	@GetMapping("/user")
	public String user(@RequestParam(name = "id") Integer userId, ModelMap modelMap) {
		System.out.println("User id= " + userId);
		User user = service.findById(userId);
		System.out.println(user);
		modelMap.addAttribute("user", user);
		return "user";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("user") User user,ModelMap model, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors()) {
			List<User> users = service.findAll();
			model.addAttribute("users", users);
			return "users";
		}
		service.create(user);
		return "redirect:/admin";
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam("id") Integer id) {
		service.delete(id);
		return "redirect:/admin";
	}
	
	@PatchMapping("/update")
	public String update(@ModelAttribute("user") User user, BindingResult bindingResult) {
		System.out.println("User from update");
		System.out.println(user);
		userValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors()) {
			System.out.println("User has errors in update method");
			//User oldUser = service.findById(user.getId());
			return "user";
		}
		user.setRoles(service.findById(user.getId()).getRoles());
		System.out.println(user);
		service.update(user);
		return "redirect:/admin";
	}
}
