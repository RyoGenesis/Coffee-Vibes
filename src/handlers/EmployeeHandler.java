package handlers;

import java.util.List;

import models.Employee;
import views.EmployeeManagementFormView;

public class EmployeeHandler {

	public static EmployeeHandler employeeHandler = null;
	public Employee employee;
	private String errorMessage;

	public EmployeeHandler() {
		employee = new Employee();
		errorMessage = "";
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
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	//**UNFINISHED**
	public Employee getEmployee(String username) {
		return null;
	}
	
	public Employee insertEmployee(String name, int positionID, int salary, String username, String password) {
		return null;
	}
	
	public Employee updateEmployee(int employeeID, String name, int salary, String username, String password) {
		return null;
	}
	
	public boolean fireEmployee(int employeeID) {
		return true;
	}
	
	public void viewEmployeeManagementForm() {
		new EmployeeManagementFormView();
	}
	//**UNFINISHED**

}
