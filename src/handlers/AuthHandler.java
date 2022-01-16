package handlers;

import models.Employee;
import views.HomePageMenuView;
import views.LoginAuthenticationFormView;

public class AuthHandler {
	
	public static AuthHandler authHandler = null;
	private static Employee user = null;

	public AuthHandler() {
		user = new Employee();
	}
	
	public Employee getAuthUser() {
		return user;
	}
	
	public String getAuthUserName() {
		if (user!=null) {
			return user.getName();			
		}
		return "";
	}
	
	public int getAuthUserPosition() {
		if (user!=null) {
			return user.getPositionID();			
		}
		return 0;
	}

	public void setAuthUser(Employee user) {
		AuthHandler.user = user;
	}

	public static AuthHandler getInstance() {
		if (authHandler == null) {
			authHandler = new AuthHandler();
		}
		return authHandler;
	}
	
	public void loginAuth(String username, String password) {
		
	}
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
