package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import handlers.TransactionHandler;
import models.Transaction;

public class TransactionManagementFormView extends JFrame {

	private JTable table;
	private JLabel transactionTitleLbl;
	private JTextField idTxt, discountTxt;
	private JButton seeEmployeeBtn;
	private DefaultTableModel dtm;
	private JPanel contentPnl, detailTransactionPnl, buttonPnl;
	private JScrollPane tableScroll;
	Object[] columns = {"ID", "Purchase Date","Voucher ID", "Employee ID", "Total Price"};
	
	
	public TransactionManagementFormView() {
		initComp();
		addComp();
		setSize(500,500);
		setTitle("Coffee Vibes - Transaction Management");
		loadData();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);		
	}

	
	private void initComp() {
		makeTransactionTable();
		makeTransactionDetail();
		makeButton();
	}
	
	private void makeTransactionTable() {
		dtm = new DefaultTableModel(columns, 0);
		table = new JTable(dtm);
		tableScroll = new JScrollPane(table);
		tableScroll.setPreferredSize(new Dimension(200,200));
	}
	
	private void makeTransactionDetail() {
		transactionTitleLbl = new JLabel("Transaction Detail");
		
		detailTransactionPnl = new JPanel(new GridLayout(2, 2));
		detailTransactionPnl.add(transactionTitleLbl);
	}

	private void makeButton() {
		seeEmployeeBtn = new JButton("See Employees");
		
		buttonPnl = new JPanel(new GridLayout(1, 2));
		buttonPnl.add(seeEmployeeBtn);
	}
	
	private void addComp() {
		contentPnl = new JPanel(new BorderLayout());
		contentPnl.add(BorderLayout.NORTH, tableScroll);
		contentPnl.add(BorderLayout.CENTER, detailTransactionPnl);
		contentPnl.add(BorderLayout.SOUTH, buttonPnl);
		add(contentPnl);
	}

	private void loadData() {
		dtm = new DefaultTableModel(columns, 0);
		List<Transaction> transactions = TransactionHandler.getInstance().getAllTransactions();
		for (Transaction transaction : transactions) {
			int transactionID = transaction.getTransactionID();
			String purchaseDate = transaction.getPurchaseDate().toString();
			int voucherID  = transaction.getVoucherID();
			int employeeID = transaction.getEmployeeID();
			int totalPrice = transaction.getTotalPrice();
			dtm.addRow(new Object[] {transactionID, purchaseDate, voucherID, employeeID, totalPrice});
		}
		table.setModel(dtm);
	}


}
