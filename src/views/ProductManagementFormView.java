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
import handlers.ProductHandler;
import models.Product;

public class ProductManagementFormView extends JFrame implements ActionListener{

	private JLabel idLbl, nameLbl, descLbl, priceLbl, stockLbl;
	private JTextField idTxt, nameTxt, descTxt, priceTxt, stockTxt;
	private JButton insertBtn, updateBtn, deleteBtn, addToCartBtn;
	private JTable table;
	private DefaultTableModel dtm;
	private JPanel contentPnl, formPnl, buttonPnl;
	private JScrollPane tableScroll;
	private String user;
	private int productID;
	Object[] columns = {"ID", "Name", "Description", "Price", "Stock"};
	
	private void init() {
		makeTable();
		if(user.equalsIgnoreCase("productAdmin")) {
			makeForm();
		}
		makeButton();
		
		contentPnl = new JPanel(new BorderLayout());
		contentPnl.add(BorderLayout.NORTH, tableScroll);
		if(user.equalsIgnoreCase("productAdmin")) {
			contentPnl.add(BorderLayout.CENTER, formPnl);
			contentPnl.add(BorderLayout.SOUTH, buttonPnl);
		}
		else {
			contentPnl.add(BorderLayout.CENTER, buttonPnl);
		}
		add(contentPnl);
	}
	
	private void makeTable() {
		dtm = new DefaultTableModel(columns, 0);
		table = new JTable(dtm);
		tableScroll = new JScrollPane(table);
		if(user.equalsIgnoreCase("productAdmin")) {
			tableScroll.setPreferredSize(new Dimension(200, 200));
		}
		else {
			tableScroll.setPreferredSize(new Dimension(400, 400));
		}
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int id = (int)table.getValueAt(row, 0);
				String name = (String)table.getValueAt(row, 1);
				String desc = (String)table.getValueAt(row, 2);
				int price = (int)table.getValueAt(row, 3);
				int stock = (int)table.getValueAt(row, 4);
				
				if(user.equalsIgnoreCase("barista")) {
					productID = id;
				}
				else {
					idTxt.setText(id + "");
					nameTxt.setText(name);
					descTxt.setText(desc);
					priceTxt.setText(price + "");
					stockTxt.setText(stock + "");
				}
			}
		});
	}

	private void makeForm() {
		idLbl = new JLabel("ID");
		nameLbl = new JLabel("Name");
		descLbl = new JLabel("Description");
		priceLbl = new JLabel("Price");
		stockLbl = new JLabel("Stock");
		
		idTxt = new JTextField();
		nameTxt = new JTextField();
		descTxt = new JTextField();
		priceTxt = new JTextField();
		stockTxt = new JTextField();
		
		formPnl = new JPanel(new GridLayout(5, 2));
		formPnl.add(idLbl);
		formPnl.add(idTxt);
		formPnl.add(nameLbl);
		formPnl.add(nameTxt);
		formPnl.add(descLbl);
		formPnl.add(descTxt);
		formPnl.add(priceLbl);
		formPnl.add(priceTxt);
		formPnl.add(stockLbl);
		formPnl.add(stockTxt);
	}

	private void makeButton() {
		if(user.equalsIgnoreCase("productAdmin")) {
			insertBtn = new JButton("Insert");
			updateBtn = new JButton("Update");
			deleteBtn = new JButton("Delete");
			
			insertBtn.addActionListener(this);
			updateBtn.addActionListener(this);
			deleteBtn.addActionListener(this);

			buttonPnl = new JPanel(new GridLayout(1, 3));
			buttonPnl.add(insertBtn);
			buttonPnl.add(updateBtn);
			buttonPnl.add(deleteBtn);
		}
		else {
			addToCartBtn = new JButton("Add To Cart");
			addToCartBtn.addActionListener(this);
			buttonPnl = new JPanel(new GridLayout(1, 1));
			buttonPnl.add(addToCartBtn);
		}
		
	}

	private void loadData() {
		dtm = new DefaultTableModel(columns, 0);
		List<Product> products =  ProductHandler.getInstance().getAllProducts();
		for (Product product : products) {
			int id = product.getProductID();
			String name = product.getName();
			String desc = product.getDescription();
			int price = product.getPrice();
			int stock = product.getStock();
			dtm.addRow(new Object[] {id, name, desc, price, stock});
		}
		table.setModel(dtm);
	}

	public ProductManagementFormView(String user) {
		this.productID = 0;
		this.user = user;
		init();
		setSize(500,500);
		setTitle("Coffee Vibes - Product Management");
		loadData();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertBtn) {
			insertProduct();
		}
		else if(e.getSource() == updateBtn) {
			updateProduct();
		}
		else if(e.getSource() == deleteBtn) {
			deleteProduct();
		}
		else if(e.getSource() == addToCartBtn) {
			if(productID == 0) {
				JOptionPane.showMessageDialog(this, "Please choose a product from table for add to cart!");
			}
			else {
				new AddToCartFormView(productID);
			}
		}
	}

	private void insertProduct() {
		String name = nameTxt.getText();
		String desc = descTxt.getText();
		String price = priceTxt.getText();
		String stock = stockTxt.getText(); 
		
		Product p = ProductHandler.getInstance().insertProduct(name, desc, price, stock);
		JOptionPane.showMessageDialog(this, ProductHandler.getInstance().getMessage());
		if(p != null) {
			loadData();
		}
	}

	private void updateProduct() {
		String id = idTxt.getText();
		String name = nameTxt.getText();
		String desc = descTxt.getText();
		String price = priceTxt.getText();
		
		int dialog = JOptionPane.showConfirmDialog(this, "Confirm Update?");
		if(dialog == JOptionPane.YES_OPTION) {
			Product p = ProductHandler.getInstance().updateProduct(id, name, desc, price);
			JOptionPane.showMessageDialog(this, ProductHandler.getInstance().getMessage());
			if(p != null) {
				loadData();
			}
		}
	}

	private void deleteProduct() {
		String id = idTxt.getText();
		
		int dialog = JOptionPane.showConfirmDialog(this, "Confirm Delete?");
		if(dialog == JOptionPane.YES_OPTION) {
			boolean p = ProductHandler.getInstance().deleteProduct(id);
			JOptionPane.showMessageDialog(this, ProductHandler.getInstance().getMessage());
			if(p) {
				loadData();
			}
			
		}
	}
}
