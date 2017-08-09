package com.cloudcog.automaton.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cloudcog.automaton.admin.data.UserRepository;
import com.cloudcog.automaton.admin.data.entity.Role;
import com.cloudcog.automaton.admin.data.entity.User;
import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class DataGenerator {

	private static final Logger log = LoggerFactory.getLogger(DataGenerator.class);

	private User user;

	@Bean
	public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (hasData(userRepository)) {
				log.info("Using existing database");
				return;
			}

			log.info("Generating demo data");
			log.info("... generating users");
			createUsers(userRepository, passwordEncoder);

			log.info("Generated demo data");
		};
	}

	private boolean hasData(UserRepository userRepository) {
		return userRepository.count() != 0L;
	}

	private void createUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		user = new User("admin", "Admin", "Admin", passwordEncoder.encode("admin"), Role.ADMIN, "admin@admin.com");
		user.setLocked(true);
		userRepository.save(user);
	}
}
