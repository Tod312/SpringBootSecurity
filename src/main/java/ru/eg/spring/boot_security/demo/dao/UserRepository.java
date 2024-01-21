package ru.eg.spring.boot_security.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.eg.spring.boot_security.demo.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	Optional<User> findByLogin(String login);
}
