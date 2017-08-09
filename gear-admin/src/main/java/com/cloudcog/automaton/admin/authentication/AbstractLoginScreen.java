package com.cloudcog.automaton.admin.authentication;

import java.io.Serializable;

import com.vaadin.server.Page;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;

/**
 * UI content when the user is not logged in yet.
 */
public abstract class AbstractLoginScreen extends CssLayout {
	private static final long serialVersionUID = 6942933886516499554L;

	private LoginListener loginListener;
    private AccessControl accessControl;

	public AbstractLoginScreen initialize(AccessControl accessControl, LoginListener loginListener) {
        this.loginListener = loginListener;
        this.accessControl = accessControl;
		return this;
    }

	protected boolean login(String username, String password) {
		if (accessControl.signIn(username, password)) {
            loginListener.loginSuccessful();
			return true;
        } else {
			showNotification(getFailedNotification());
			return false;
        }
    }

	protected abstract Notification getFailedNotification();

	protected void showNotification(Notification notification) {
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }

    public interface LoginListener extends Serializable {
        void loginSuccessful();
    }
}
