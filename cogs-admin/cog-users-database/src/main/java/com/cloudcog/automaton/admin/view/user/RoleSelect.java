package com.cloudcog.automaton.admin.view.user;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloudcog.automaton.admin.data.RoleRepository;
import com.cloudcog.automaton.admin.data.entity.Role;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CheckBoxGroup;

@UIScope
@SpringComponent
public class RoleSelect extends CheckBoxGroup<Role> {
	private static final long serialVersionUID = 1235233604041225813L;

	@Autowired
	RoleRepository roleRepository;

	public RoleSelect() {
		setCaption("Role");
	}

	@PostConstruct
	private void init() {
		setItems(roleRepository.findAll());
	}

	public void setRoles(List<Role> roles) {

		this.select((Role[]) roles.toArray());
	}

}
