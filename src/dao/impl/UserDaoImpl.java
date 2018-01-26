package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.IUserDao;
import po.Bank;
import po.User;
import util.JdbcUtils;

public class UserDaoImpl implements IUserDao {

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		String sql = "select * from user";
		List<User> list = new ArrayList<User>();
		
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());	
		try {
			
			//返回包装的类型的类对象 或者列表****************************
			list = queryRunner.query(sql, new BeanListHandler<>(User.class));
			//******************************************************
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public User userLogin(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from user where userName =? and userPassword=?";
		User user2 = null;
		Object []params = {user.getUserName(),user.getUserPassword()};
		//创建DataSource的数据源
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		try {
			user2 = queryRunner.query(sql, new BeanHandler<User>(User.class),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user2;
	}

	/**
	 * 1.改用StringBuffer 怎么生成主键值呢？？？？
	 * 2.涉及到long类型对象转换成int对象 long res.intValue();
	 */
	@Override
	public Integer userRegister(User user) {
		// TODO Auto-generated method stub
		//String sql = "insert into user(userName,userPassword,realName,sex,tel,idCard,address) values(?,?,?,?,?,?,?)";
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("insert into user(userName,userPassword,realName,sex,tel,idCard,address)");
		sBuffer.append(" values(?,?,?,?,?,?,?)");
		Object []params = {user.getUserName(),user.getUserPassword(),user.getRealName(),user.getSex(),user.getTel(),user.getIdCard(),user.getAddress()};
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		Long userId = null;

		try {
			
			userId = queryRunner.insert(sBuffer.toString(), new ScalarHandler<Long>(),params);
			//可以获取主键的方法 jdbc的工具类 为了扩展性更强 返回的是long类型的 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("注册返回结果为:" + userId);
		return userId == null?0:userId.intValue();
	}

	@Override
	public boolean isExists(String userName) {
		// TODO Auto-generated method stub
		
		String sql = "select count(*) as count from user where userName= ?";
		Object []params = {userName};
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		//int res = -1;
		Object res = null;
		boolean flag = false;
		
		try {
			res = queryRunner.query(sql,new ScalarHandler<>(), params);
			//System.out.println("查询结果为:" + res );
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(Integer.parseInt(res.toString()) == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public int updateUserpassword(User user) {
		// TODO Auto-generated method stub
		String sql = "update user set userPassword = ? where userId= ?";
		Object []params = {user.getUserPassword(),user.getUserId()};
		int res = -1;
		
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			res = queryRunner.update(sql, params);
			//System.out.println("受影响的行数 " + res);
			//res = queryRunner.update(JdbcUtils.getConnection(), sql, params);
			//System.out.println("受影响的行数 " + res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
