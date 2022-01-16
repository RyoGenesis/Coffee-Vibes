package handlers;

import views.HomePageMenuView;
import views.LoginAuthenticationFormView;

public class AuthHandler {
	
	public static AuthHandler authHandler = null;
	private String user;

	public AuthHandler() {
		
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public static AuthHandler getInstance() {
		if (authHandler == null) {
			authHandler = new AuthHandler();
		}
		return authHandler;
	}
	
//	public void loginAuth() {
//		//unfinished
//	}
//	
//	public void logout() {
//		//unfinished, temporary
//		viewLoginForm();
//	}
	public void viewHome() {
		new HomePageMenuView(user);
	}
	
	public void viewLoginForm() {
		new LoginAuthenticationFormView();
	}

}
