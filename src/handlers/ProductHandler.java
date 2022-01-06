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
		return null;
	}
	
	public Product insertProduct(String name, String description, int price, int stock) {
		return null;
	}
	
	public Product updateProduct(int productID, String name, String description, int price) {
		return null;
	}
	
	public Product updateProductStock(int productID, int stock) {
		return null;
	}
	
	public boolean deleteProduct(int productID) {
		return true;
	}
	
	public void viewProductManagementForm() {
		new ProductManagementFormView();
	}
	//**UNFINISHED**

}
