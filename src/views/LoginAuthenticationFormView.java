package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import handlers.AuthHandler;

public class LoginAuthenticationFormView extends JFrame implements ActionListener{
	
	JPanel contentPnl, buttonPnl, formPnl, wrapperPnl;
	JButton baristaBtn, productAdminBtn, managerBtn, hrdBtn, loginBtn;
	JTextField usernameTxt;
	JPasswordField passwordTxt;
	JLabel textLbl, usernameLbl, passwordLbl;
	
	private void init() {
		textLbl = new JLabel("Login To Coffee Vibes");
		textLbl.setHorizontalAlignment(JLabel.CENTER);
		
		makeLoginForm();
		
		loginBtn = new JButton("Login");
		loginBtn.setPreferredSize(new Dimension(100,30));
		loginBtn.addActionListener(this);
		
		buttonPnl = new JPanel(new FlowLayout());
		buttonPnl.add(loginBtn);
		
		BorderLayout borderLayout = new BorderLayout(0,30);
		contentPnl = new JPanel(borderLayout);
		contentPnl.setPreferredSize(new Dimension(250,170));
		contentPnl.add(BorderLayout.NORTH, textLbl);
		contentPnl.add(BorderLayout.CENTER, formPnl);
		contentPnl.add(BorderLayout.SOUTH, buttonPnl);
		
		wrapperPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		wrapperPnl.add(contentPnl);
		wrapperPnl.setPreferredSize(new Dimension(350,350));
		wrapperPnl.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		add(wrapperPnl);
	}

	private void makeLoginForm() {
		
		usernameLbl = new JLabel("Username");
		passwordLbl = new JLabel("Password");
		usernameTxt = new JTextField();
		passwordTxt = new JPasswordField();
			
		formPnl = new JPanel(new GridLayout(2, 2));			
		formPnl.add(usernameLbl);
		formPnl.add(usernameTxt);
		formPnl.add(passwordLbl);
		formPnl.add(passwordTxt);
		
	}

	public LoginAuthenticationFormView() {
		init();
		setSize(500,500);
		setTitle("Coffee Vibes - Login");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginBtn) {
			String username = usernameTxt.getText();
			String password = new String(passwordTxt.getPassword());
			
			boolean authenticated = AuthHandler.getInstance().loginAuth(username, password);
			if(authenticated) {
				AuthHandler.getInstance().viewHome();
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid username and password combination!");
			}
		}
	}
}
