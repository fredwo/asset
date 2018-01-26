package view;

import java.awt.image.BandCombineOp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.LoginContext;

import com.mysql.jdbc.log.Log;

import po.Admin;
import po.Bank;
import po.User;
import service.IAdminService;
import service.IBankService;
import service.IUserService;
import service.impl.AdminServiceImpl;
import service.impl.BankServiceImpl;
import service.impl.UserServiceImpl;

public class AdminUI {
	//初始化 UI层需要的控件对象
	private Scanner scanner = new Scanner(System.in);
	//初始化 业务层逻辑对象
	private IAdminService adminService = new AdminServiceImpl();
	//当前初始化用户 静态方法 一直可以访问
	public static Admin currentAdmin = new Admin();
	private IBankService bankService = new BankServiceImpl();
	private IUserService userService = new UserServiceImpl();
	
	/**
	 * 界面层管理员登录界面显示
	 */
	public void login() {
		
		String adminName = null;
		String adminPassword = null;
		Admin admin = new Admin();
		
		while(true) {
			int loginTime = 0;
			
			System.out.println("*********管理员登陆*********");
			System.out.println("*********用户名:");admin.setAdminName(scanner.nextLine());//adminName = scanner.nextLine();
			System.out.println("*********密码:");admin.setAdminPassword(scanner.nextLine());//adminPassword = scanner.nextLine();
			//admin = new Admin(0, adminName, adminPassword);
			//调用业务层的方法
			currentAdmin = adminService.Login(admin);
			
			if(currentAdmin != null) {
				System.out.println("*******登陆成功" + currentAdmin.getAdminName() + "*******");
				Adminmenu();
			} else {
				loginTime++;
				if(loginTime >= 3) {
					System.out.println("您已登陆三次,请稍后再登陆...");
				}else {
					System.err.println("用户名或者密码错误...");
				}
			}
		}
	}
	/**
	 * 管理员登陆菜单显示界面
	 */
	public void Adminmenu() {
		
		while( true ) {
		int choice = -1;
		System.out.println("===================欢迎您，管理员 [" + currentAdmin.getAdminName() +"] 登陆管理系统=================");
		System.out.println("1.查看所有用户  2.添加银行  3.修改银行 4.删除银行 5.查看所有银行 6.返回登陆界面 0.返回主界面");
		System.out.println("=====================================================================");
		System.out.println("请选择:");choice = scanner.nextInt();
		
		switch(choice) {
		
		case 1://调用查看所有用户方法
			getAllUsers();
			break;
		case 2://调用添加银行方法
			Addbank();
			break;
		case 3://调用修改银行方法
			updateBank();
			break;
		case 4://调用删除银行方法
			deleteBank();
			break;
		case 5://调用查看所有银行方法
			getAllBanks();
			break;
		case 6://调用返回登陆界面方法
			currentAdmin = null;
			AdminUI adminUI = new AdminUI();
			adminUI.login();
			break;
		case 0:
			MainUI mainUI = new MainUI();
			mainUI.mainMenu();
			break;
		default:
			System.err.println("输入有误,请检查后重新输入.");
			AdminUI adminUI2 = new AdminUI();
			adminUI2.Adminmenu();
			break;
			
			}
		}
	}
	
	/**
	 * 添加银行界面
	 */
	public void Addbank() {
		
		Bank bank  = new Bank();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("===================银行添加=================");
			System.out.println("请输入银行名称:");bank.setBankName(scanner.nextLine());
			System.out.println("请输入银行电话:");bank.setBankTel(scanner.nextLine());
			
			/* 调用业务层的实现方法 */
			int res = bankService.addBank(bank);
			if(res == 1) {
				System.out.println("添加银行信息成功!");
				return;
			}else if(res == -1){
				System.err.println("银行已存在,请确认后重新添加！");
				return;//调回上一级调用层级
			}
		}
	}
	
	/**
	 * 修改银行界面
	 */
	public void updateBank() {
		Bank bank  = new Bank();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("===================银行修改=================");
			System.out.println("输入要修改的银行编号:");bank.setBankId(scanner.nextInt());scanner.nextLine();
			System.out.println("输入要修改的银行名称:");bank.setBankName(scanner.nextLine());
			System.out.println("输入要修改的银行电话:");bank.setBankTel(scanner.nextLine());
			
			int res = bankService.updateBank(bank);
			if(res == -1) {
				System.err.println("银行不存在,无法更新输入的银行.");
			}else if(res == 1){
				System.out.println("银行信息更新成功，更新的银行信息:" + bank);
				return;
			}else {
				System.out.println("...未做任何修改...");
			}
		}
	}
	
	/**
	 * 删除银行界面
	 */
	
	public void deleteBank() {
		int bankId;
		Scanner scanner = new Scanner(System.in);
		int res;
		Bank bank = null;
		char choice;
		String str = null;
		
		System.out.println("===================删除银行=================");
		System.out.println("请输入要删除的银行编号:");bankId = scanner.nextInt();scanner.nextLine();//吃掉回车
		bank = bankService.getBankByBankId(bankId);
		
		if(bank == null) {
			System.err.println("输入的银行Id不存在，请重新输入!");
			
		}else {
			System.out.println("要删除的银行信息:" + bank + "确认删除么？" + " [Y/N] " );
			str = scanner.nextLine();
			choice = str.charAt(0);
			switch(choice) {
			case 'Y':
			case 'y':
				if( bankService.deleteBank(bankId) ==1) {
					System.out.println("银行删除成功!");
				}
				break;
			case 'N':
			case 'n':
				System.out.println("未删除银行信息!");
				break;
			default:
				System.err.println("输入有误请重新输入!");
				deleteBank();
			}
		}	
	}
	
	/**
	 * 查看所有银行信息
	 */
	public void getAllBanks() {
		
		List<Bank> list = new ArrayList<Bank>();
		Scanner scanner = new Scanner(System.in);
		String choice = null;
		
		list = bankService.getAllBanks();
		
		System.out.println("当前系统内所有的银行信息如下:");
		
		for(Bank bank : list) {			
			System.out.println(bank);	
		}
		
		System.out.println("按任意键返回上一层:");
		choice  = scanner.nextLine();
		
		switch(choice) {	
			default:Adminmenu();
		}
	}
	
	/**
	 * 查看所有用户信息
	 */
	public void getAllUsers() {
		
		List<User> list = new ArrayList<User>();
		Scanner scanner = new Scanner(System.in);
		String choice = null;
		
		list = userService.getAllUsers();
		
		System.out.println("当前系统内所有的银行信息如下:");
		
		for(User user: list) {			
			System.out.println(user);	
		}
		
		System.out.println("按任意键返回上一层:");
		choice  = scanner.nextLine();
		
		switch(choice) {	
			default:Adminmenu();
		}
	}
}

