package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Product {

	private int productID;
	private String name;
	private String description;
	private int price;
	private int stock;

	public Product(int productID, String name, String description, int price, int stock) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public Product() {}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	private Product map(ResultSet rs) {
		try {
			// not finished, placeholder only
			int id = rs.getInt("id");
			String name = rs.getString("name");
			
			// not finished, placeholder only
			return new Product();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//**UNFINISHED**
	public Product insertNewProduct() {
		return null;
	}
	
	public List<Product> getAllProducts() {
		return null;
	}
	
	public Product getProduct(int productID) {
		return null;
	}
	
	public Product updateProduct() {
		return null;
	}
	
	public boolean deleteProduct() {
		return false;
	}
	
	//**UNFINISHED**

}
