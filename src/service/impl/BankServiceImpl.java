package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.IBankDao;
import dao.impl.BankDaoImpl;
import po.Bank;
import service.IBankService;
import util.JdbcUtils;
/**
 * 银行业务逻辑层实现类 addBank实现类知识点慢慢
 * @author wupeng
 *
 */
public class BankServiceImpl implements IBankService {

	private IBankDao bandDao = new BankDaoImpl();
	
	@Override
	public int addBank(Bank bank) {
		// TODO Auto-generated method stub
		Bank bank2 = bandDao.isExists(bank.getBankName());
		int res = -1;
		
		if(bank2 == null) {//银行不存在，可以添加银行信息
			if(bandDao.addBank(bank) > 0) {
				res = 1;
			}
		} /*else {//银行信息存在，不能添加 不能打印
			
			System.err.println("银行已存在,不能添加");
			System.out.println("已存在的银行信息：" + bank2);
		}*/
		return res;
	}
	
	@Override
	public int updateBank(Bank bank) {
		// TODO Auto-generated method stub
		int res = -1;
		Bank bank2 = bandDao.getBankByBankId(bank.getBankId());
		if(bank2 == null) {//说明不存在此银行
			return res;
		}else {//存在此银行
			res = bandDao.updateBank(bank);
		}	
		return res;
	}
	/**
	 * 删除银行的事务
	 * 事务处理流程 事务transaction
			 
			try {
				//开启事务
				JdbcUtils.beginTranscation();
				//删除资产
				
				//删除银行
				
				//提交事务
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//事务失败 回滚
	 */
	@Override
	public int deleteBank(int bankId) {
		// TODO Auto-generated method stub
		int res = -1;
		Bank bank2 = bandDao.getBankByBankId(bankId);
		
		if(bank2 == null) {//说明不存在此银行
			return res;
		}else {//存在此银行 进行事务 然后删除
			
			res = bandDao.deleteBank(bankId);
		}	
		
		return res;
	}

	@Override
	public List<Bank> getAllBanks() {
		// TODO Auto-generated method stub
		return bandDao.getAllBanks();
	}

	@Override
	public Bank getBankByBankId(int bankId) {
		// TODO Auto-generated method stub
		return bandDao.getBankByBankId(bankId);
	}

	@Override
	public boolean isExists(int bankId) {
		// TODO Auto-generated method stub
		Bank bank = null;
		bank = bandDao.isExists(bankId);
		boolean flag = false;
		
		if(bank != null) {
			flag = true;
		}
		
		return flag;
	}
	
}
