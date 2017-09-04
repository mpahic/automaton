package com.cloudcog.automaton.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudcog.automaton.admin.authentication.AbstractLoginScreen;
import com.cloudcog.automaton.admin.authentication.AbstractLoginScreen.LoginListener;
import com.cloudcog.automaton.admin.authentication.AccessControl;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public abstract class AdminUI extends UI implements ViewDisplay {
	private static final long serialVersionUID = -1244163331948852755L;
	private static final Logger log = LoggerFactory.getLogger(AdminUI.class);

	private final AccessControl accessControl;
	private final Panel viewContainer = new Panel();

	public AdminUI(AccessControl accessControl) {
		this.accessControl = accessControl;

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
		
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();
		MainScreen mainScreen = new MainScreen(getAppName(), viewContainer);
		setContent(mainScreen);
		mainScreen.setViews(getViews());

		try {
			// getNavigator().addView("", getViews().get(0).getClass());
			// if (!getViews().isEmpty()) {
			// getNavigator().navigateTo(getViews().get(0).getViewName());
			// }
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@Override
	public void showView(View view) {
		viewContainer.setContent(view.getViewComponent());
		
	}

	protected abstract List<ViewType> getViews();

	protected String getAppName() {
		return "Admin";
	}
}
