package ru.eg.spring.boot_security.demo.configs;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ru.eg.spring.boot_security.demo.dao.RoleRepository;
import ru.eg.spring.boot_security.demo.dao.UserRepository;
import ru.eg.spring.boot_security.demo.model.Role;
import ru.eg.spring.boot_security.demo.model.User;

@Component
public class RunAfterStartup implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		Role role1 = new Role("ROLE_USER");
		Role role2 = new Role("ROLE_ADMIN");
		roleRepository.saveAll(List.of(role1, role2));
		
		String passwordUser = encoder.encode("user");
		String passwordAdmin = encoder.encode("admin");
		User user1 = new User("user",passwordUser, Set.of(role1));
		User user2 = new User("admin",passwordAdmin, Set.of(role1, role2));
		
		userRepository.saveAll(List.of(user1, user2));
	}

	
}
