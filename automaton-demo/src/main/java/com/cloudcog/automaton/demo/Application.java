package com.cloudcog.automaton.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cloudcog.automaton.admin.authentication.BasicAccessControl;
import com.cloudcog.automaton.admin.data.UserRepository;
import com.cloudcog.automaton.admin.data.entity.User;
import com.cloudcog.automaton.admin.data.service.UserService;
import com.cloudcog.automaton.admin.data.util.LocalDateJpaConverter;
import com.cloudcog.automaton.admin.security.UserDetailsServiceImpl;
import com.cloudcog.automaton.admin.view.user.UserAdminPresenter;

@SpringBootApplication(scanBasePackageClasses = { DemoUI.class, Application.class, UserService.class,
		SecurityConfig.class, UserDetailsServiceImpl.class, BasicAccessControl.class, UserAdminPresenter.class })
@EnableJpaRepositories(basePackageClasses = { UserRepository.class })
@EntityScan(basePackageClasses = { User.class, LocalDateJpaConverter.class })
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
