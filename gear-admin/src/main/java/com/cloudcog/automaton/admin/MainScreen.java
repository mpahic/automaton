package com.cloudcog.automaton.admin;

import java.util.List;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

public class MainScreen extends HorizontalLayout {
	private static final long serialVersionUID = 3567938456892678351L;

	private Menu menu;

	public MainScreen(String appName, Panel viewContainer) {

        setSpacing(false);
        setStyleName("main-screen");
		menu = new Menu(appName);
		addComponent(menu);
		addComponentsAndExpand(viewContainer);
		setSizeFull();


    }

	public void setViews(List<ViewType> views) {
		views.forEach(view -> {
			menu.addView(view.getViewClass(), view.getViewName(), view.getViewCaption(),
					view.getIcon());
		});
	}

}
