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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import handlers.CartHandler;
import handlers.TransactionHandler;
import handlers.VoucherHandler;
import models.CartItem;
import models.Voucher;

public class CartManagementFormView extends JFrame implements ActionListener{

	private JLabel productIdLbl;
	private JTextField productIdTxt;
	private JButton removeBtn, checkOutBtn;
	private JTable table;
	private DefaultTableModel dtm;
	private JPanel contentPnl, checkOutPnl, formPnl;
	private JScrollPane tableScroll;
	Object[] columns = {"Product ID", "Name", "Description", "Price", "Stock", "Quantity"};
	
	private void init() {
		makeTable();
		makeCheckOut();
		makeForm();
		
		contentPnl = new JPanel(new BorderLayout());
		contentPnl.add(BorderLayout.NORTH, tableScroll);
		contentPnl.add(BorderLayout.CENTER, checkOutPnl);
		contentPnl.add(BorderLayout.SOUTH, formPnl);
		add(contentPnl);
	}
	
	private void makeTable() {
		dtm = new DefaultTableModel(columns, 0);
		table = new JTable(dtm);
		tableScroll = new JScrollPane(table);
		tableScroll.setPreferredSize(new Dimension(300, 300));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int productId = (int)table.getValueAt(row, 0);
				
				productIdTxt.setText(productId + "");
			}
		});
	}

	private void makeForm() {
		productIdLbl = new JLabel("Product ID");
		productIdTxt = new JTextField();
		removeBtn = new JButton("Remove");
		removeBtn.addActionListener(this);
		
		formPnl = new JPanel(new GridLayout(3, 1));
		formPnl.add(productIdLbl);
		formPnl.add(productIdTxt);
		formPnl.add(removeBtn);
	}

	private void makeCheckOut() {
		checkOutBtn = new JButton("Checkout");
		checkOutBtn.addActionListener(this);
		
		checkOutPnl = new JPanel(new GridLayout(1, 1));
		checkOutPnl.add(checkOutBtn);
	}

	private void loadData() {
		dtm = new DefaultTableModel(columns, 0);
		List<CartItem> cartItems = CartHandler.getInstance().getCart();
		for (CartItem cartItem : cartItems) {
			int productId = cartItem.getProduct().getProductID();
			String name = cartItem.getProduct().getName();
			String desc = cartItem.getProduct().getDescription();
			int price = cartItem.getProduct().getPrice();
			int stock = cartItem.getProduct().getStock();
			int qty = cartItem.getQuantity();
			dtm.addRow(new Object[] {productId, name, desc, price, stock, qty});
		}
		table.setModel(dtm);
	}

	public CartManagementFormView() {
		init();
		setSize(500,500);
		setTitle("Coffee Vibes - Cart Management");
		loadData();
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == checkOutBtn) {
			TransactionHandler.getInstance().viewTransactionManagementForm();
		}
		else if(e.getSource() == removeBtn) {
			removeCartProduct();
		}
	}

	private void removeCartProduct() {
		String productId = productIdTxt.getText();
		
		int dialog = JOptionPane.showConfirmDialog(this, "Confirm Remove?");
		if(dialog == JOptionPane.YES_OPTION) {
			boolean c = CartHandler.getInstance().removeProductFromCart(productId);
			JOptionPane.showMessageDialog(this, CartHandler.getInstance().getMessage());
			if(c) {
				loadData();
			}
			
		}
	}
}
