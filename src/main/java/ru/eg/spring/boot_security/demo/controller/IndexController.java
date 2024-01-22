package ru.eg.spring.boot_security.demo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping
	public String index(ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<String> auth = authentication.getAuthorities().stream().map(authorities -> authorities.getAuthority()).peek(System.out::println).toList();
		if(auth.contains("ROLE_ANONYMOUS")) {
			modelMap.addAttribute("name", null);
		}else {
			modelMap.addAttribute("name", "auth");
		}
		return "index";
	}
}
