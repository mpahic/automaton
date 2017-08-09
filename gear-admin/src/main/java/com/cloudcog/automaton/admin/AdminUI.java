package com.cloudcog.automaton.admin;

import java.util.List;

import com.cloudcog.automaton.admin.authentication.AbstractLoginScreen;
import com.cloudcog.automaton.admin.authentication.AbstractLoginScreen.LoginListener;
import com.cloudcog.automaton.admin.authentication.AccessControl;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public abstract class AdminUI extends UI {
	private static final long serialVersionUID = -1244163331948852755L;

	private final AccessControl accessControl;
	private final SpringNavigator navigationManager;

	public AdminUI(AccessControl accessControl, SpringNavigator navigationManager) {
		this.accessControl = accessControl;
		this.navigationManager = navigationManager;
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Responsive.makeResponsive(this);
		setLocale(vaadinRequest.getLocale());
		getPage().setTitle(getAppName());
		if (!accessControl.isUserSignedIn()) {
			setContent(getLoginScreen().initialize(accessControl, new LoginListener() {
				private static final long serialVersionUID = 7699737458371349218L;

				@Override
				public void loginSuccessful() {
					showMainView();
				}
			}));
		} else {
			showMainView();
		}
	}

	public AccessControl getAccessControl() {
		return accessControl;
	}

	protected abstract AbstractLoginScreen getLoginScreen();

	protected void showMainView() {
		addStyleName(ValoTheme.UI_WITH_MENU);
		setContent(new MainScreen(AdminUI.this, navigationManager, getAppName(), getViews()));

		if (getNavigator().getState().isEmpty() && !getViews().isEmpty()) {
			getNavigator().navigateTo(getViews().get(0).getViewName());
		} else {
			getNavigator().navigateTo(getNavigator().getState());
		}
	}

	protected abstract List<ViewType> getViews();

	protected String getAppName() {
		return "Admin";
	}
}
