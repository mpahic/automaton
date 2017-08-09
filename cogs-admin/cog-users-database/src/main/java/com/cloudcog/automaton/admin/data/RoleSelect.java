package com.cloudcog.automaton.admin.data;

import com.cloudcog.automaton.admin.data.entity.Role;
import com.vaadin.ui.ComboBox;

public class RoleSelect extends ComboBox<String> {
	private static final long serialVersionUID = 1235233604041225813L;

	public RoleSelect() {
		setCaption("Role");
		setEmptySelectionAllowed(false);
		setItems(Role.getAllRoles());
		setTextInputAllowed(false);
	}
}
