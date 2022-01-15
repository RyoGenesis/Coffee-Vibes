package handlers;

import java.util.List;

import models.Transaction;
import models.TransactionItem;
import views.TransactionManagementFormView;
import java.time.LocalDate;

public class TransactionHandler {

	public static TransactionHandler transactionHandler = null;
	public Transaction transaction;
	private String message;
	private String user;

	public TransactionHandler() {
		transaction = new Transaction();
		message = "";
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
	
	public String getMessage() {
		return message;
	}
	
	public List<TransactionItem> getTransactionDetail(int transactionID) {
		return transaction.getTransactionDetail(transactionID);
	}
	
	public Transaction insertTransaction(String voucherID, String employeeID, String totalPayment) {
		message = "";
		int intVoucherID = 0;
		if(voucherID.equals("")) {
			message += "Voucher ID cannot be empty! ";
		} else {
			try {
				intVoucherID = Integer.parseInt(voucherID);
			} catch (NumberFormatException e) {
				message += "Voucher ID must be numeric! ";

			}
		}
		
		int intEmployeeID = 0;
		if(employeeID.equals("")) {
			message += "Employee ID cannot be empty! ";
		} else {
			try {
				intEmployeeID = Integer.parseInt(employeeID);
			} catch (NumberFormatException e) {
				message += "Employee ID must be numeric! ";

			}
		}
		
		int intTotalPayment = 0;
		if(totalPayment.equals("")) {
			message += "Total Payment cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intTotalPayment = Integer.parseInt(totalPayment);
			} catch (NumberFormatException e) {
				message += "Total Payment must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intTotalPayment < 1) {
					message += "Total Payment cannot be less than one! ";
				}
			}
		}
		
		if(message.equals("")) {
			transaction = new Transaction(LocalDate.now(), intVoucherID, intEmployeeID, intTotalPayment);
			message = "Successfully Inserted!";
			return transaction.insertTransaction();
		}
		return null;
	}
	
	public void viewTransactionManagementForm() {
		new TransactionManagementFormView(getUser());
	}


}
