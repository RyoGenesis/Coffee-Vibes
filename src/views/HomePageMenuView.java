package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import handlers.AuthHandler;
import handlers.CartHandler;
import handlers.ProductHandler;
import handlers.TransactionHandler;
import handlers.VoucherHandler;
import models.Employee;

public class HomePageMenuView extends JFrame implements ActionListener{
	
	JPanel contentPnl, buttonPnl;
	JButton logoutBtn;
	JButton addToCartBtn, checkoutBtn, cartManagementBtn;
	JButton manageProductBtn, manageVoucherBtn;
	JLabel textLbl;
	private Employee user;
	
	private void init() {
		logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(this);
		//if Barista
		if(user.getPositionID() == 4) {
			textLbl = new JLabel("Barista");
			textLbl.setHorizontalAlignment(JLabel.CENTER);
			
			addToCartBtn = new JButton("Add to Cart");
			addToCartBtn.addActionListener(this);
			checkoutBtn = new JButton("Checkout Transaction");
			checkoutBtn.addActionListener(this);
			cartManagementBtn = new JButton("Cart Management");
			cartManagementBtn.addActionListener(this);
			
			GridLayout gridLayout = new GridLayout(5, 1);
			gridLayout.setVgap(50);
			buttonPnl = new JPanel(gridLayout);
			buttonPnl.add(addToCartBtn);
			buttonPnl.add(checkoutBtn);
			buttonPnl.add(cartManagementBtn);
			buttonPnl.add(logoutBtn);
			
			BorderLayout borderLayout = new BorderLayout();
			contentPnl = new JPanel(borderLayout);
			contentPnl.add(BorderLayout.CENTER, textLbl);
			contentPnl.add(BorderLayout.SOUTH, buttonPnl);
			add(contentPnl);
		}
		//if Product Admin
		else if(user.getPositionID() == 1) {
			textLbl = new JLabel("Product Admin");
			textLbl.setHorizontalAlignment(JLabel.CENTER);
			
			manageProductBtn = new JButton("Manage Product");
			manageProductBtn.addActionListener(this);
			manageVoucherBtn = new JButton("Manage Voucher");
			manageVoucherBtn.addActionListener(this);
			
			GridLayout gridLayout = new GridLayout(5, 1);
			gridLayout.setVgap(50);
			buttonPnl = new JPanel(gridLayout);
			buttonPnl.add(manageProductBtn);
			buttonPnl.add(manageVoucherBtn);
			buttonPnl.add(logoutBtn);
			
			BorderLayout borderLayout = new BorderLayout();
			contentPnl = new JPanel(borderLayout);
			contentPnl.add(BorderLayout.CENTER, textLbl);
			contentPnl.add(BorderLayout.SOUTH, buttonPnl);
			add(contentPnl);
		}
		//if Manager
		else if(user.getPositionID() == 2) {
			//ISI
		}
		//if Human Resource Manager
		else if(user.getPositionID() == 3) {
			//ISI
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
			AuthHandler.getInstance().viewLoginForm();
			dispose();
		}
		if(e.getSource() == addToCartBtn) {
			ProductHandler.getInstance().viewProductManagementForm();
		}
		if(CartHandler.getInstance().getCart().isEmpty() && (e.getSource() == checkoutBtn || e.getSource() == cartManagementBtn)) {
			JOptionPane.showMessageDialog(this, "Your cart is empty!\nPlease do Add to Cart first!");
		}
		else if(!CartHandler.getInstance().getCart().isEmpty()) {
			if(e.getSource() == checkoutBtn) {
				TransactionHandler.getInstance().viewTransactionManagementForm();
			}
			else if(e.getSource() == cartManagementBtn) {
				CartHandler.getInstance().viewCheckoutForm();
			}
		}
		
		if(e.getSource() == manageProductBtn) {
			ProductHandler.getInstance().viewProductManagementForm();
		}
		if(e.getSource() == manageVoucherBtn) {
			VoucherHandler.getInstance().viewVoucherManagementForm();
		}
	}

}
