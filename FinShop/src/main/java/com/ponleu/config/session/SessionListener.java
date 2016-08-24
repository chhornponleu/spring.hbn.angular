package com.ponleu.config.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Session " + se.getSession().getId() + " created");
		se.getSession().setMaxInactiveInterval(-1);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Session " + se.getSession().getId() + " has been destroyed");
	}

}
