package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePageMenuView extends JFrame implements ActionListener{
	
	JPanel contentPnl, buttonPnl;
	JButton addToCartBtn, checkoutBtn, cartManagementBtn;
	JLabel textLbl;
	private String user;
	
	private void init() {
		if(user.equalsIgnoreCase("barista")) {
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
			buttonPnl.add(baristaBtn);
			buttonPnl.add(productAdminBtn);
			buttonPnl.add(managerBtn);
			buttonPnl.add(hrdBtn);
			
			BorderLayout borderLayout = new BorderLayout();
			contentPnl = new JPanel(borderLayout);
			contentPnl.add(BorderLayout.CENTER, textLbl);
			contentPnl.add(BorderLayout.SOUTH, buttonPnl);
			add(contentPnl);
		}
		else if(user.equalsIgnoreCase("productAdmin")) {
			
		}
		else if(user.equalsIgnoreCase("manager")) {
			
		}
		else if(user.equalsIgnoreCase("hrd")) {
			
		}
	}
	
	public HomePageMenuView(String user) {
		this.user = user;
		//initComp();
		//addComp();
		setSize(500,500);
		setTitle("Coffee Vibes - Product Management");
		//loadData();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
