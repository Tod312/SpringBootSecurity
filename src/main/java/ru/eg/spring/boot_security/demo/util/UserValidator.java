package ru.eg.spring.boot_security.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.eg.spring.boot_security.demo.model.User;
import ru.eg.spring.boot_security.demo.service.UserService;

@Component
public class UserValidator implements Validator{

	@Autowired
	private UserService service;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(User.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		validateLogin(user, errors);
		validatePassword(user, errors);
	}

	private void validateLogin(User user, Errors errors) {
		Pattern pattern = Pattern.compile(".*[\\[<>?*&$# !].*");
		Matcher matcher = pattern.matcher(user.getLogin());
		
		if(matcher.find()) {
			errors.rejectValue("login", "invalid", "The login must not contain prohibited characters(<>?*&$# !)");	
		}else if(user.getLogin().isBlank() || user.getLogin().isEmpty()) {
			errors.rejectValue("login", "invalid", "login cannot be empty");
		}else if(user.getLogin().length() < 5){
			errors.rejectValue("login","invalid", "The login must contain more than 5 letters");
		}else {
			User oldUser = service.findByLogin(user.getLogin());
			if(oldUser != null) {
				if(oldUser.getId() != user.getId()) {
					errors.rejectValue("login","invalid", "This login is already taken ");
				}
			}
		}
	}
	
	private void validatePassword(User user, Errors errors) {
		Pattern pattern = Pattern.compile(".*[\\[<>?*&$# !].*");
		Matcher matcher = pattern.matcher(user.getPassword());
		
		if(matcher.find()) {
			errors.rejectValue("password", "invalid", "The password must not contain prohibited characters(<>?*&$# !)");
		}else if(user.getPassword().isBlank() || user.getPassword().isEmpty()) {
			errors.rejectValue("password", "invalid", "Password cannot be empty");
		}else if(user.getPassword().length() < 6){
			errors.rejectValue("password","invalid", "Password must contain more than 5 letters");
		}
	}
}
