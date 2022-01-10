package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Transaction {

	private int transactionID;
	private LocalDate purchaseDate;
	private int voucherID;
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
	
	private Transaction map(ResultSet rs) {
		try {
			// not finished, placeholder only
			int id = rs.getInt("id");
			
			// not finished, placeholder only
			return new Transaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//**UNFINISHED**
	public Transaction insertTransaction() {
		return null;
	}
	
	public List<Transaction> getAllTransactions() {
		return null;
	}
	
	public Transaction getTransactionDetail(int transactionID) {
		return null;
	}
	//**UNFINISHED**

}
