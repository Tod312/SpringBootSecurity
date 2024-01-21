package ru.eg.spring.boot_security.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import ru.eg.spring.boot_security.demo.model.Role;

public interface RoleRepository extends Repository<Role, Integer>{
	Optional<Role> findRoleByName(String name);
}
