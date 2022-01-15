package handlers;

import java.util.List;

import models.Transaction;
import models.TransactionItem;
import views.TransactionManagementFormView;

public class TransactionHandler {

	public static TransactionHandler transactionHandler = null;
	public Transaction transaction;
	private String errorMessage;
	private String user;

	public TransactionHandler() {
		transaction = new Transaction();
		errorMessage = "";
	}
	
	public static TransactionHandler getInstance() {
		if (transactionHandler == null) {
			transactionHandler = new TransactionHandler();
		}
		return transactionHandler;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Transaction> getAllTransactions(){
		return transaction.getAllTransactions();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public List<TransactionItem> getTransactionDetail(int transactionID) {
		return transaction.getTransactionDetail(transactionID);
	}
	
	//**UNFINISHED**
	public Transaction insertTransaction(int voucherID, int employeeID, int totalPayment) {
		return null;
	}
	
	public void viewTransactionManagementForm() {
		new TransactionManagementFormView(getUser());
	}
	//**UNFINISHED**

}
