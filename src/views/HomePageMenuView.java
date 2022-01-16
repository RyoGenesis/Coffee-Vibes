package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import handlers.AuthHandler;
import handlers.CartHandler;
import handlers.EmployeeHandler;
import handlers.ProductHandler;
import handlers.TransactionHandler;
import handlers.VoucherHandler;
import models.Employee;

public class HomePageMenuView extends JFrame implements ActionListener{
	
	JPanel contentPnl, buttonPnl;
	JButton logoutBtn;
	JButton addToCartBtn, checkoutBtn, cartManagementBtn;
	JButton manageProductBtn, manageVoucherBtn;
	JButton manageTransactionBtn, manageEmployeeBtn;
	JLabel textLbl;
	private Employee user;
	
	private void init() {
		logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(this);
		
		if (user != null) {
			GridLayout gridLayout = new GridLayout(5, 1);
			gridLayout.setVgap(50);
			buttonPnl = new JPanel(gridLayout);
			
			//if Barista
			if(user.getPositionID() == 4) {
				textLbl = new JLabel("Hello, " + user.getName() +" (Barista)");
				textLbl.setHorizontalAlignment(JLabel.CENTER);
				
				addToCartBtn = new JButton("Add to Cart");
				addToCartBtn.addActionListener(this);
				checkoutBtn = new JButton("Checkout Transaction");
				checkoutBtn.addActionListener(this);
				cartManagementBtn = new JButton("Cart Management");
				cartManagementBtn.addActionListener(this);
				
				buttonPnl.add(addToCartBtn);
				buttonPnl.add(checkoutBtn);
				buttonPnl.add(cartManagementBtn);
			}
			//if Product Admin
			else if(user.getPositionID() == 1) {
				textLbl = new JLabel("Hello, " + user.getName() +" (Product Admin)");
				textLbl.setHorizontalAlignment(JLabel.CENTER);
				
				manageProductBtn = new JButton("Manage Product");
				manageProductBtn.addActionListener(this);
				manageVoucherBtn = new JButton("Manage Voucher");
				manageVoucherBtn.addActionListener(this);
				
				buttonPnl.add(manageProductBtn);
				buttonPnl.add(manageVoucherBtn);
			}
			//if Manager
			else if(user.getPositionID() == 2) {
				textLbl = new JLabel("Hello, " + user.getName() +" (Manager)");
				textLbl.setHorizontalAlignment(JLabel.CENTER);
				
				manageTransactionBtn = new JButton("View Transaction");
				manageTransactionBtn.addActionListener(this);
				manageEmployeeBtn = new JButton("Manage Employee");
				manageEmployeeBtn.addActionListener(this);
	
				buttonPnl.add(manageTransactionBtn);
				buttonPnl.add(manageEmployeeBtn);	
			}
			//if Human Resource Manager (user Position ID = 3
			else {
				textLbl = new JLabel("Hello, " + user.getName() +" (Human Resource Department)");
				textLbl.setHorizontalAlignment(JLabel.CENTER);

				manageEmployeeBtn = new JButton("Manage Employee");
				manageEmployeeBtn.addActionListener(this);

				buttonPnl.add(manageEmployeeBtn);	
			}
			
			buttonPnl.add(logoutBtn);
			
			BorderLayout borderLayout = new BorderLayout();
			contentPnl = new JPanel(borderLayout);
			contentPnl.add(BorderLayout.CENTER, textLbl);
			contentPnl.add(BorderLayout.SOUTH, buttonPnl);
			contentPnl.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
			add(contentPnl);
		}
	}
	
	public HomePageMenuView(Employee user) {
		this.user = user;
		init();
		setSize(500,500);
		setTitle("Coffee Vibes - Home");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logoutBtn) {
			AuthHandler.getInstance().logout();
			dispose();
		}
		if(e.getSource() == addToCartBtn) {
			ProductHandler.getInstance().viewProductManagementForm();
			dispose();
		}
		if(CartHandler.getInstance().getCart().isEmpty() && (e.getSource() == checkoutBtn || e.getSource() == cartManagementBtn)) {
			JOptionPane.showMessageDialog(this, "Your cart is empty!\nPlease do Add to Cart first!");
		}
		else if(!CartHandler.getInstance().getCart().isEmpty()) {
			if(e.getSource() == checkoutBtn) {
				TransactionHandler.getInstance().viewTransactionManagementForm();
				dispose();
			}
			else if(e.getSource() == cartManagementBtn) {
				CartHandler.getInstance().viewCheckoutForm();
				dispose();
			}
		}
		
		if(e.getSource() == manageProductBtn) {
			ProductHandler.getInstance().viewProductManagementForm();
			dispose();
		}
		if(e.getSource() == manageVoucherBtn) {
			VoucherHandler.getInstance().viewVoucherManagementForm();
			dispose();
		}
		
		if(e.getSource() == manageTransactionBtn) {
			TransactionHandler.getInstance().viewTransactionManagementForm();
			dispose();
		}
		
		if(e.getSource() == manageEmployeeBtn) {
			EmployeeHandler.getInstance().viewEmployeeManagementForm();
			dispose();
		}
	}

}
