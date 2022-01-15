package handlers;

import java.util.List;
import java.util.Vector;
import models.CartItem;
import models.Product;
import views.AddProductToCartFormView;
import views.CheckoutFormView;

public class CartHandler {
	
	public static CartHandler cartHandler = null;
	private List<CartItem> listItem;
	public CartItem cartItem;
	private String message;

	public CartHandler() {
		cartItem = new CartItem();
		listItem = new Vector<CartItem>();
		message = "";
	}
	
	public static CartHandler getInstance() {
		if (cartHandler == null) {
			cartHandler = new CartHandler();
		}
		return cartHandler;
	}

	public String getMessage() {
		return message;
	}
	
	public List<CartItem> getCart(){
		return listItem;
	}
	
	public CartItem addToCart(int productID, int quantity) {
		return null;
	}
	
	public Product getProduct(int productID) {
		return null;
	}
	
	public Product updateProductStock(int productID, int stock) {
		return null;
	}
	
	public CartItem updateCartProductQuantity(int productID, int quantity) {
		return null;
	}
	
	public boolean removeProductFromCart(int productID) {
		return true;
	}
	
	public void clearCart() {
		listItem.clear();
	}
	
	public void viewAddProductToCartForm() {
		new AddProductToCartFormView();
	}
	
	public void viewCheckoutForm() {
		new CheckoutFormView();
	}
}
