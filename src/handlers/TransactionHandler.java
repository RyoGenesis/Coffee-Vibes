package handlers;

import java.util.List;

import models.Product;
import models.Transaction;
import models.TransactionItem;
import models.Voucher;
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
	
	public Transaction insertTransaction(String voucherID, String employeeID, int totalPayment) {
		message = "";
		Integer intVoucherID = null;
		if(!voucherID.equals("")) {
			intVoucherID = Integer.parseInt(voucherID);
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
		
		if(message.equals("")) {
			transaction = new Transaction(LocalDate.now(), intVoucherID, intEmployeeID, totalPayment);
			message = "Successfully Inserted!";
			return transaction.insertTransaction();
		}
		return null;
	}
	
	Voucher v = null;
	public Voucher getVoucher(String voucherID) {
		message = "";
		int intVoucherID = 0;
		boolean canParse = true;
		try {
			intVoucherID = Integer.parseInt(voucherID);
		} catch (NumberFormatException e) {
			message += "Voucher ID must be numeric! ";
			canParse = false;
		}
		if(canParse) {
			v = VoucherHandler.getInstance().getVoucher(intVoucherID);
			if(v == null) {
				message += "Voucher ID is not exist in database! ";
			}
		}
		if(v != null) {
			message = "Voucher applied successfully!";
		}
		return v;
	}
	
	public int recalculateTotalPrice(int totalPrice) {
		int total = 0;
		if(v.getStatus().equalsIgnoreCase("unused")) {
			total = totalPrice - v.getDiscount();
			if(total < 0) {
				total = 0;
			}
		}
		else {
			total = totalPrice;
		}
		return total;
	}
	
	public void viewTransactionManagementForm() {
		new TransactionManagementFormView(getUser());
	}


}
