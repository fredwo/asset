package view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import dao.impl.UserDaoImpl;
import po.Asset;
import po.Bank;
import po.User;
import service.IAssetService;
import service.IBankService;
import service.IUserService;
import service.impl.AssetServiceImpl;
import service.impl.BankServiceImpl;
import service.impl.UserServiceImpl;
import vo.allAsset;

public class UserUI {
	
	public static User currentUser = null;//当前登录用户
	private IUserService  userService = new UserServiceImpl();
	private Scanner scanner = new Scanner(System.in);
	private IAssetService assetService = new AssetServiceImpl();
	private IBankService bankService = new BankServiceImpl();

	public void userMenu() {
	
		while(true) {
			int choice = -1;
			System.out.println("*********************个人资产管理系统***********************");
			System.out.println("*********欢迎您,用户[ " + currentUser.getUserName() +" ] ,请按相应按键选择业务**********");
			System.out.println("1.查看资产 2.添加新资产 3.存取资产 4.查看总资产 5.修改密码  6.返回主菜单");
			System.out.println("请选择:");choice = scanner.nextInt();scanner.nextLine();
			
			switch(choice) {
			
			case 1://调用查看资产方法
				viewAllAsset();
				break;
			case 2://调用添加新资产方法
				addNewAsset();
				break;
			case 3://调用存取资产方法
				depositeNwithdrawlAsset();
				break;
			case 4://调用查看总资产方法
				getAssetAmount();
				break;
			case 5://调用修改密码方法
				alterPassword();
				break;
			case 6://调用返回主界面方法
				userPanel();
				break;
			}
		}
	}
	
	/**
	 * 显示查看资产列表
	 */
	public void viewAllAsset() {
		
		System.out.println("*********************个人资产管理系统***********************");
		System.out.println("***********************查 看 资 产************************");
		List<allAsset> list = assetService.viewAllAset(currentUser.getUserId());
		
		if(list.size() == 0) {
			System.err.println("您暂时没有任何财产！");
		}else {
			
			for(allAsset asset : list) {
				System.out.println(asset);
			}
		}
		
		anyKeyToReturn();
	}
	
	/**
	 * 添加新资产的UI层
	 */
	public void addNewAsset() {
		
		Asset asset = new Asset();
		
		System.out.println("*********************个人资产管理系统***********************");
		System.out.println("************************添加新资产*************************");
		System.out.println("当前系统内存在的银行信息如下:");
		
		List<Bank> listBank = bankService.getAllBanks();
		for(Bank bank : listBank) {
			System.out.println(bank);
		}
		
		System.out.println("请选择银行Id:");asset.setBankId(scanner.nextInt());scanner.nextLine();
		System.out.println("请输入银行卡号:");asset.setCardNum(scanner.nextLine());
		System.out.println("请输入金额:");asset.setCardMoney(scanner.nextDouble());
		asset.setUserId(currentUser.getUserId());
		
		if(bankService.isExists(asset.getBankId()) == false) {//不存在
			System.err.println("您输入的银行Id不存在!请查看后再输入!");
			addNewAsset();
		}else {//存在银行卡		
			
			if(assetService.isExists(asset) == false) {//银行Id下不存在该账号 可以添加资产
				
				if(assetService.addNewAsset(asset) == 1) {
					System.out.println("恭喜你，资产添加成功!");
				}else {
					System.err.println("数据库内部错误，未成功添加!");
				}
				
			}else {//存在 则 输出已存在该银行卡
				System.err.println("该银行下已存在该卡号!");
			}
			
		}
	}
	/**
	 * 存取新资产
	 */
	public void depositeNwithdrawlAsset() {
		
		//List<allAsset> list = assetService.viewAllAset(currentUser.getUserId());
		
		System.out.println("*********************个人资产管理系统***********************");
		System.out.println("**********************存取个人资产*************************");
		
		System.out.println("请选择存入\\取出 个人资产: [1].存入 [2].取出");
		int choice = -1; choice = scanner.nextInt();scanner.nextLine();
		Asset asset = new Asset();
		
		switch( choice ) {
		case 1:
			//assetService.getAllAssetByUserId(currentUser.getUserId());
			//showAllAsset(list);
			showAllBanks();
			asset = inputAssetInfo("存入");
			//handleAsset(asset,"存入",list);
			handleAsset(asset, "存入");
			break;
		case 2:
			//assetService.getAllAssetByUserId(currentUser.getUserId());
			//showAllAsset(list);
			showAllBanks();
			asset = inputAssetInfo("取出");
			//handleAsset(asset,"取出",list);
			handleAsset(asset, "取出");
			break;
		default:
			System.err.println("输入的选项有误，请重新输入!");
			depositeNwithdrawlAsset();
			break;
		}
		anyKeyToReturn();
	}
	
	public void showAllBanks() {
		
		List<Bank> list = null;
		list = bankService.getAllBanks();
		
		for(Bank bank : list) {
			
			System.out.println(bank);
		}
	}
	
	/*
	public void showAllAsset(List<allAsset> list) {
		
		if(list.size() == 0) {
			System.err.println("抱歉，您暂时没有资产！");
		}else {
			System.err.println("抱歉，您暂时没有资产！");
			for(allAsset allAsset : list) {
				System.out.println(allAsset);
			}
		}
		
	}*/
	
	/**
	 * 处理资产的业务
	 * @param asset
	 * @param type
	 * @param list
	 */
	public void handleAsset(Asset asset,String type,List<allAsset> list) {
		
		boolean flag = false;//标记是否存在资产
		allAsset allAsset = null;//查找出的要操作的资产
		
		for(allAsset asset2 : list) {
			if(asset.getBankId() == asset2.getBankId() && asset.getCardNum() == asset2.getCardNum()) {
				flag = true;
				allAsset = asset2;
				
				System.out.println("***********");
				break;
			}
		}
		
		if(flag == false) {//不表示存在该资产
			System.err.println("不存在该资产！......");
		}else {//表示存在该资产
			
			if(type.equals("存入")) {
				
				if(assetService.depositeAsset(asset) == 1) {
					System.out.println("存入资产成功!");
				}
				else {
					System.err.println("存入资产失败!");
				}
				
			}else if(type.equals("取出")) {
				
				if(allAsset.getCardMoney() < asset.getCardMoney()) {
					System.err.println("您卡里余额不足，你心里没点那啥数么!...");
				}else{
					
					if(assetService.withdrawlAsset(asset) == 1) {
						System.out.println("取出资产成功!");
					}
					else {
						System.err.println("取出资产失败!");
					}
				}
			}
		}
		
	}
	
	/**
	 * 处理资产的业务
	 * @param asset
	 * @param type
	 */
	public void handleAsset(Asset asset,String type) {
		
		allAsset allAsset = assetService.getallAssetByKey(asset.getBankId(), asset.getCardNum());
		
		if(allAsset == null) {
			System.err.println("您并无此资产!");
		}else {
			
			switch (type) {
			case "存入":
				if(assetService.depositeAsset(asset) == 1) {
					System.out.println("存入资产成功！");
				}else {
					System.err.println("数据库内部错误导致操作失败!");
				}
				break;
			case "取出":
				if(asset.getCardMoney() > allAsset.getCardMoney()) {
					System.err.println("资产不足，心里没点那啥数么!");
				}else {
					
					if(assetService.withdrawlAsset(asset) == 1) {
						System.out.println("取出资产成功！");
					}else {
						System.err.println("数据库内部错误导致操作失败!");
					}
				}
				break;
			default:
				System.err.println("输入的选项有误，请重新输入!");
				handleAsset(asset, type);
				break;
			}	
		}
		anyKeyToReturn();
		
	}
	
	/**
	 * 从键盘接收后封装asset对象 
	 * @param type字符型表示回显的字符串
	 * @return 从键盘接收后封装的asset对象 
	 * ****UI****层出错不只一次了
	 */
	public Asset inputAssetInfo(String type) {
		Asset asset = new Asset();
		long time = System.currentTimeMillis();
		System.out.println("请输入银行Id:");asset.setBankId(scanner.nextInt());scanner.nextLine();
		System.out.println("请输入银行卡号:");asset.setCardNum(scanner.nextLine());
		System.out.println("请输入" + type +"金额:");asset.setCardMoney(scanner.nextDouble());scanner.nextLine();
		asset.setCreateDate(new Date(time));
		asset.setUserId(currentUser.getUserId());
		//System.out.println(asset);
		return asset;
	}
	
	/**
	 * 查看新资产UI层
	 * 3.存取资产 4.查看总资产 注册有问题
	 */
	public void getAssetAmount() {
		
		System.out.println("*********************个人资产管理系统***********************");
		System.out.println("************************查看个人资产***********************");
		double totalMoney = assetService.getAllAssetByUserId(currentUser.getUserId());
		System.out.println("您的总资产为：" + totalMoney);
		anyKeyToReturn();
	}
	
	/**
	 * 用户层UI修改密码代码
	 * 更新密码出错代码怎么办？？？
	 * 此处代码UI层好好看看 很有必要 UI层老是出错 要么封装数据出错 要么属性值设置错 都无法得到正确的结果
	 */
	public void alterPassword() {
		
		User user = new User();
		String inputPassword = null;
		
		System.out.println("*********************个人资产管理系统***********************");
		System.out.println("***********************密 码 修 改************************");
		System.out.println("请输入当前用户的密码:");inputPassword = scanner.nextLine();
		if(verifyOldPassword(currentUser.getUserPassword(), inputPassword)) {//输入密码与原密码一致
			
			System.out.println("请输入新密码:");user.setUserPassword(scanner.nextLine());
			user.setUserId(currentUser.getUserId());
			if(userService.updateUserPassword(user)) {//修改成功
				
				//System.out.println("******当前登陆用户:" + user);
				
				System.out.println("密码修改成功!");
				/******* 出错 * ********/
				//currentUser = userService.login(user);//修改成功之后 将数据库数据取回赋值给全局变量currentUser
				currentUser.setUserPassword(user.getUserPassword());
				//System.out.println("******currentUser：" + currentUser);
			}else {//修改失败
				System.err.println("密码修改失败!");
			}
			
		}else {//入密码与原密码一致
			
			System.err.println("您输入的原始密码有误,请核对后再进行修改!");
			//userMenu();
		}
		anyKeyToReturn();
	}
	
	/**
	 * 比较输入的密码和原始的密码 是否相同 并返回结果的boolean值
	 * @param oldPassword
	 * @param inputPassword
	 * @return 输入密码与原始密码之间比较结果，true:两者相同；false：两者不同
	 */
	public boolean verifyOldPassword(String oldPassword,String inputPassword) {
		
		boolean flag = false;
		
		if(oldPassword.equals(inputPassword)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 用户注册UI层方法代码
	 * 1.用户名存在 可以继续注册 所以要while(true)
	 * 2.Column 'userPassword' cannot be null Query: insert into user(userName,userPassword,realName,sex,tel,idCard,address) values(?,?,?,?,?,?,?) Parameters: [1, null, null, null, null, null, null]
	 * 上述错误 因为粘代码导致
	 */
	public void register() {
		
		User user = new User();
		Integer userId = null;
		
		while( true ) {
			
			System.out.println("*********************个人资产管理系统***********************");
			System.out.println("*********************用 户 注 册*********************");
			System.out.println("请输入用户名:");user.setUserName(scanner.nextLine());
			System.out.println("请输入密码:");user.setUserPassword(scanner.nextLine());
			System.out.println("请输入真实姓名");user.setRealName(scanner.nextLine());
			System.out.println("请输入性别:");user.setSex(scanner.nextLine());
			System.out.println("请输入电话号码");user.setTel(scanner.nextLine());
			System.out.println("请输入身份证号:");user.setIdCard(scanner.nextLine());
			System.out.println("请输入住址");user.setAddress(scanner.nextLine());
			
			userId = userService.register(user);
			if( userId== null) {
				
				System.err.println("该用户名已存在，请重新注册!");
				
			}else {
				
				System.out.println("注册用户成功，其userID为" + userId);
				user.setUserId(userId.intValue());//传递当前用户的Id
				currentUser = user;//设置当前登陆用户的全局变量
				anyKeyToReturn();
			}
		}	
	}
	
	/**
	 * 用户登陆UI层方法
	 */
	public void login() {
		
		User user = new User();
		
		System.out.println("*********************个人资产管理系统***********************");
		System.out.println("*********************用 户 登 陆*********************");
		System.out.println("请输入用户名:");user.setUserName(scanner.nextLine());
		System.out.println("请输入密码:");user.setUserPassword(scanner.nextLine());
		
		user = userService.login(user);
		if(user != null) {//登陆成功
			currentUser = user;
			//
			System.out.println("恭喜你，用户 [ " + currentUser.getUserName() + " ] ，登陆成功!");
			userMenu();
			
		}else {
			System.err.println("登陆失败,请重新登陆.");
			UserUI userUI = new UserUI();
			userUI.login();
		}	
	}
	
	/**
	 * 按任意键返回
	 */
	public void anyKeyToReturn() {
		
		System.out.println("按1上层菜单...");
		int key = -1;
		key = scanner.nextInt();
		
		switch(key) {
		case 1:
			userMenu();
			break;
		default:
			System.err.println("输入的选项有误，请重新输入!");
			anyKeyToReturn();
			break;
		}
	}
	
	/**
	 * 用户主要选择界面
	 */
	public void userPanel() {
		
		int choice = -1;
		System.out.println("*********************个人资产管理系统***********************");
		System.out.println("*********************用 户 界 面*********************");
		System.out.println("1.登陆  2.注册 3.退出");
		System.out.println("请选择: ");choice = scanner.nextInt();scanner.nextLine();
		
		switch(choice) {
		case 1:
			login();
			break;
		case 2:
			register();
			break;
		case 3:
			System.exit(0);
			break;
		default:
			System.err.println("输入有误,请检查后重新输入!");
			userPanel();;
		}
		
		
	}
}	
