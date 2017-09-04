package com.cloudcog.automaton.admin.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudcog.automaton.admin.data.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	Privilege findByName(String name);
}
