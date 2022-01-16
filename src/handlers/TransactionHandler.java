package handlers;

import java.util.List;

import connect.Connect;
import models.CartItem;
import models.Employee;
import models.Product;
import models.Transaction;
import models.TransactionItem;
import models.Voucher;
import views.TransactionManagementFormView;

import java.sql.ResultSet;
import java.time.LocalDate;

public class TransactionHandler {

	public static TransactionHandler transactionHandler = null;
	public Transaction transaction;
	private String message;

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
		int intVoucherID = 0;
		if(!voucherID.equals("")) {
			intVoucherID = Integer.parseInt(voucherID);
		}
		
		int intEmployeeID = 0;
		if(employeeID.equals("")) {
			message += "Employee ID cannot be empty! ";
		} else {
			boolean canParse = true;
			try {
				intEmployeeID = Integer.parseInt(employeeID);
			} catch (NumberFormatException e) {
				message += "Employee ID must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				Employee e = new Employee();
				if(e.getEmployeeById(intEmployeeID) == null) {
					message += "Employee ID is not exist in database! ";
				}
			}
		}
		
		if(message.equals("")) {
			if(intVoucherID != 0) {
				VoucherHandler.getInstance().deleteVoucher(voucherID);
			}

			transaction = new Transaction(LocalDate.now(), intVoucherID, intEmployeeID, totalPayment);
			transaction.insertTransaction();
			Transaction t = getLastTransaction();
			int insertedTransactionID = t.getTransactionID();
			
			List<CartItem> cartItems = CartHandler.getInstance().getCart();
			for (CartItem cartItem : cartItems) {
				int productID = cartItem.getProduct().getProductID();
				int quantity = cartItem.getQuantity();
				int stock = cartItem.getProduct().getStock() - quantity;
				ProductHandler.getInstance().updateProductStock(productID, stock);
				TransactionItem item = new TransactionItem(insertedTransactionID, productID, quantity);
				item.insertTransactionItem();
			}
			CartHandler.getInstance().clearCart();
			
			message = "Success Checkout!";
			return transaction;
		}
		return null;
	}
	
	public Transaction getLastTransaction() {
		return transaction.getLastTransaction();
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
		total = totalPrice - v.getDiscount();
		if(total < 0) {
			total = 0;
		}
		return total;
	}
	
	public void viewTransactionManagementForm() {
		new TransactionManagementFormView(AuthHandler.getInstance().getAuthUserPosition());
	}


}
