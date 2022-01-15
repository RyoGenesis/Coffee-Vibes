package handlers;

import java.util.List;

import models.Employee;
import models.Position;
import models.Product;
import views.EmployeeManagementFormView;

public class EmployeeHandler {

	public static EmployeeHandler employeeHandler = null;
	public Employee employee;
	private String message;

	public EmployeeHandler() {
		employee = new Employee();
		message = "";
	}
	
	public static EmployeeHandler getInstance() {
		if (employeeHandler == null) {
			employeeHandler = new EmployeeHandler();
		}
		return employeeHandler;
	}
	
	public List<Employee> getAllEmployees(){
		return employee.getAllEmployees();
	}
	
	public String getMessage() {
		return message;
	}

	public Employee getEmployee(String username) {
		return employee.getEmployee(username);
	}
	
	public Employee insertEmployee(String name, String position, String salary, String username, String password) {
		message = "";
		
		if(name.equals("")) {
			message += "Name cannot be empty! ";
		}
		
		Position empPosition = new Position();
		if(position.equals("")) {
			message += "Employee position cannot be empty! ";
		}
		else {
			empPosition = empPosition.getPosition(convertPositionToInt(position));
			if(empPosition == null) {
				message += "Employee position invalid! ";
			}
		}
		
		int intSalary = 0;
		if(salary.equals("")) {
			message += "Salary cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intSalary = Integer.parseInt(salary);
			} catch (NumberFormatException e) {
				message += "Salary must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intSalary < 1) {
					message += "Salary cannot be lower than one! ";
				}
			}
		}
		
		if(username.equals("")) {
			message += "Username cannot be empty! ";
		}
		else {
			Employee emp = employee.getEmployee(username);
			if(emp != null) {
				message += "Username is already used!";
			}
		}
		
		if(password.equals("")) {
			message += "Password cannot be empty! ";
		}
		
		if(message.equals("")) {
			employee = new Employee(empPosition.getPositionID(), name, intSalary, username, password);
			message = "Successfully Inserted!";
			return employee.insertEmployee();
		}
		return null;
	}
	
	private int convertPositionToInt(String position) {
		int pos=0;
		
		switch (position.toUpperCase()) {
			case "PRODUCT ADMIN":
				pos = 1;
				break;
			case "MANAGER":
				pos = 2;
				break;
			case "HUMAN RESOURCE DEPARTMENT":
				pos = 3;
				break;
			case "BARISTA":
				pos = 4;
				break;	
			default:
				break;
		}
		return pos;
	}

	public Employee updateEmployee(String employeeID, String name, String salary, String username, String password) {
		message = "";
		int empID = 0;
		if(employeeID.equals("")) {
			message += "ID cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				empID = Integer.parseInt(employeeID);
			} catch (NumberFormatException e) {
				message += "ID must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				Employee emp = employee.getEmployeeById(empID);
				if(emp == null) {
					message += "Employee does not exist! ";
				}
			}
		}
		
		if(name.equals("")) {
			message += "Name cannot be empty! ";
		}
		
		int intSalary = 0;
		if(salary.equals("")) {
			message += "Salary cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intSalary = Integer.parseInt(salary);
			} catch (NumberFormatException e) {
				message += "Salary must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intSalary < 1) {
					message += "Salary cannot be lower than one! ";
				}
			}
		}
		
		if(username.equals("")) {
			message += "Username cannot be empty! ";
		}
		
		if(password.equals("")) {
			message += "Password cannot be empty! ";
		}
		
		if(message.equals("")) {
			employee = new Employee(empID, 0, name, intSalary, username, password);
			message = "Employee Update Successful!";
			return employee.updateEmployee();
		}
		return null;
	}
	
	public boolean deleteEmployee(String employeeID) {
		message = "";
		int empID = 0;
		if(employeeID.equals("")) {
			message += "ID cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				empID = Integer.parseInt(employeeID);
			} catch (NumberFormatException e) {
				message += "ID must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				Employee emp = employee.getEmployeeById(empID);
				if(emp == null) {
					message += "Employee does not exist! ";
				}
			}
		}
		
		if(message.equals("")) {
			message = "Employee Successfully Deleted/Fired!";
			return employee.fireEmployee(empID);
		}
		return false;
	}
	
	public boolean fireEmployee(String employeeID) {
		message = "";
		int empID = 0;
		if(employeeID.equals("")) {
			message += "ID cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				empID = Integer.parseInt(employeeID);
			} catch (NumberFormatException e) {
				message += "ID must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				Employee emp = employee.getEmployeeById(empID);
				if(emp == null) {
					message += "Employee does not exist! ";
				}
			}
		}
		
		if(message.equals("")) {
			message = "Employee Successfully Deleted/Fired!";
			return employee.fireEmployee(empID);
		}
		return false;
	}
	
	public void viewEmployeeManagementForm(String employeePos) {
		new EmployeeManagementFormView(employeePos);
	}

}
