package handlers;

import java.util.List;
import java.util.Vector;
import models.CartItem;
import models.Product;
import views.CartManagementFormView;
import views.TransactionManagementFormView;

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
	
	Product product = null;
	public CartItem addToCart(String productID, String quantity) {
		message = "";
		int intProductId = 0;
		if(productID.equals("")) {
			message += "Product ID cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intProductId = Integer.parseInt(productID);
			} catch (NumberFormatException e) {
				message += "Product ID must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				product = ProductHandler.getInstance().getProduct(intProductId);
				if(product == null) {
					message += "Product is not exist in database! ";
				}
			}
		}
		int intQty = 0;
		if(quantity.equals("")) {
			message += "Quantity cannot be empty! ";
		}
		else {
			boolean canParse = true;
			try {
				intQty = Integer.parseInt(quantity);
			} catch (NumberFormatException e) {
				message += "Quantity must be numeric! ";
				canParse = false;
			}
			if(canParse) {
				if(intQty < 1) {
					message += "Quantity cannot be less than one! ";
				}
				else {
					if(product != null) {
						if(product.getStock() < intQty) {
							message += "Quantity insufficient! ";
						}
					}
				}
			}
		}
		
		if(message.equals("")) {
			if(getProduct(intProductId) != null) {
				cartItem = updateCartProductQuantity(intProductId, intQty);
			}
			else {
				cartItem = new CartItem(product, intQty);
				listItem.add(cartItem);
			}
			
			if(message.equals("")) {
				message = "Successfully add to cart!";
				return cartItem;
			}
		}
		return null;
	}

	public Product getProduct(int productID) {
		for (int i = 0; i < listItem.size(); i++) {
			if(listItem.get(i).getProduct().getProductID() == productID) {
				return listItem.get(i).getProduct();
			}
		}
		return null;
	}

	public Product updateProductStock(int productID, int stock) {
		return null;
	}

	public CartItem updateCartProductQuantity(int productID, int quantity) {
		for (int i = 0; i < listItem.size(); i++) {
			if(listItem.get(i).getProduct().getProductID() == productID) {
				int qty = listItem.get(i).getQuantity() + quantity;
				if(qty <= product.getStock()) {
					listItem.get(i).setQuantity(qty);
				}
				else {
					message += "Quantity insufficient! ";
				}
				return listItem.get(i);
			}
		}
		return null;
	}
	
	public boolean removeProductFromCart(int productID) {
		return true;
	}
	
	public void clearCart() {
		listItem.clear();
	}
	
	public void viewAddProductToCartForm() {
		new CartManagementFormView();
	}
	
	public void viewCheckoutForm() {
		new TransactionManagementFormView();
	}
}
