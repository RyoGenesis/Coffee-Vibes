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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import handlers.TransactionHandler;
import models.Transaction;
import models.TransactionItem;

public class TransactionManagementFormView extends JFrame implements ActionListener{
	

	private JTable tableTransaction, tableTransactionItem;
	private JLabel transactionTitleLbl, transactionIdLbl, purchaseDateLbl, voucherIdLbl, employeeIdLbl, priceLbl, totalPriceLbl;
	private JLabel transactionIdTxt, purchaseDateTxt, priceTxt;
	private JButton checkOutBtn;
	private DefaultTableModel dtmTransaction, dtmTransactionItem;
	private JPanel contentPnl, formPnl, buttonPnl, detailTransactionPnl , transactionPnl, transactionTitlePnl, transactionContentPnl;
	private JPanel transactionIdLblPnl, transactionIdTxtPnl, purchaseDateLblPnl, purchaseDateTxtPnl, priceLblPnl, priceTxtPnl;
	private JScrollPane tableTransactionHeaderScroll, tableTransactionItemScroll;
	private Object[] columnTransactionHeaders = {"ID", "Purchase Date","Voucher ID", "Employee ID", "Total Price"};
	private Object[] columnTransactionItems = {"Product ID", "Quantity"};
	private String user;
	
	
	public TransactionManagementFormView(String user) {
		this.user = user;
		initComp();
		addComp();
		setTitle("Coffee Vibes - Transaction Management");
		if(user.equalsIgnoreCase("manager")) {
			setSize(500,500);
			loadTransactionHeaderData();
		}
		else {
			setSize(250,250);
		}
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);		
	}

	
	private void initComp() {
		if(user.equalsIgnoreCase("manager")) {
			makeTransactionHeaderTable();
			makeTransactionDetail();
		}
		else {
			makeForm();
			makeButton();
		}
	}
	
	private void makeForm() {
		voucherIdLbl 
		employeeIdLbl
		
		formPnl = new JPanel(new GridLayout(2,2));
	}

	private void makeButton() {
		checkOutBtn = new JButton("Checkout");
		
//		buttonPnl = new JPanel(new GridLayout(1, 2));
//		buttonPnl.add(checkOutBtn);
	}

	private void makeTransactionHeaderTable() {
		dtmTransaction = new DefaultTableModel(columnTransactionHeaders, 0);
		tableTransaction = new JTable(dtmTransaction);
		tableTransactionHeaderScroll = new JScrollPane(tableTransaction);
		tableTransactionHeaderScroll.setPreferredSize(new Dimension(200,150));
		
	}
	
	private void makeTransactionItemTable() {
		dtmTransactionItem = new DefaultTableModel(columnTransactionItems, 0);
		tableTransactionItem = new JTable(dtmTransactionItem);
		tableTransactionItemScroll = new JScrollPane(tableTransactionItem);
		tableTransactionItemScroll.setPreferredSize(new Dimension(200,100));
		tableTransactionItemScroll.setVisible(false);
		
	}
	
	private void makeTransactionDetail() {
		makeTransactionItemTable();
		
		
		transactionPnl = new JPanel(new BorderLayout());
		detailTransactionPnl = new JPanel(new BorderLayout());
		transactionTitlePnl = new JPanel();
		transactionContentPnl = new JPanel(new GridLayout(3,3));
		
		transactionTitleLbl = new JLabel("Transaction Detail");
		transactionTitlePnl.add(transactionTitleLbl);
		
		//Transaction ID
		transactionIdLblPnl = new JPanel();
		transactionIdTxtPnl = new JPanel();
		transactionIdLbl = new JLabel("Transaction ID:");
		transactionIdTxt = new JLabel();
		transactionIdTxt.setPreferredSize(new Dimension(100, 15));
		transactionIdLblPnl.add(transactionIdLbl);
		transactionIdTxtPnl.add(transactionIdTxt);
		
		//Purchase Date
		purchaseDateLblPnl = new JPanel();
		purchaseDateTxtPnl = new JPanel();
		purchaseDateLbl = new JLabel("Purchase Date:");
		purchaseDateTxt = new JLabel();
		purchaseDateTxt.setPreferredSize(new Dimension(100, 15));
		purchaseDateLblPnl.add(purchaseDateLbl);
		purchaseDateTxtPnl.add(purchaseDateTxt);
		
		// Total Price
		priceLblPnl = new JPanel();
		priceTxtPnl = new JPanel();
		priceLbl = new JLabel("Total Price:");
		priceTxt = new JLabel();
		priceTxt.setPreferredSize(new Dimension(100, 15));
		priceLblPnl.add(priceLbl);
		priceTxtPnl.add(priceTxt);
				
		transactionContentPnl.add(transactionIdLblPnl);
		transactionContentPnl.add(transactionIdTxtPnl);
		
		transactionContentPnl.add(purchaseDateLblPnl);
		transactionContentPnl.add(purchaseDateTxtPnl);
		
		transactionContentPnl.add(priceLblPnl);
		transactionContentPnl.add(priceTxtPnl);
		
	
		detailTransactionPnl.add(BorderLayout.NORTH, transactionTitlePnl);
		detailTransactionPnl.add(BorderLayout.CENTER, transactionContentPnl);
		detailTransactionPnl.add(BorderLayout.SOUTH, tableTransactionItemScroll);
		transactionPnl.add(detailTransactionPnl);
	}
	
	private void addComp() {
		if(user.equalsIgnoreCase("manager")) {
			tableTransaction.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					//reload table transaction item
					dtmTransactionItem = new DefaultTableModel(columnTransactionItems, 0);
					
					tableTransactionItem.clearSelection();
					int row = tableTransaction.getSelectedRow();
					int transactionId = (int) tableTransaction.getValueAt(row, 0);
					String purchaseDate = (String) tableTransaction.getValueAt(row, 1).toString();
					int totalPrice = (int) tableTransaction.getValueAt(row, 4);
					transactionIdTxt.setText(transactionId + "");
					purchaseDateTxt.setText(purchaseDate);
					priceTxt.setText(totalPrice + "");
					
					loadTransactionItemData(transactionId);
					tableTransactionItemScroll.setVisible(true);
				}
			});
			
			contentPnl = new JPanel(new BorderLayout());
			contentPnl.add(BorderLayout.NORTH, tableTransactionHeaderScroll);
			contentPnl.add(BorderLayout.CENTER, transactionPnl);
			add(contentPnl);
		}
		else {
			
		}
	}

	private void loadTransactionHeaderData() {
		dtmTransaction = new DefaultTableModel(columnTransactionHeaders, 0);
		List<Transaction> transactions = TransactionHandler.getInstance().getAllTransactions();
		for (Transaction transaction : transactions) {
			int transactionID = transaction.getTransactionID();
			String purchaseDate = transaction.getPurchaseDate().toString();
			int voucherID  = transaction.getVoucherID();
			int employeeID = transaction.getEmployeeID();
			int totalPrice = transaction.getTotalPrice();
			dtmTransaction.addRow(new Object[] {transactionID, purchaseDate, voucherID, employeeID, totalPrice});
		}
		tableTransaction.setModel(dtmTransaction);
	}
	
	private void loadTransactionItemData(int transactionID) {
		List<TransactionItem> transactions = TransactionHandler.transactionHandler.getTransactionDetail(transactionID);
		for (TransactionItem transaction : transactions) {
			int ID = transaction.getTransactionID();
			int productID = transaction.getProductID();
			int quantity = transaction.getQuantity();
			dtmTransactionItem.addRow(new Object[] {productID, quantity});
		}
		tableTransactionItem.setModel(dtmTransactionItem);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}


}
