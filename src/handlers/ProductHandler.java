package handlers;

import java.util.List;

import models.Product;
import views.ProductManagementFormView;

public class ProductHandler {

	public static ProductHandler productHandler = null;
	public Product product;
	private String errorMessage;

	public ProductHandler() {
		product = new Product();
		errorMessage = "";
	}
	
	public static ProductHandler getInstance() {
		if (productHandler == null) {
			productHandler = new ProductHandler();
		}
		return productHandler;
	}
	
	public List<Product> getAllProducts(){
		return product.getAllProducts();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	//**UNFINISHED**
	public Product getProduct(int productID) {
		return product.getProduct(productID);
	}
	
	public Product insertProduct(String name, String description, String price, String stock) {
		errorMessage = "";
		if(name.equals("")) {
			errorMessage += "Name cannot be empty! ";
		}
		if(description.equals("")) {
			errorMessage += "Description cannot be empty! ";
		}
		int intPrice = 0;
		if(price.equals("")) {
			errorMessage += "Price cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intPrice = Integer.parseInt(price);
			} catch (NumberFormatException e) {
				errorMessage += "Price must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intPrice < 1) {
					errorMessage += "Price cannot be less than one! ";
				}
			}
		}
		int intStock = 0;
		if(stock.equals("")) {
			errorMessage += "Stock cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intStock = Integer.parseInt(stock);
			} catch (NumberFormatException e) {
				errorMessage += "Stock must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intStock < 0) {
					errorMessage += "Stock cannot be less than zero! ";
				}
			}
		}
		
		if(errorMessage.equals("")) {
			product = new Product(name, description, intPrice, intStock);
			return product.insertNewProduct();
		}
		return null;
	}
	
	public Product updateProduct(String productID, String name, String description, String price) {
		errorMessage = "";
		int intID = 0;
		if(productID.equals("")) {
			errorMessage += "ID cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intID = Integer.parseInt(productID);
			} catch (NumberFormatException e) {
				errorMessage += "ID must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				Product p = getProduct(intID);
				if(p == null) {
					errorMessage += "ID is not exist in database! ";
				}
			}
		}
		if(name.equals("")) {
			errorMessage += "Name cannot be empty! ";
		}
		if(description.equals("")) {
			errorMessage += "Description cannot be empty! ";
		}
		int intPrice = 0;
		if(price.equals("")) {
			errorMessage += "Price cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intPrice = Integer.parseInt(price);
			} catch (NumberFormatException e) {
				errorMessage += "Price must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intPrice < 1) {
					errorMessage += "Price cannot be less than one! ";
				}
			}
		}
		
		if(errorMessage.equals("")) {
			product = new Product(intID, name, description, intPrice);
			return product.updateProduct();
		}
		return null;
	}
	
	public Product updateProductStock(int productID, int stock) {
		//BARISTA
		return null;
	}
	
	public boolean deleteProduct(String productID) {
		errorMessage = "";
		int intID = 0;
		if(productID.equals("")) {
			errorMessage += "ID cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intID = Integer.parseInt(productID);
			} catch (NumberFormatException e) {
				errorMessage += "ID must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				Product p = getProduct(intID);
				if(p == null) {
					errorMessage += "ID is not exist in database! ";
				}
			}
		}
		
		if(errorMessage.equals("")) {
			product = new Product(intID);
			return product.deleteProduct();
		}
		return false;
	}
	
	public void viewProductManagementForm() {
		new ProductManagementFormView();
	}
	//**UNFINISHED**

}
