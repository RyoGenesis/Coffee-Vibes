package main;

import handlers.AuthHandler;
import handlers.CartHandler;
import handlers.EmployeeHandler;
import handlers.ProductHandler;
import handlers.TransactionHandler;
import handlers.VoucherHandler;

public class Main {

	public Main() {
		//temporary
//		AuthHandler.getInstance().viewLoginForm();
		
		//VIEW YG BS DIPAKE BARISTA
//		ProductHandler.getInstance().setUser("barista");
//		ProductHandler.getInstance().viewProductManagementForm();
//		TransactionHandler.getInstance().setUser("barista");;
//		TransactionHandler.getInstance().viewTransactionManagementForm();
//		CartHandler.getInstance().viewCheckoutForm(); //ini cart management form
		
		//VIEW YG BS DIPAKE PRODUCT ADMIN
//		ProductHandler.getInstance().setUser("productAdmin");
//		ProductHandler.getInstance().viewProductManagementForm();
//		VoucherHandler.getInstance().viewVoucherManagementForm();
		
		//VIEW YG BS DIPAKE MANAGER
//		TransactionHandler.getInstance().setUser("manager");
//		TransactionHandler.getInstance().viewTransactionManagementForm();
//		EmployeeHandler.getInstance().viewEmployeeManagementForm("Manager");
		
		//VIEW YG BS DIPAKE HRD
//		EmployeeHandler.getInstance().viewEmployeeManagementForm("Human Resource Management");
	}

	public static void main(String[] args) {
		new Main();
	}

}
