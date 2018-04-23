package vn.fis.cms.configs;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setMaxInactiveInterval(30 * 60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
	}

}
