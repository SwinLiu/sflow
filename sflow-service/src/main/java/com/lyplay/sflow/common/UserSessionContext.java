package com.lyplay.sflow.common;


public class UserSessionContext implements AutoCloseable{

	private static final ThreadLocal<UserSession> current = new ThreadLocal<UserSession>();

	public UserSessionContext(UserSession userSession) {
        current.set(userSession);
    }
	
	public static void setUserSession(UserSession userSession) {
        current.set(userSession);
    }
	
	public static UserSession getUserSession() {
		return current.get();
	}
	
	public static void removeUserSession() {
		current.remove();
	}
	
	@Override
	public void close() throws Exception {
		current.remove();
	}
	
}
