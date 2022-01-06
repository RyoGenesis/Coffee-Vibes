package handlers;

import java.util.List;

import models.Voucher;
import views.VoucherManagementFormView;

public class VoucherHandler {
	
	public static VoucherHandler voucherHandler = null;
	public Voucher voucher;
	private String errorMessage;

	public VoucherHandler() {
		voucher = new Voucher();
		errorMessage = "";
	}
	
	public static VoucherHandler getInstance() {
		if (voucherHandler == null) {
			voucherHandler = new VoucherHandler();
		}
		return voucherHandler;
	}
	
	public List<Voucher> getAllVouchers(){
		return voucher.getAllVouchers();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	//**UNFINISHED**
	public Voucher insertVoucher(int discount) {
		return null;
	}
	
	public Voucher getVoucher(int voucherID) {
		return null;
	}
	
	public boolean deleteVoucher(int voucherID) {
		return true;
	}
	
	public void viewVoucherManagementForm() {
		new VoucherManagementFormView();
	}
	//**UNFINISHED**

}
