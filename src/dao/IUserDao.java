package dao;
/**
 * 用户dao层接口：数据库用户表访问方法的定义
 * @author wupeng
 *
 */

import java.util.List;

import po.User;

public interface IUserDao {
	
	/**
	 * 用户登陆方法
	 */
	public User userLogin(User user);
	
	/**
	 * 用户注册方法
	 */
	public Integer userRegister(User user);
	
	/**
	 * 查看所有的用户
	 * @return 用户对象的list，装载数据库中所有的用户
	 * @param 无参数
	 */
	public List<User> getAllUsers();
	
	/**
	 * 查看用户名是否存在 返回用户名是否存在的boolean值 
	 * true：用户名存在
	 * false:用户名不存在
	 * @param 用户名
	 * @return 标记用户名存在的boolean值
	 */
	public boolean isExists(String userName);
	
	/**
	 * 修改用户密码
	 * @param user对象
	 * @return 更新用户密码后受影响的行数
	 */
	public int updateUserpassword(User user);
}
