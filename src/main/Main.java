package main;

import handlers.AuthHandler;
import handlers.CartHandler;
import handlers.EmployeeHandler;
import handlers.ProductHandler;
import handlers.TransactionHandler;
import handlers.VoucherHandler;
import models.Employee;

public class Main {

	public Main() {
		//temporary
//		AuthHandler.getInstance().viewLoginForm();
		
		//VIEW YG BS DIPAKE BARISTA
//		ProductHandler.getInstance().setUser("barista");
//		ProductHandler.getInstance().viewProductManagementForm();
//		TransactionHandler.getInstance().setUser("barista");
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
		//cara settingnya bikin employeenya dulu (ganti parameter pertama aja)
		// 1 = product admin, 2 = manager, 3 = hrd, 4 = barista
		Employee user = new Employee(4, "Joel", 25000, "hrd", "hrd");
		AuthHandler.getInstance().setAuthUser(user);
		
		//pilih view tinggal di komen unkomen aja
//		EmployeeHandler.getInstance().viewEmployeeManagementForm();
//		ProductHandler.getInstance().viewProductManagementForm();
		CartHandler.getInstance().viewCheckoutForm();
//		TransactionHandler.getInstance().viewTransactionManagementForm();
	}

	public static void main(String[] args) {
		new Main();
	}

}
