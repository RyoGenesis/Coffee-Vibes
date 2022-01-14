package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connect.Connect;

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
	
	public Voucher(int discount) {
		super();
		this.discount = discount;
		this.status = "unused";
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
	
	public Voucher generateVoucher() {
		Connect con =  Connect.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO voucher VALUES(default,?,?)");
			preparedStatement.setInt(1, discount);
			preparedStatement.setString(2, status);
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		Voucher voucher = new Voucher(discount);
		return voucher;
	}
	
	public List<Voucher> getAllVouchers() {
		Connect con =  Connect.getConnection();
		List<Voucher> vouchers = new Vector<>();
		try {
			ResultSet resultSet = con.executeQuery("SELECT * FROM voucher");
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				int discount = resultSet.getInt(2);
				String status = resultSet.getString(3);
				
				Voucher voucher = new Voucher(id, discount, status);
				vouchers.add(voucher);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return vouchers;
	}
	
	public Voucher getVoucher(int voucherID) {
		Connect con = Connect.getConnection();
		Voucher voucher = null;
		try {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM voucher WHERE ID = ?");
			preparedStatement.setInt(1, voucherID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int id = resultSet.getInt(1);
				int discount = resultSet.getInt(2);
				String status = resultSet.getString(3);
				voucher = new Voucher(id, discount, status);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return voucher;
	}
	
	public boolean deleteVoucher(int voucherID) {
		Connect con =  Connect.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM voucher WHERE ID = ?");
			preparedStatement.setInt(1, voucherID);
			return preparedStatement.executeUpdate() == 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
