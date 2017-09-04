package com.cloudcog.automaton.admin.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudcog.automaton.admin.data.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
