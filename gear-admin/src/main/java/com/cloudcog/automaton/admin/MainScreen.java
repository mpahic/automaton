package com.cloudcog.automaton.admin;

import java.util.List;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class MainScreen extends HorizontalLayout {
	private static final long serialVersionUID = 3567938456892678351L;

	private Menu menu;

	public MainScreen(UI ui, SpringNavigator navigationManager, String appName, List<ViewType> views) {

        setSpacing(false);
        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

		navigationManager.init(ui, viewContainer);
		navigationManager.setErrorView(ErrorView.class);
		menu = new Menu(appName, navigationManager);

		views.forEach(view -> {
			menu.addView(view.getViewClass(), view.getViewName(), view.getViewCaption(),
					view.getIcon());
		});

		navigationManager.addViewChangeListener(viewChangeListener);

        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    ViewChangeListener viewChangeListener = new ViewChangeListener() {
		private static final long serialVersionUID = 3029434924625704872L;

		@Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }

    };
}
