package main;

import handlers.AuthHandler;

public class Main {

	public Main() {
		//first page login
		AuthHandler.getInstance().viewLoginForm();
	}

	public static void main(String[] args) {
		new Main();
	}

}
