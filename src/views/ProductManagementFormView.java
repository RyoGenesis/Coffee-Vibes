package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import handlers.ProductHandler;
import models.Product;

public class ProductManagementFormView extends JFrame implements ActionListener{

	//**UNFINISHED**
	private JLabel idLbl, nameLbl, descLbl, priceLbl, stockLbl;
	private JTextField idTxt, nameTxt, descTxt, priceTxt, stockTxt;
	private JButton insertBtn, updateBtn, deleteBtn;
	private JTable table;
	private DefaultTableModel dtm;
	private JPanel contentPnl, formPnl, buttonPnl;
	private JScrollPane tableScroll;
	Object[] columns = {"ID", "Name", "Description", "Price", "Stock"};
	
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

	public ProductManagementFormView() {
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
		
		Product p = ProductHandler.getInstance().updateProduct(id, name, desc, price);
		JOptionPane.showMessageDialog(this, ProductHandler.getInstance().getMessage());
		if(p != null) {
			loadData();
		}
	}

	private void deleteProduct() {
		String id = idTxt.getText();
		
		boolean p = ProductHandler.getInstance().deleteProduct(id);
		JOptionPane.showMessageDialog(this, ProductHandler.getInstance().getMessage());
		if(p) {
			loadData();
		}
	}

}
