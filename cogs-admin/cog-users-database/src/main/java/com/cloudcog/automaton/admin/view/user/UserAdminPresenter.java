package com.cloudcog.automaton.admin.view.user;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloudcog.automaton.admin.data.entity.User;
import com.cloudcog.automaton.admin.view.AbstractCrudPresenter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent
@ViewScope
public class UserAdminPresenter extends AbstractCrudPresenter<User, UserService, UserAdminView>
		implements Serializable {
	private static final long serialVersionUID = 1121641582614222283L;


	@Autowired
	public UserAdminPresenter(UserAdminDataProvider userAdminDataProvider, 
			UserService service) {
		super(service, User.class, userAdminDataProvider);
	}

	public String encodePassword(String value) {
		return getService().encodePassword(value);
	}

	@Override
	protected void editItem(User item) {
		super.editItem(item);
		getView().setPasswordRequired(item.isNew());
	}


}