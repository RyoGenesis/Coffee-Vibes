package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import connect.Connect;

public class Product {

	private int productID;
	private String name;
	private String description;
	private int price;
	private int stock = 0;

	public Product(int productID, String name, String description, int price, int stock) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
	
	public Product(String name, String description, int price, int stock) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public Product(int productID, String name, String description, int price) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Product(int productID, int stock) {
		super();
		this.productID = productID;
		this.stock = stock;
	}

	public Product(int productID) {
		super();
		this.productID = productID;
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
	
	public Product insertNewProduct() {
		Connect con =  Connect.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO product VALUES(default,?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			preparedStatement.setInt(3, price);
			preparedStatement.setInt(4, stock);
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		Product product = new Product(name, description, price, stock);
		return product;
	}
	
	public List<Product> getAllProducts() {
		Connect con =  Connect.getConnection();
		List<Product> products = new Vector<>();
		try {
			ResultSet resultSet = con.executeQuery("SELECT * FROM product");
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				int price = resultSet.getInt(4);
				int stock = resultSet.getInt(5);
				
				Product product = new Product(id, name, description, price, stock);
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return products;
	}
	
	public Product getProduct(int productID) {
		Connect con = Connect.getConnection();
		Product product = null;
		try {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM product WHERE ID = ?");
			preparedStatement.setInt(1, productID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				int price = resultSet.getInt(4);
				int stock = resultSet.getInt(5);
				product = new Product(id, name, description, price, stock);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return product;
	}
	
	public Product updateProduct() {
		Connect con =  Connect.getConnection();
		Product product = null;
		if(stock == 0) {
			product = new Product(productID, name, description, price);
			try {
				PreparedStatement preparedStatement = con.prepareStatement("UPDATE product SET Name = ?, Description = ?, Price = ? WHERE ID = ?");
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, description);
				preparedStatement.setInt(3, price);
				preparedStatement.setInt(4, productID);
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else {//updateProductStock
			product = new Product(productID, stock);
			try {
				PreparedStatement preparedStatement = con.prepareStatement("UPDATE product SET Stock = ? WHERE ID = ?");
				preparedStatement.setInt(1, stock);
				preparedStatement.setInt(2, productID);
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return product;
	}
	
	public boolean deleteProduct() {
		Connect con =  Connect.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM product WHERE ID = ?");
			preparedStatement.setInt(1, productID);
			return preparedStatement.executeUpdate() == 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
}
