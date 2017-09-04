package com.cloudcog.automaton.admin.view.user;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloudcog.automaton.admin.data.entity.User;
import com.cloudcog.automaton.admin.view.AbstractCrudView;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

@SpringView(name = UserAdminView.VIEW_NAME)
public class UserAdminView extends AbstractCrudView<User> {
	private static final long serialVersionUID = -1389746878486716853L;

	public static final String VIEW_NAME = "Users";

	@Autowired
	private UserAdminPresenter presenter;

	private final UserAdminViewDesign userAdminViewDesign = new UserAdminViewDesign();
	private boolean passwordRequired;

	/**
	 * Custom validator to be able to decide dynamically whether the password
	 * field is required or not (empty value when updating the user is
	 * interpreted as 'do not change the password').
	 */
	private Validator<String> passwordValidator = new Validator<String>() {
		private static final long serialVersionUID = 1223706838305312825L;
		BeanValidator passwordBeanValidator = new BeanValidator(User.class, "password");

		@Override
		public ValidationResult apply(String value, ValueContext context) {
			if (!passwordRequired && value.isEmpty()) {
				// No password required and field is empty
				// OK regardless of other restrictions as the empty value will
				// not be used
				return ValidationResult.ok();
			} else {
				return passwordBeanValidator.apply(value, context);
			}
		}
	};

	@PostConstruct
	private void init() {
		presenter.init(this);
		getGrid().setColumns("username", "name", "surname");
	}

	@Override
	public void bindFormFields(BeanValidationBinder<User> binder) {
		binder.forField(getViewComponent().password).withValidator(passwordValidator).bind(bean -> "",
				(bean, value) -> {
					if (value.isEmpty()) {
						// If nothing is entered in the password field, do
						// nothing
					} else {
						bean.setPassword(presenter.encodePassword(value));
					}
				});

		binder.bind(getViewComponent().roles, "roles");
		binder.bindInstanceFields(getViewComponent());
	}

	public void setPasswordRequired(boolean passwordRequired) {
		this.passwordRequired = passwordRequired;
		getViewComponent().password.setRequiredIndicatorVisible(passwordRequired);
	}

	@Override
	public void editItem(boolean isNew) {
		super.editItem(isNew);

		if (isNew) {
			userAdminViewDesign.username.setEnabled(true);
		} else {
			userAdminViewDesign.username.setEnabled(false);
		}
	}

	@Override
	public UserAdminViewDesign getViewComponent() {
		return userAdminViewDesign;
	}

	@Override
	protected UserAdminPresenter getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<User> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<User> grid) {
		getViewComponent().list = grid;
	}

	@Override
	protected Component getForm() {
		return getViewComponent().form;
	}

	@Override
	protected Button getAdd() {
		return getViewComponent().add;
	}

	@Override
	protected Button getCancel() {
		return getViewComponent().cancel;
	}

	@Override
	protected Button getDelete() {
		return getViewComponent().delete;
	}

	@Override
	protected Button getUpdate() {
		return getViewComponent().update;
	}

	@Override
	protected TextField getSearch() {
		return getViewComponent().search;
	}

	@Override
	protected Focusable getFirstFormField() {
		return getViewComponent().username;
	}

}
