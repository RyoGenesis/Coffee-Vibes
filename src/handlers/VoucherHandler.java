package handlers;

import java.util.List;

import models.Product;
import models.Voucher;
import views.VoucherManagementFormView;

public class VoucherHandler {
	
	public static VoucherHandler voucherHandler = null;
	public Voucher voucher;
	private String message;

	public VoucherHandler() {
		voucher = new Voucher();
		message = "";
	}
	
	public static VoucherHandler getInstance() {
		if (voucherHandler == null) {
			voucherHandler = new VoucherHandler();
		}
		return voucherHandler;
	}
	
	public String getMessage() {
		return message;
	}

	public List<Voucher> getAllVouchers(){
		return voucher.getAllVouchers();
	}
	
	public Voucher insertVoucher(String discount) {
		message = "";
		int intDiscount = 0;
		if(discount.equals("")) {
			message += "Discount cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intDiscount = Integer.parseInt(discount);
			} catch (NumberFormatException e) {
				message += "Discount must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intDiscount < 1 || intDiscount > 100) {
					message += "Discount must be between 1 and 100! ";
				}
			}
		}
		
		if(message.equals("")) {
			voucher = new Voucher(intDiscount);
			message = "Successfully Inserted!";
			return voucher.generateVoucher();
		}
		return null;
	}
	
	public Voucher getVoucher(int voucherID) {
		return voucher.getVoucher(voucherID);
	}
	
	public boolean deleteVoucher(String voucherID) {
		message = "";
		int intID = 0;
		if(voucherID.equals("")) {
			message += "ID cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intID = Integer.parseInt(voucherID);
			} catch (NumberFormatException e) {
				message += "ID must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				Voucher v = getVoucher(intID);
				if(v == null) {
					message += "ID is not exist in database! ";
				}
			}
		}
		
		if(message.equals("")) {
			message = "Successfully Deleted!";
			return voucher.deleteVoucher(intID);
		}
		return false;
	}
	
	public void viewVoucherManagementForm() {
		new VoucherManagementFormView();
	}
}
