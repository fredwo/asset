package view;

import java.sql.Date;
import java.util.List;

import dao.IUserDao;
import dao.impl.AssetDaoImpl;
import dao.impl.UserDaoImpl;
import po.Asset;
import po.User;
import service.IAssetService;
import service.IUserService;
import service.impl.AssetServiceImpl;
import service.impl.UserServiceImpl;
import vo.allAsset;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdminUI adminUI = new AdminUI();
		//adminUI.Addbank();
		//adminUI.updateBank();
		//adminUI.deleteBank();
		//adminUI.Adminmenu();
		//adminUI.getAllBanks();
		//adminUI.getAllUsers();
		IUserDao userDao = new UserDaoImpl();
		//System.out.println(userDao.isExists("WUPENG"));
		//userDao.userRegister(user)
		/*UserUI userUI = new UserUI();
		userUI.register();*/
		/*AssetDaoImpl aDaoImpl = new AssetDaoImpl();
		System.out.println(aDaoImpl.getAllAssetByUserId(1));*/
		
		IAssetService iAssetService = new AssetServiceImpl();
		
		/*List<allAsset> list = iAssetService.viewAllAset(1);
		for(allAsset allAsset : list) {
			System.out.println(allAsset);
		}*/
		
		//System.out.println("总资产为:" + iAssetService.getAllAssetByUserId(1));
		//将数据库表修改成double(12,2)即可表示
		/**System.out.println("总资产为:" + iAssetService.getAllAssetByUserId(1));
		 * Exception in thread "main" java.lang.ClassCastException: java.math.BigDecimal cannot be cast to java.lang.Double
		   at dao.impl.AssetDaoImpl.getAllAssetByUserId(AssetDaoImpl.java:31)
		   at service.impl.AssetServiceImpl.getAllAssetByUserId(AssetServiceImpl.java:22)
		   at view.MainTest.main(MainTest.java:37)
		 */
		
		
		//****设置userId构造方法的userId写错成useId****
		//int bankId,String cardNum,double cardMoney,Date createDate,int useId
		//long currentTime =  System.currentTimeMillis();
		//Asset asset = new Asset(6,"699600201",20000,new Date(currentTime),1);
		//asset.setUserId(1);
		//asset.setCardMoney(100);
		//iAssetService.addNewAsset(asset);
		//System.out.println(iAssetService.depositeAsset(asset) + "受影响的行数");
		//System.out.println(iAssetService.withdrawlAsset(asset) + "受影响的行数");
		
		//test4user 
		IUserService UserService = new UserServiceImpl();
		
		/*
		 * *
		 * *获得所有用户信息的方法测试
		List<User> list = UserService.getAllUsers();
		for(User user : list) {
			
			System.out.println("UserInfo: " + user);
		}
	  */
		/**
		 * user方法的Login的测试
		User user = new User();
		user.setUserName("WUPENG");
		user.setUserPassword("123456");
		User user2 = UserService.login(user);
		System.out.println("登陆用户的信息： " + user2);
		 */
		
		/**
		 * user方法register测试
		User user = new User("ZZZ","121212","WUP","M","110","1221","Zibo");
		int res = UserService.register(user);
		System.out.println("生成的主键Id " + res);
		*/
		/**
		AssetDaoImpl assetDaoImpl = new AssetDaoImpl();
		assetDaoImpl.getallAssetByKey(5, "1100010");
		**/
		//System.out.println(iAssetService.getallAssetByKey(5, "1100010"));
		UserUI userUI = new UserUI();
		
		/*
		 * Asset asset = new Asset();
		
		asset.setBankId(5);
		asset.setCardNum("1100010");
		asset.setCardMoney(10);
		String type = "取出";
		userUI.handleAsset(asset, type);
		 */
		//System.out.println(userUI.inputAssetInfo("转出"));
		
		/**更新用户密码测试
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		User user = new User();
		user.setUserId(1);
		user.setUserPassword("1111");
		userDaoImpl.updateUserpassword(user);
		*/
		
		/**
		 *查看所有资产的测试 
		 */
		AssetDaoImpl assetDaoImpl = new AssetDaoImpl();
		
		System.out.println(assetDaoImpl.getAllAssetByUserId(1));
	}

}
