package ru.eg.spring.boot_security.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ru.eg.spring.boot_security.demo.dao.UserRepository;
import ru.eg.spring.boot_security.demo.model.User;

public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user= repository.findByLogin(username);
		
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		return user.get();
	}

}
