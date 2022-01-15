package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import handlers.CartHandler;

public class AddToCartFormView extends JFrame implements ActionListener{

	JLabel qtyLbl;
	JTextField qtyTxt;
	JButton confirmBtn;
	JPanel contentPnl, formPnl, btnPnl; ;
	
	private void init() {
		qtyLbl = new JLabel("Quantity");
		qtyTxt = new JTextField();
		confirmBtn = new JButton("Confirm");
		confirmBtn.addActionListener(this);
		
		formPnl = new JPanel(new GridLayout(1, 2));
		formPnl.add(qtyLbl);
		formPnl.add(qtyTxt);
		
		btnPnl = new JPanel(new GridLayout(1, 1));
		btnPnl.add(confirmBtn);
		
		contentPnl = new JPanel(new GridLayout(2, 1));
		contentPnl.add(formPnl);
		contentPnl.add(btnPnl);
		add(contentPnl);
	}
	
	private int productID;
	public AddToCartFormView(int productID) {
		this.productID = productID;
		init();
		setSize(500,150);
		setTitle("Coffee Vibes - Add To Cart");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String quantity = qtyTxt.getText();
		if(e.getSource() == confirmBtn) {
			CartHandler.getInstance().addToCart(productID, quantity);
			JOptionPane.showMessageDialog(this, CartHandler.getInstance().getMessage());
			if(CartHandler.getInstance().getMessage().equals("Successfully add to cart!")) {
				CartHandler.getInstance().viewCheckoutForm();
				dispose();
			}
		}
	}

}
