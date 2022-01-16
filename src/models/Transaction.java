package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import connect.Connect;

public class Transaction {

	private int transactionID;
	private LocalDate purchaseDate;
	private int voucherID = 0;
	private int employeeID;
	private int totalPrice;
	private List<TransactionItem> listTransactionItem;
	
	public Transaction(int transactionID, LocalDate purchaseDate, int voucherID, int employeeID, int totalPrice) {
		this.transactionID = transactionID;
		this.purchaseDate = purchaseDate;
		this.voucherID = voucherID;
		this.employeeID = employeeID;
		this.totalPrice = totalPrice;
	}
	
	public Transaction(LocalDate purchaseDate, int voucherID, int employeeID, int totalPrice) {
		this.purchaseDate = purchaseDate;
		this.voucherID = voucherID;
		this.employeeID = employeeID;
		this.totalPrice = totalPrice;
	}

	public Transaction(LocalDate purchaseDate, int employeeID, int totalPrice) {
		super();
		this.purchaseDate = purchaseDate;
		this.employeeID = employeeID;
		this.totalPrice = totalPrice;
	}

	public Transaction() {	}

	public int getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	public int getVoucherID() {
		return voucherID;
	}
	
	public void setVoucherID(int voucherID) {
		this.voucherID = voucherID;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public List<TransactionItem> getListTransactionItem() {
		return listTransactionItem;
	}
	
	public void setListTransactionItem(List<TransactionItem> listTransactionItem) {
		this.listTransactionItem = listTransactionItem;
	}
	
	public void addTransactionItem(TransactionItem transactionItem) {
		listTransactionItem.add(transactionItem);
	}
	
	public Transaction insertTransaction() {
		Connect con =  Connect.getConnection();
		Transaction transaction = null;
		if(voucherID != 0) {
			try {
				
				Date date = Date.valueOf(purchaseDate);
				
				PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO transactionheader VALUES(default,?,?,?,?)");
				preparedStatement.setDate(1, date);
				preparedStatement.setInt(2, voucherID);
				preparedStatement.setInt(3, employeeID);
				preparedStatement.setInt(4, totalPrice);
				preparedStatement.execute();
			} catch (Exception e) {
				// TODO: handle exception
			}
			transaction= new Transaction(purchaseDate, voucherID, employeeID, totalPrice);
		}
		else {//checkout without voucher
			try {
				
				Date date = Date.valueOf(purchaseDate);
				
				PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO transactionheader VALUES(default,?,NULL,?,?)");
				preparedStatement.setDate(1, date);
				preparedStatement.setInt(2, employeeID);
				preparedStatement.setInt(3, totalPrice);
				preparedStatement.execute();
			} catch (Exception e) {
				// TODO: handle exception
			}
			transaction= new Transaction(purchaseDate, employeeID, totalPrice);
		}
		return transaction;
	}
	
	public List<Transaction> getAllTransactions() {
		Connect con =  Connect.getConnection();
		List<Transaction> transactions = new Vector<>();
		try {
			ResultSet resultSet = con.executeQuery("SELECT * FROM transactionheader");
			while(resultSet.next()) {
				int transactionID = resultSet.getInt(1);
				LocalDate purchaseDate= resultSet.getDate(2).toLocalDate();
				int voucherID = resultSet.getInt(3);
				int employeeID = resultSet.getInt(4);
				int totalPrice = resultSet.getInt(5);
				
				Transaction transaction = new Transaction(transactionID, purchaseDate, voucherID, employeeID, totalPrice);
				transactions.add(transaction);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return transactions;
	}
	
	public Transaction getLastTransaction() {
		Connect con =  Connect.getConnection();
		Transaction transaction = null;
		try {
			ResultSet resultSet = con.executeQuery("SELECT * FROM transactionheader ORDER BY ID DESC LIMIT 1");
			if(resultSet.next()) {
				int transactionID = resultSet.getInt(1);
				LocalDate purchaseDate= resultSet.getDate(2).toLocalDate();
				int voucherID = resultSet.getInt(3);
				int employeeID = resultSet.getInt(4);
				int totalPrice = resultSet.getInt(5);
				transaction = new Transaction(transactionID, purchaseDate, voucherID, employeeID, totalPrice);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return transaction;
	}
	
	public List<TransactionItem> getTransactionDetail(int transactionID) {
		Connect con = Connect.getConnection();
		List<TransactionItem> transactions = new Vector<>();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM transactiondetail WHERE TransactionID = ?");
			preparedStatement.setInt(1, transactionID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int ID = resultSet.getInt(1);
				int productID = resultSet.getInt(2);
				int quantity = resultSet.getInt(3);
				
				TransactionItem transactionItem = new TransactionItem(ID, productID, quantity);
				transactions.add(transactionItem);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return transactions;
	}
}
