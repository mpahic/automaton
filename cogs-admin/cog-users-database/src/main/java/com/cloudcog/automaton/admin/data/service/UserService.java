package com.cloudcog.automaton.admin.data.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudcog.automaton.admin.data.UserRepository;
import com.cloudcog.automaton.admin.data.entity.User;
import com.cloudcog.automaton.admin.security.SecurityUtils;

@Service
public class UserService extends CrudService<User> {

	private static final String MODIFY_LOCKED_USER_NOT_PERMITTED = "User has been locked and cannot be modified or deleted";
	private static final String DELETE_CURRENT_USER_NOT_PERMITTED = "Current user cannot be modified or deleted";
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User findByUsername(String username) {
		return getRepository().findByUsername(username);
	}

	@Override
	public Page<User> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByUsernameLikeIgnoreCaseOrNameLikeIgnoreCaseOrRoleLikeIgnoreCase(
					repositoryFilter,
					repositoryFilter, repositoryFilter, pageable);
		} else {
			return getRepository().findAll(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByUsernameLikeIgnoreCaseOrNameLikeIgnoreCase(repositoryFilter,
					repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	@Override
	protected UserRepository getRepository() {
		return userRepository;
	}

	public String encodePassword(String value) {
		return passwordEncoder.encode(value);
	}

	@Override
	@Transactional
	public User save(User entity) {
		return super.save(entity);
	}

	@Override
	@Transactional
	public void delete(long userId) {
		throwIfUserLocked(userId);
		super.delete(userId);
	}

	private void throwIfUserLocked(Long userId) {
		if (userId == null) {
			return;
		}

		User dbUser = getRepository().findOne(userId);
		if (dbUser.isLocked()) {
			throw new UserFriendlyDataException(MODIFY_LOCKED_USER_NOT_PERMITTED);
		} else if (SecurityUtils.getCurrentUser(this).getId().equals(userId)) {
			throw new UserFriendlyDataException(DELETE_CURRENT_USER_NOT_PERMITTED);
		}
	}

}
