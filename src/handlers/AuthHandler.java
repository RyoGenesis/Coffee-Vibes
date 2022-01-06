package handlers;

import views.LoginAuthenticationFormView;

public class AuthHandler {
	
	public static AuthHandler authHandler = null;
	private String errorMessage;

	public AuthHandler() {
		errorMessage = "";
	}
	
	public static AuthHandler getInstance() {
		if (authHandler == null) {
			authHandler = new AuthHandler();
		}
		return authHandler;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void loginAuth() {
		//unfinished
	}
	
	public void logout() {
		//unfinished, temporary
		viewLoginForm();
	}
	
	public void viewLoginForm() {
		new LoginAuthenticationFormView();
	}

}
