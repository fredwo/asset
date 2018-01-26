package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.IBankDao;
import po.Bank;
import util.JdbcUtils;

public class BankDaoImpl implements IBankDao {

	@Override
	public int addBank(Bank bank) {
		// TODO Auto-generated method stub
		int res = -1;
		//准备sql语句
		String sql = "insert into bank(bankName,bankTel) values(?,?)";
		//准备预编译sql语句的参数
		Object params [] = {bank.getBankName(),bank.getBankTel()};
		//创建一个queryRunner对象 传递一个datasourse类型参数
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			res = queryRunner.update(sql,params);
			//System.out.println("=======打印的更新添加的行数:[ " + res +" ]===");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int updateBank(Bank bank) {
		// TODO Auto-generated method stub
		int res = -1;
		//准备sql语句
		String sql = "update bank set bankName=? , bankTel=? where bankId=?";
		//准备预编译sql语句的参数
		Object params [] = {bank.getBankName(),bank.getBankTel(),bank.getBankId()};
		//创建一个queryRunner对象 传递一个datasourse类型参数
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			res = queryRunner.update(sql,params);
			//System.out.println("=======打印更新添加行数:[ " + res +" ]===");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return res;
	}

	@Override
	//涉及事务处理
	public int deleteBank(int bankId) {
		// TODO Auto-generated method stub
		String sql1 = "delete from bank where bankId=?";//删除银行sql
		String sql2 = "";//删除银行相关的记录信息sql
		int res = -1;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		try {
			res = queryRunner.update(JdbcUtils.getConnection(),sql1,bankId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//传输一个参数的情况
		
		return res;
	}

	@Override
	public List<Bank> getAllBanks() {
		// TODO Auto-generated method stub
		String sql = "select * from bank";
		List<Bank> list = new ArrayList<Bank>();
		
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());	
		try {
			
			//返回包装的类型的类对象 或者列表****************************
			list = queryRunner.query(sql, new BeanListHandler<>(Bank.class));
			//******************************************************
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Bank isExists(String bankName) {
		// TODO Auto-generated method stub
		String sql = "select * from bank where bankName= ?";
		Object []params = {bankName};
		Bank bank = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		try {
			bank = queryRunner.query(sql, new BeanHandler<Bank>(Bank.class),params);//传递的是BeanHandler对象
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bank;
	}

	@Override
	public Bank getBankByBankId(int bankId) {
		// TODO Auto-generated method stub
		String sql = "select * from bank where bankId= ?";
		Object []params = {bankId};
		Bank bank = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		try {
			bank = queryRunner.query(sql, new BeanHandler<Bank>(Bank.class),params);//传递的是BeanHandler对象
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return bank;
	}

	@Override
	public Bank isExists(int bankId) {
		// TODO Auto-generated method stub
		String sql = "select * from bank where bankId= ?";
		Object []params = {bankId};
		Bank bank = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		try {
			bank = queryRunner.query(sql, new BeanHandler<Bank>(Bank.class),params);//传递的是BeanHandler对象
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return bank;
	}

}
