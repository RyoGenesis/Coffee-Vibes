package main;

import handlers.AuthHandler;
import handlers.ProductHandler;
import handlers.VoucherHandler;

public class Main {

	public Main() {
		//temporary
//		AuthHandler.getInstance().viewLoginForm();
//		ProductHandler.getInstance().setUser("productAdmin");
//		ProductHandler.getInstance().viewProductManagementForm();
		ProductHandler.getInstance().setUser("barista");
		ProductHandler.getInstance().viewProductManagementForm();
//		VoucherHandler.getInstance().viewVoucherManagementForm();
	}

	public static void main(String[] args) {
		new Main();
	}

}
