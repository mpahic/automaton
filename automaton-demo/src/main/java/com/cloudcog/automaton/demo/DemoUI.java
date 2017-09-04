package com.cloudcog.automaton.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.cloudcog.automaton.admin.AdminUI;
import com.cloudcog.automaton.admin.ViewType;
import com.cloudcog.automaton.admin.authentication.AbstractLoginScreen;
import com.cloudcog.automaton.admin.view.user.BasicAccessControl;
import com.cloudcog.automaton.admin.view.user.UserAdminView;
import com.cloudcog.automaton.demo.view.AboutView;
import com.cloudcog.automaton.todo.TodoView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Viewport;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;

@Viewport("width=device-width,initial-scale=1.0,user-scalable=no")
@Theme("default")
@SpringUI
@SpringViewDisplay
public class DemoUI extends AdminUI {

	@Value("${app.name}")
	private String appName;

	@Autowired
	public DemoUI(BasicAccessControl basicAccessControl) {
		super(basicAccessControl);
	}

	@Override
	protected List<ViewType> getViews() {
		List<ViewType> views = new ArrayList<>();
		if (getAccessControl().isUserInRole("admin")) {
			views.add(new ViewType(UserAdminView.VIEW_NAME, UserAdminView.VIEW_NAME, UserAdminView.class,
					VaadinIcons.USERS));
		}
		views.add(new ViewType(TodoExtendedView.VIEW_NAME, TodoExtendedView.VIEW_NAME, TodoExtendedView.class,
				VaadinIcons.TASKS));
		views.add(new ViewType(AboutView.VIEW_NAME, AboutView.VIEW_NAME, AboutView.class, VaadinIcons.INFO_CIRCLE_O));
		return views;
	}

	@Override
	protected String getAppName() {
		return appName;
	}

	@Override
	protected AbstractLoginScreen getLoginScreen() {
		return new LoginScreen();
	}

	@UIScope
	@SpringView(name = TodoView.VIEW_NAME)
	class TodoExtendedView extends TodoView {
	}

}
