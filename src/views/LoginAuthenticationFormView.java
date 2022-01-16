package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import handlers.AuthHandler;

public class LoginAuthenticationFormView extends JFrame implements ActionListener{
	
	JPanel contentPnl, buttonPnl;
	JButton baristaBtn, productAdminBtn, managerBtn, hrdBtn;
	JLabel textLbl;
	
	private void init() {
		textLbl = new JLabel("Login As");
		textLbl.setHorizontalAlignment(JLabel.CENTER);
		
		baristaBtn = new JButton("Barista");
		baristaBtn.addActionListener(this);
		productAdminBtn = new JButton("Product Admin");
		productAdminBtn.addActionListener(this);
		managerBtn = new JButton("Manager");
		managerBtn.addActionListener(this);
		hrdBtn = new JButton("Human Resource Department");
		hrdBtn.addActionListener(this);
		
		GridLayout gridLayout = new GridLayout(4, 1);
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

	public LoginAuthenticationFormView() {
		init();
		setSize(500,500);
		setTitle("Coffee Vibes - Role");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == baristaBtn) {
			AuthHandler.getInstance().setUser("barista");
		}
		else if(e.getSource() == productAdminBtn) {
			AuthHandler.getInstance().setUser("productAdmin");
		}
		else if(e.getSource() == managerBtn) {
			AuthHandler.getInstance().setUser("manager");
		}
		else if(e.getSource() == hrdBtn) {
			AuthHandler.getInstance().setUser("hrd");
		}
		AuthHandler.getInstance().viewHome();
		dispose();
	}
}
