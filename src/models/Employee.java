package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Employee {

	private int employeeID;
	private int positionID;
	private String name;
	private int salary;
	private String username;
	private String password;
	
	public Employee(int employeeID, int positionID, String name, int salary, String username, String password) {
		super();
		this.employeeID = employeeID;
		this.positionID = positionID;
		this.name = name;
		this.salary = salary;
		this.username = username;
		this.password = password;
	}
	
	public Employee() {}

	public int getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	public int getPositionID() {
		return positionID;
	}
	
	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	private Employee map(ResultSet rs) {
		try {
			// not finished, placeholder only
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			
			// not finished, placeholder only
			return new Employee();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//**UNFINISHED**
	public Employee insertEmployee() {
		return null;
	}
	
	public List<Employee> getAllEmployees() {
		return null;
	}
	
	public Employee getEmployee(String username) {
		return null;
	}
	
	public Employee updateEmployee() {
		return null;
	}
	
	public boolean fireEmployee(int ID) {
		return false;
	}
	//**UNFINISHED**
}
