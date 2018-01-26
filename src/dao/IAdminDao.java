package dao;

import po.Admin;

/**
 * 管理员账户的方法接口
 * @author wupeng
 *
 */
public interface IAdminDao {
	/**
	 * 管理员登陆方法
	 * @param Admin对象
	 * @return Admin对象
	 */
	public Admin Login(Admin admin);
	
	/**
	 * 注册添加管理员用户
	 * @param Admin对象
	 * @return Admin对象的AdminId返回
	 */
}
