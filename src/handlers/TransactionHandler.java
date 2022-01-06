package handlers;

import java.util.List;

import models.Transaction;
import views.TransactionManagementFormView;

public class TransactionHandler {

	public static TransactionHandler transactionHandler = null;
	public Transaction transaction;
	private String errorMessage;

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
	
	public List<Transaction> getAllTransactions(){
		return transaction.getAllTransactions();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	//**UNFINISHED**
	public Transaction getTransactionDetail(int transactionID) {
		return null;
	}
	
	public Transaction insertTransaction(int voucherID, int employeeID, int totalPayment) {
		return null;
	}
	
	public void viewTransactionManagementForm() {
		new TransactionManagementFormView();
	}
	//**UNFINISHED**

}
