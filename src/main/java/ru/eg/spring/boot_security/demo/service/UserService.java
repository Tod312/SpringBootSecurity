package ru.eg.spring.boot_security.demo.service;

import java.util.List;

import ru.eg.spring.boot_security.demo.model.User;

public interface UserService {
	User findById(Integer id);
	User findByLogin(String login);
	List<User> findAll();
	void create(User user);
	void update(User user);
	void delete(Integer id);
}
