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
	
	public boolean loginAuth(String username, String password) {
		
		if(username.equals("") || password.equals("")) {
			return false;
		}
		
		Employee authUser = EmployeeHandler.getInstance().getEmployee(username);
		
		if(authUser == null) return false;
		
		if(authUser.getPassword().equals(password)) {
			user = authUser;
			return true;
		}
		
		return false;
	}
	
	public void logout() {
		user = null;
		viewLoginForm();
	}
	
	public void viewHome() {
		new HomePageMenuView(user);
	}
	
	public void viewLoginForm() {
		new LoginAuthenticationFormView();
	}

}
