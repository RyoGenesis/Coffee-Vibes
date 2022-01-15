package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import handlers.EmployeeHandler;
import handlers.PositionHandler;
import handlers.ProductHandler;
import models.Employee;
import models.Position;

public class EmployeeManagementFormView extends JFrame implements ActionListener{

	private JLabel idLbl, nameLbl, positionLbl, salaryLbl, usernameLbl, passwordLbl;
	private JTextField idTxt, nameTxt, positionTxt, salaryTxt, usernameTxt;
	private JPasswordField passwordTxt;
	private JButton insertBtn, updateBtn, deleteBtn;
	private JTable table;
	private DefaultTableModel dtm;
	private JPanel contentPnl, formPnl, buttonPnl;
	private JScrollPane tableScroll;
	private String user;
	Object[] columns = {"ID", "Name", "Position", "Salary", "Username", "Password"};
	
	private void init() {
		makeTable();
		makeForm();
		makeButton();
		
		contentPnl = new JPanel(new BorderLayout());
		contentPnl.add(BorderLayout.NORTH, tableScroll);
		contentPnl.add(BorderLayout.CENTER, formPnl);
		contentPnl.add(BorderLayout.SOUTH, buttonPnl);
		add(contentPnl);
	}
	
	private void makeTable() {
		dtm = new DefaultTableModel(columns, 0);
		table = new JTable(dtm);
		tableScroll = new JScrollPane(table);
		tableScroll.setPreferredSize(new Dimension(200, 200));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int id = (int)table.getValueAt(row, 0);
				String name = (String)table.getValueAt(row, 1);
				String position = (String)table.getValueAt(row, 2);
				int salary = (int)table.getValueAt(row, 3);
				String username = (String)table.getValueAt(row, 4);
				String password = (String)table.getValueAt(row, 5);
				
				idTxt.setText(id + "");
				nameTxt.setText(name);
				positionTxt.setText(position);
				salaryTxt.setText(salary + "");
				usernameTxt.setText(username);
				passwordTxt.setText(password);
			}
		});
	}
	
	private void makeForm() {
		idLbl = new JLabel("ID");
		nameLbl = new JLabel("Name");
		positionLbl = new JLabel("Position");
		salaryLbl = new JLabel("Salary");
		usernameLbl = new JLabel("Username");
		passwordLbl = new JLabel("Password");
		
		idTxt = new JTextField();
		nameTxt = new JTextField();
		positionTxt = new JTextField();
		salaryTxt = new JTextField();
		usernameTxt = new JTextField();
		passwordTxt = new JPasswordField();
		
		formPnl = new JPanel(new GridLayout(5, 2));
		formPnl.add(idLbl);
		formPnl.add(idTxt);
		formPnl.add(nameLbl);
		formPnl.add(nameTxt);
		formPnl.add(positionLbl);
		formPnl.add(positionTxt);
		formPnl.add(salaryLbl);
		formPnl.add(salaryTxt);
		formPnl.add(usernameLbl);
		formPnl.add(usernameTxt);
		
		formPnl.add(passwordLbl);
		formPnl.add(passwordTxt);	
	}
	
	private void makeButton() {
		buttonPnl = new JPanel(new GridLayout(1, 4));
		
		if(user.equalsIgnoreCase("Human Resource Department")) {
			insertBtn = new JButton("Insert");
			updateBtn = new JButton("Update");
			
			insertBtn.addActionListener(this);
			updateBtn.addActionListener(this);
			
			buttonPnl.add(insertBtn);
			buttonPnl.add(updateBtn);			
		}
		
		deleteBtn.addActionListener(this);
		deleteBtn = new JButton("Fire");
		buttonPnl.add(deleteBtn);	
	}

	private void loadData() {
		dtm = new DefaultTableModel(columns, 0);
		List<Employee> employees =  EmployeeHandler.getInstance().getAllEmployees();
		PositionHandler positionHandler = PositionHandler.getInstance();
		for (Employee employee : employees) {
			int id = employee.getEmployeeID();
			String name = employee.getName();
			Position position = positionHandler.getPosition(employee.getPositionID());
			String positionName = position.getName();
			int salary = employee.getSalary();
			String username = employee.getUsername();
			String password = employee.getPassword();
			dtm.addRow(new Object[] {id, name, positionName, salary, username, password});
		}
		table.setModel(dtm);
	}
	
	public EmployeeManagementFormView(String user) {
		this.user = user;
		init();
		setSize(500,500);
		setTitle("Coffee Vibes - Employee Management");
		loadData();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertBtn) {
			insertEmployee();
		}
		else if(e.getSource() == updateBtn) {
			updateEmployee();
		}
		else if(e.getSource() == deleteBtn) {
			fireEmployee();
		}
	}
	
	private void insertEmployee() {
		String name = nameTxt.getText();
		String position = positionTxt.getText();
		String salary = salaryTxt.getText();
		String username = usernameTxt.getText(); 
		String password = new String(passwordTxt.getPassword());
		
		Employee employee = EmployeeHandler.getInstance().insertEmployee(name, position, salary, username, password);
		JOptionPane.showMessageDialog(this, EmployeeHandler.getInstance().getMessage());
		if(employee != null) {
			loadData();
		}	
	}
	
	private void updateEmployee() {
		String id = idTxt.getText();
		String name = nameTxt.getText();
		String salary = salaryTxt.getText();
		String username = usernameTxt.getText(); 
		String password = new String(passwordTxt.getPassword());
		
		int option = JOptionPane.showConfirmDialog(this, "Confirm Update Employee?");
		if(option == JOptionPane.YES_OPTION) {
			Employee employee = EmployeeHandler.getInstance().updateEmployee(id, name, salary, username, password);
			JOptionPane.showMessageDialog(this, ProductHandler.getInstance().getMessage());
			if(employee != null) {
				loadData();
			}
		}
		
	}

	private void fireEmployee() {
		String id = idTxt.getText();
		
		int option = JOptionPane.showConfirmDialog(this, "Confirm Fire Employee?");
		if(option == JOptionPane.YES_OPTION) {
			boolean fired = EmployeeHandler.getInstance().fireEmployee(id);
			JOptionPane.showMessageDialog(this, ProductHandler.getInstance().getMessage());
			if(fired) {
				loadData();
			}
		}
	}
}
