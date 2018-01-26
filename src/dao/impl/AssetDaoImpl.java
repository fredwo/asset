package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Assertions;

import dao.IAssetDao;
import po.Asset;
import po.Bank;
import util.JdbcUtils;
import vo.allAsset;

public class AssetDaoImpl implements IAssetDao {

	@Override
	public double getAllAssetByUserId(int userId) {
		// TODO Auto-generated method stub
		
		String sql = "select sum(cardMoney) as totalMoney from asset where userId =?";
		Object params[] = {userId};
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		//Double totalMoney = null;
		Double totalMoney = null;
		double res = 0;
		//jdk1.5以后 double与Double自动转换
		try {
			totalMoney = queryRunner.query(sql,new ScalarHandler<Double>(),params);
			//new ScalarHandler<Double>()不给参数 默认 第一行第一列
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(totalMoney != null) {
			res = totalMoney.doubleValue();
		}
		
		return res;
	}

	@Override
	public int addNewAsset(Asset asset) {
		// TODO Auto-generated method stub
		String sql = "insert into asset(bankId,cardNum,cardMoney,createDate,userId) values(?,?,?,now(),?)";
		Object []params = {asset.getBankId(),asset.getCardNum(),asset.getCardMoney(),asset.getUserId()};
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		int res = -1;
		
		try {
			res = queryRunner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	/**
	 * 			//返回包装的类型的类对象 或者列表****************************
				list = queryRunner.query(sql, new BeanListHandler<>(Bank.class));
				//******************************************************
				list使用总结：
				list = queryRunner.query(sql.toString(), new BeanListHandler<allAsset>(allAsset.class), params);
	 */
	@Override
	public List<allAsset> viewAllAset(int userId) {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer();
		sql.append("select u.userId,userName,idCard,b.bankId,bankName,cardNum,cardMoney,createDate ");
		sql.append("from asset a inner join user u on a.userId= u.userId inner join bank b on b.bankId=a.bankId where u.userId=?");
		Object [] params = {userId};
		
		List<allAsset> list = new ArrayList<allAsset>();
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		try {
			
			list = queryRunner.query(sql.toString(), new BeanListHandler<allAsset>(allAsset.class), params);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int depositeAsset(Asset asset) {
		// TODO Auto-generated method stub
		String sql = "update asset set cardMoney = cardMoney + ? where bankId =? and cardNum =?";
		
		Object [] params = {asset.getCardMoney(),asset.getBankId(),asset.getCardNum()};
		int  res = -1;
		
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			res = queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int withdrawlAsset(Asset asset) {
		// TODO Auto-generated method stub
		
		String sql = "update asset set cardMoney = cardMoney - ? where bankId =? and cardNum =?";
		
		Object [] params = {asset.getCardMoney(),asset.getBankId(),asset.getCardNum()};
		int  res = -1;
		
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			res = queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return res;
	}

	@Override
	public boolean isExists(Asset asset) {
		// TODO Auto-generated method stub
		
		String sql = "select count(*) from asset where bankId=? and cardNum= ?";
		Object []params = {asset.getBankId(),asset.getCardNum()};
		boolean flag = false;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		Object res = null;
		
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
	public allAsset getallAssetByKey(int bankId, String cardNum) {
		// TODO Auto-generated method stub
		allAsset allAsset = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select u.userId,userName,idCard,b.bankId,bankName,cardNum,cardMoney,createDate ");
		sql.append("from asset a inner join user u on a.userId= u.userId inner join bank b on b.bankId=a.bankId where a.bankId=? and a.cardNum=? ");
		Object [] params = {bankId,cardNum};
	
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		//System.out.println(sql.toString());
		
		try {
			allAsset = queryRunner.query(sql.toString(), new BeanHandler<>(allAsset.class), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(allAsset);
		
		return allAsset;
	}

}
