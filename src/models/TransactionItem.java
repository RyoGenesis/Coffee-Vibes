package models;

import java.sql.PreparedStatement;

import connect.Connect;

public class TransactionItem {

	private int transactionID;
	private int productID;
	private int quantity;
	
	public TransactionItem(int transactionID, int productID, int quantity) {
		this.transactionID = transactionID;
		this.productID = productID;
		this.quantity = quantity;
	}
	
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public TransactionItem insertTransactionItem() {
		Connect con =  Connect.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO transactiondetail VALUES(?,?,?)");
			preparedStatement.setInt(1, transactionID);
			preparedStatement.setInt(2, productID);
			preparedStatement.setInt(3, quantity);
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		TransactionItem item = new TransactionItem(transactionID, productID, quantity);
		return item;
	}
}
