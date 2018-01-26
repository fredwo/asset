package service.impl;

import java.util.List;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import po.User;
import service.IUserService;

public class UserServiceImpl implements IUserService {

	private IUserDao UserDao = new UserDaoImpl();
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return UserDao.getAllUsers();
	}

	@Override
	public Integer register(User user) {
		// TODO Auto-generated method stub
		Integer userId = null;		
		
		if(UserDao.isExists(user.getUserName()) == false) {//进行注册
			
			userId = UserDao.userRegister(user);
			
		}
		
		return userId;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return UserDao.userLogin(user);
	}

	@Override
	public boolean updateUserPassword(User user) {
		// TODO Auto-generated method stub
		int res = UserDao.updateUserpassword(user);
		boolean flag = false;
		
		if(res == 0 || res == 1) {
			flag = true;
		}
		
		return flag;
	}
}
