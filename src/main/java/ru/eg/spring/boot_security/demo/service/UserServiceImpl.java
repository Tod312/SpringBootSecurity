package ru.eg.spring.boot_security.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.eg.spring.boot_security.demo.dao.RoleRepository;
import ru.eg.spring.boot_security.demo.dao.UserRepository;
import ru.eg.spring.boot_security.demo.model.User;

@Transactional
@Service
public class UserServiceImpl implements UserService{

	private final UserRepository repository;
	
	private final RoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository repository, RoleRepository roleRepository, PasswordEncoder encoder) {
		this.repository = repository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = encoder;
	}

	@Transactional(readOnly = true)
	@Override
	public User findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	@Override
	public User findByLogin(String login) {
		return repository.findByLogin(login).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {
		return (List<User>) repository.findAll();
	}

	@Override
	public void create(User user) {
		user.setRoles(Set.of(roleRepository.findRoleByName("ROLE_USER").get()));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
		
	}

	@Override
	public void update(User user) {
		repository.findById(user.getId())
					.ifPresent(oldUser -> {
						oldUser.setLogin(user.getUsername());
						oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
						oldUser.setRoles(user.getRoles());
						repository.save(oldUser);
					});
		
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
		
	}

}
