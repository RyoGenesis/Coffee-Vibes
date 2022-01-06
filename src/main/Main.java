package main;

import handlers.AuthHandler;

public class Main {

	public Main() {
		//temporary
		AuthHandler.getInstance().viewLoginForm();
	}

	public static void main(String[] args) {
		new Main();
	}

}
