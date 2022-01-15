package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connect.Connect;

public class Employee {

	private int employeeID;
	private int positionID;
	private String name;
	private int salary;
	private String username;
	private String password;
	private String table = "employee";
	
	public Employee(int employeeID, int positionID, String name, int salary, String username, String password) {
		super();
		this.employeeID = employeeID;
		this.positionID = positionID;
		this.name = name;
		this.salary = salary;
		this.username = username;
		this.password = password;
	}
	
	public Employee(int positionID, String name, int salary, String username, String password) {
		super();
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

	public Employee insertEmployee() {
		Connect con = Connect.getConnection();
		try {
			String query = String.format("INSERT INTO %s VALUES(default,?,?,?,?,?) ", this.table);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, positionID);
			ps.setString(2, name);
			ps.setInt(3, salary);
			ps.setString(4, username);
			ps.setString(5, password);
			ps.execute();
			return getEmployee(username);
		} catch (SQLException e) {
			
		}
		return null;
	}
	
	public List<Employee> getAllEmployees() {
		Connect con = Connect.getConnection();
		String query = "SELECT * FROM " + this.table;
		List<Employee> employees = new Vector<>();
		try {
			ResultSet rs = con.executeQuery(query);
			while (rs.next()) {
				int empId = rs.getInt("id");
				int posId = rs.getInt("PositionID");
				String name = rs.getString("name");
				int salary = rs.getInt("salary");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Employee employee = new Employee(empId, posId, name, salary, username, password);
				employees.add(employee);
			}
		} catch (SQLException e) {
			
		}
		return employees;
	}
	
	public Employee getEmployee(String username) {
		Connect con = Connect.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM "+ this.table + " WHERE Username = ?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int empId = rs.getInt("id");
				int posId = rs.getInt("PositionID");
				String name = rs.getString("name");
				int salary = rs.getInt("salary");
				String empUsername = rs.getString("username");
				String password = rs.getString("password");
				return new Employee(empId, posId, name, salary, empUsername, password);
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public Employee getEmployeeById(int id) {
		Connect con = Connect.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM "+ this.table + " WHERE id = ?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int empId = rs.getInt("id");
				int posId = rs.getInt("PositionID");
				String name = rs.getString("name");
				int salary = rs.getInt("salary");
				String empUsername = rs.getString("username");
				String password = rs.getString("password");
				return new Employee(empId, posId, name, salary, empUsername, password);
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public Employee updateEmployee() {
		Connect con = Connect.getConnection();
		try {
			String query = String.format("UPDATE %s SET name = ?, salary = ?, username = ?, password = ? WHERE id = ?", this.table);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(5, employeeID);
			ps.setString(1, name);
			ps.setInt(2, salary);
			ps.setString(3, username);
			ps.setString(4, password);
			if(ps.executeUpdate() == 1)
				return getEmployee(username);
		} catch (SQLException e) {
			
		}
		return null;
	}
	
	public boolean fireEmployee(int ID) {
		Connect con =  Connect.getConnection();
		try {
			String query = String.format("DELETE FROM %s WHERE id = ?", this.table);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, ID);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			
		}
		return false;
	}
}
