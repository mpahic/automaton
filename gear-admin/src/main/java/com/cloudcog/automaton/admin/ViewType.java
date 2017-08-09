package com.cloudcog.automaton.admin;

import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

public class ViewType {
	private String viewName;
	private String viewCaption;
	private Class<? extends View> viewClass;
	private Resource icon;

	public ViewType(String viewName, String viewCaption, Class<? extends View> viewClass, Resource icon) {
		super();
		this.viewName = viewName;
		this.viewCaption = viewCaption;
		this.viewClass = viewClass;
		this.icon = icon;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getViewCaption() {
		return viewCaption;
	}

	public void setViewCaption(String viewCaption) {
		this.viewCaption = viewCaption;
	}

	public Class<? extends View> getViewClass() {
		return viewClass;
	}

	public void setViewClass(Class<? extends View> viewClass) {
		this.viewClass = viewClass;
	}

	public Resource getIcon() {
		return icon;
	}

	public void setIcon(Resource icon) {
		this.icon = icon;
	}


}
