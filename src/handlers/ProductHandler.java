package handlers;

import java.util.List;

import models.Product;
import views.ProductManagementFormView;

public class ProductHandler {

	public static ProductHandler productHandler = null;
	public Product product;
	private String message;
	private String user;

	public ProductHandler() {
		product = new Product();
		message = "";
	}
	
	public static ProductHandler getInstance() {
		if (productHandler == null) {
			productHandler = new ProductHandler();
		}
		return productHandler;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public List<Product> getAllProducts(){
		return product.getAllProducts();
	}
	
	public Product getProduct(int productID) {
		return product.getProduct(productID);
	}
	
	public Product insertProduct(String name, String description, String price, String stock) {
		message = "";
		if(name.equals("")) {
			message += "Name cannot be empty! ";
		}
		if(description.equals("")) {
			message += "Description cannot be empty! ";
		}
		int intPrice = 0;
		if(price.equals("")) {
			message += "Price cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intPrice = Integer.parseInt(price);
			} catch (NumberFormatException e) {
				message += "Price must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intPrice < 1) {
					message += "Price cannot be less than one! ";
				}
			}
		}
		int intStock = 0;
		if(stock.equals("")) {
			message += "Stock cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intStock = Integer.parseInt(stock);
			} catch (NumberFormatException e) {
				message += "Stock must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intStock < 0) {
					message += "Stock cannot be less than zero! ";
				}
			}
		}
		
		if(message.equals("")) {
			product = new Product(name, description, intPrice, intStock);
			message = "Successfully Inserted!";
			return product.insertNewProduct();
		}
		return null;
	}
	
	public Product updateProduct(String productID, String name, String description, String price) {
		message = "";
		int intID = 0;
		if(productID.equals("")) {
			message += "ID cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intID = Integer.parseInt(productID);
			} catch (NumberFormatException e) {
				message += "ID must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				Product p = getProduct(intID);
				if(p == null) {
					message += "ID is not exist in database! ";
				}
			}
		}
		if(name.equals("")) {
			message += "Name cannot be empty! ";
		}
		if(description.equals("")) {
			message += "Description cannot be empty! ";
		}
		int intPrice = 0;
		if(price.equals("")) {
			message += "Price cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intPrice = Integer.parseInt(price);
			} catch (NumberFormatException e) {
				message += "Price must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intPrice < 1) {
					message += "Price cannot be less than one! ";
				}
			}
		}
		
		if(message.equals("")) {
			product = new Product(intID, name, description, intPrice);
			message = "Successfully Updated!";
			return product.updateProduct();
		}
		return null;
	}
	
	public Product updateProductStock(int productID, int stock) {
		product = new Product(productID, stock);
		return product.updateProduct();
	}
	
	public boolean deleteProduct(String productID) {
		message = "";
		int intID = 0;
		if(productID.equals("")) {
			message += "ID cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intID = Integer.parseInt(productID);
			} catch (NumberFormatException e) {
				message += "ID must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				Product p = getProduct(intID);
				if(p == null) {
					message += "ID is not exist in database! ";
				}
			}
		}
		
		if(message.equals("")) {
			product = new Product(intID);
			message = "Successfully Deleted!";
			return product.deleteProduct();
		}
		return false;
	}
	
	public void viewProductManagementForm() {
		new ProductManagementFormView(getUser());
	}
}
