package handlers;

import java.util.List;

import models.Transaction;
import models.TransactionItem;
import views.TransactionManagementFormView;
import java.time.LocalDate;

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
	
	public Transaction insertTransaction(String voucherID, String employeeID, String totalPayment) {
		errorMessage = "";
		int intVoucherID = 0;
		if(voucherID.equals("")) {
			errorMessage += "Voucher ID cannot be empty! ";
		} else {
			try {
				intVoucherID = Integer.parseInt(voucherID);
			} catch (NumberFormatException e) {
				errorMessage += "Voucher ID must be numeric! ";

			}
		}
		
		int intEmployeeID = 0;
		if(employeeID.equals("")) {
			errorMessage += "Employee ID cannot be empty! ";
		} else {
			try {
				intEmployeeID = Integer.parseInt(employeeID);
			} catch (NumberFormatException e) {
				errorMessage += "Employee ID must be numeric! ";

			}
		}
		
		int intTotalPayment = 0;
		if(totalPayment.equals("")) {
			errorMessage += "Total Payment cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intTotalPayment = Integer.parseInt(totalPayment);
			} catch (NumberFormatException e) {
				errorMessage += "Total Payment must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intTotalPayment < 1) {
					errorMessage += "Total Payment cannot be less than one! ";
				}
			}
		}
		
		if(errorMessage.equals("")) {
			transaction = new Transaction(LocalDate.now(), intVoucherID, intEmployeeID, intTotalPayment);
			errorMessage = "Successfully Inserted!";
			return transaction.insertTransaction();
		}
		return null;
	}
	
	public void viewTransactionManagementForm() {
		new TransactionManagementFormView(getUser());
	}


}
