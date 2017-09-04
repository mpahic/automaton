package com.cloudcog.automaton.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.cloudcog.automaton.admin.data.PrivilegeRepository;
import com.cloudcog.automaton.admin.data.RoleRepository;
import com.cloudcog.automaton.admin.data.UserRepository;
import com.cloudcog.automaton.admin.data.entity.Privilege;
import com.cloudcog.automaton.admin.data.entity.Role;
import com.cloudcog.automaton.admin.data.entity.User;
import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class DataGenerator implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger log = LoggerFactory.getLogger(DataGenerator.class);

	// private User user;
	//
	// @Bean
	// public CommandLineRunner loadData(UserRepository userRepository,
	// PasswordEncoder passwordEncoder) {
	// return args -> {
	// if (hasData(userRepository)) {
	// log.info("Using existing database");
	// return;
	// }
	//
	// log.info("Generating demo data");
	// log.info("... generating users");
	// createUsers(userRepository, passwordEncoder);
	//
	// log.info("Generated demo data");
	// };
	// }
	//
	// private boolean hasData(UserRepository userRepository) {
	// return userRepository.count() != 0L;
	// }
	//
	// private void createUsers(UserRepository userRepository, PasswordEncoder
	// passwordEncoder) {
	// user = new User("admin", "Admin", "Admin", passwordEncoder.encode("admin"),
	// Role.ADMIN, "admin@admin.com");
	// user.setLocked(true);
	// userRepository.save(user);
	// }

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		 if (hasData(userRepository)) {
			log.info("Using existing database");
			return;
		}
		
		if (alreadySetup)
			return;
		Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

		List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		User user = new User("admin", "Admin", "Admin", passwordEncoder.encode("admin"), adminRole, "admin@admin.com");
		user.setLocked(true);
		userRepository.save(user);

		alreadySetup = true;
	}

	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

	private boolean hasData(UserRepository userRepository) {
		return userRepository.count() != 0L;
	}
}
