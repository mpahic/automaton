package com.cloudcog.automaton.admin.view.user;

import com.cloudcog.automaton.admin.data.RoleSelect;
import com.cloudcog.automaton.admin.data.entity.User;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UserAdminViewDesign extends VerticalLayout {
	private static final long serialVersionUID = -2583685316687797388L;

	protected TextField search;
	protected Button add;
	protected Grid<User> list;
	protected HorizontalLayout form;
	protected TextField username;
	protected TextField name;
	protected TextField surname;
	protected PasswordField password;
	protected TextField email;
	protected TextField phoneNumber;
	protected RoleSelect role;
	protected Button update;
	protected Button cancel;
	protected Button delete;

	public UserAdminViewDesign() {
		setStyleName("crud-template");
		setSpacing(false);
		setResponsive(true);
		setMargin(false);
		setSizeFull();

		generateTopLayout();

		CssLayout contentLayout = new CssLayout();
		contentLayout.setStyleName("content");
		contentLayout.setSizeFull();

		CssLayout listLayout = new CssLayout();
		listLayout.setSizeFull();
		listLayout.setStyleName("list");
		list = new Grid<>(User.class);
		list.setSizeFull();
		list.setId("list");

		listLayout.addComponent(list);

		contentLayout.addComponent(listLayout);

		form = new HorizontalLayout();
		form.setEnabled(false);
		form.setStyleName("inspect");
		form.setSpacing(false);
		form.setId("form");
		form.setMargin(false);
		form.setSizeFull();

		CssLayout editLayout = new CssLayout();
		editLayout.setStyleName("edit");
		editLayout.setSizeFull();

		username = new TextField("Username");
		username.setHeight(31, Unit.PIXELS);
		editLayout.addComponent(wrapAndStylize(username));

		name = new TextField("Name");
		editLayout.addComponent(wrapAndStylize(name));

		surname = new TextField("Surname");
		editLayout.addComponent(wrapAndStylize(surname));

		password = new PasswordField("Password");
		editLayout.addComponent(wrapAndStylize(password));

		email = new TextField("Email");
		editLayout.addComponent(wrapAndStylize(email));

		phoneNumber = new TextField("Phone");
		editLayout.addComponent(wrapAndStylize(phoneNumber));

		role = new RoleSelect();
		editLayout.addComponent(wrapAndStylize(role));

		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.setWidth(100, Unit.PERCENTAGE);
		buttonsLayout.setHeight(50, Unit.PIXELS);

		update = new Button("Update");
		update.addStyleNames("small", "primary");
		buttonsLayout.addComponent(update);
		buttonsLayout.setComponentAlignment(update, Alignment.MIDDLE_LEFT);

		cancel = new Button("Cancel");
		cancel.addStyleNames("small");
		buttonsLayout.addComponent(cancel);
		buttonsLayout.setComponentAlignment(cancel, Alignment.MIDDLE_CENTER);

		delete = new Button("Delete");
		delete.addStyleNames("small", "danger");
		buttonsLayout.addComponent(delete);
		buttonsLayout.setComponentAlignment(delete, Alignment.MIDDLE_RIGHT);
		buttonsLayout.setExpandRatio(delete, 1.0f);

		editLayout.addComponent(buttonsLayout);
		form.addComponent(editLayout);
		form.setExpandRatio(editLayout, 1.0f);
		contentLayout.addComponent(form);
		this.addComponent(contentLayout);
		setExpandRatio(contentLayout, 1.0f);

	}

	private Component wrapAndStylize(Component component) {
		component.addStyleName("small");
		component.setWidth(100, Unit.PERCENTAGE);

		CssLayout wrapper = new CssLayout(component);
		wrapper.addStyleNames("section", "half");
		wrapper.setWidth(100, Unit.PERCENTAGE);
		return wrapper;
	}

	private void generateTopLayout() {
		HorizontalLayout layoutTop = new HorizontalLayout();
		layoutTop.setStyleName("top-bar");
		layoutTop.setSpacing(false);
		layoutTop.setWidth(100, Unit.PERCENTAGE);
		layoutTop.setHeight(50, Unit.PIXELS);

		HorizontalLayout layoutStyle = new HorizontalLayout();
		layoutStyle.setSpacing(false);
		layoutStyle.setWidth(100, Unit.PERCENTAGE);

		search = new TextField();
		search.addStyleNames("small", "inline-icon", "search");
		search.setPlaceholder("Search");
		search.setId("search");
		search.setIcon(VaadinIcons.SEARCH);

		layoutStyle.addComponent(search);
		layoutTop.addComponent(layoutStyle);
		layoutTop.setComponentAlignment(layoutStyle, Alignment.MIDDLE_LEFT);
		layoutTop.setExpandRatio(layoutStyle, 1.0f);

		add = new Button("Add new", VaadinIcons.PLUS);
		add.setStyleName("borderless");
		add.setId("add");
		layoutTop.addComponent(add);
		layoutTop.setComponentAlignment(add, Alignment.MIDDLE_RIGHT);

		addComponent(layoutTop);
	}

}
