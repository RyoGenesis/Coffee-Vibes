package main;

import handlers.AuthHandler;
import handlers.EmployeeHandler;
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
//		EmployeeHandler.getInstance().viewEmployeeManagementForm("Human Resource Management");
	}

	public static void main(String[] args) {
		new Main();
	}

}
