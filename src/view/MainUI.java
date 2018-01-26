package view;

import java.util.Scanner;

public class MainUI {

	private int choice;
	private Scanner scanner = new Scanner(System.in);
	private UserUI userUI = new UserUI();
	private AdminUI adminUI = new AdminUI();
	
	public void mainMenu() {
		
		System.out.println("*********************个人资产管理系统***********************");
		System.out.println("*********************1.管理员*********************");
		System.out.println("*********************2.用户*********************");
		System.out.println("*********************0.退出*********************");
		System.out.println("请选择:");choice = scanner.nextInt();scanner.nextLine();
		
		switch (choice) {
		case 1:
			adminUI.login();
			break;
		case 2:
			userUI.userPanel();
			break;
		case 0:
			System.exit(0);
			break;
		default:
			System.err.println("输入有误,请检查后重新输入.");
			MainUI mainUI = new MainUI();
			mainUI.mainMenu();
		}
	}
}
