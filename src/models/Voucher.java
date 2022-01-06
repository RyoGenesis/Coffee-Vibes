package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Voucher {

	private int voucherID;
	private int discount;
	private String status;
	
	public Voucher(int voucherID, int discount, String status) {
		super();
		this.voucherID = voucherID;
		this.discount = discount;
		this.status = status;
	}

	public Voucher() {}

	public int getVoucherID() {
		return voucherID;
	}

	public void setVoucherID(int voucherID) {
		this.voucherID = voucherID;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	private Voucher map(ResultSet rs) {
		try {
			// not finished, placeholder only
			int id = rs.getInt("id");
			
			// not finished, placeholder only
			return new Voucher();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//**UNFINISHED**
	public Voucher generateVoucher() {
		return null;
	}
	
	public List<Voucher> getAllVouchers() {
		return null;
	}
	
	public Voucher getVoucher(int voucherID) {
		return null;
	}
	
	public boolean deleteVoucher(int voucherID) {
		return false;
	}
	//**UNFINISHED**

}
