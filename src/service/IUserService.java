package service;

import java.util.List;

import po.User;

public interface IUserService {
	
	/**
	 * 用户业务登陆方法
	 * @param user对象 用来验证用户名及密码
	 * @return 数据库查询出的user对象
	 */
	public User login(User user);
	
	/**
	 * 用户业务层注册方法声明
	 * @param user
	 * @return user生成的Id
	 */
	public Integer register(User user);
	
	/**
	 * 用户业务层查看所有用户并封装列表
	 * @return List<User>
	 */
	public List<User> getAllUsers();
	
	/**
	 * 用户业务层更新用户的密码
	 * @param 用户更新对象
	 * @return 更新对象成功与否的boolean值 true：更新成功;false:更新不成功
	 */
	public boolean updateUserPassword(User user);
}
