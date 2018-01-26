package dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import dao.IAdminDao;
import po.Admin;
import util.JdbcUtils;

public class AdminDaoImpl implements IAdminDao {

	@Override
	public Admin Login(Admin admin) {
		// TODO Auto-generated method stub
		
		//定义sql语句
		String sql = "select * from admin where adminName=? and adminPassword=?";
		//创建参数列表数组
		Object [] params = {admin.getAdminName(),admin.getAdminPassword()};
		//创建QueryRunner对象,从数据库连接池获取数据源
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		Admin admin2 = null;
		//执行sql语句
		try {
			//将结果集中的第一行数据封装成一个javaBean的对象
			admin2 = queryRunner.query(sql, new BeanHandler<>(Admin.class),params);//将查询的结果 封装成admin对象 然后返回
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin2;
	}

}
