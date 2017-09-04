package com.cloudcog.automaton.admin.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudcog.automaton.admin.data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String email);

	Page<User> findByUsernameLikeIgnoreCaseOrNameLikeIgnoreCase(String usernameLike, String nameLike,
			Pageable pageable);

	long countByUsernameLikeIgnoreCaseOrNameLikeIgnoreCase(String usernameLike, String nameLike);
}
