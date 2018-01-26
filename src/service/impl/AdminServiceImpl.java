package service.impl;

import dao.impl.AdminDaoImpl;
import po.Admin;
import service.IAdminService;

public class AdminServiceImpl implements IAdminService {

	private AdminDaoImpl IAdminDaoImpl = new AdminDaoImpl();	
	/**
	 * 管理员业务层实现之管理员登陆 
	 * @param admin管理员对象
	 * @return admin管理员对象
	 */
	@Override
	public Admin Login(Admin admin) {
		// TODO Auto-generated method stub
		return IAdminDaoImpl.Login(admin);
	}

}
