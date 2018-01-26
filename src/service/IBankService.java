package service;

import java.util.List;

import po.Bank;

/**
 * bank业务接口层
 * @author wupeng
 *
 */
public interface IBankService {
	/**
	 * 添加银行
	 * @return 返回int表示受影响的行数 
	 * @param  bank对象
	 */
	public int addBank(Bank bank);
	
	/**
	 * 修改银行的信息
	 * @param bank对象
	 * @return 返回受影响行数
	 */
	public int updateBank(Bank bank);
	
	/**
	 * 删除银行
	 * @return 返回受影响行数
	 * @param BankId
	 */
	public int deleteBank(int bankId);
	
	/**
	 * 查看所有银行
	 * @return 银行列表
	 * @param 无参数
	 */
	public List<Bank> getAllBanks();
	
	/**
	 * 通过银行的ID返回银行对象的信息
	 * @param bankId银行的编号
	 * @return bank对象
	 */
	public Bank getBankByBankId(int bankId);
	
	/**
	 *　查看特定银行Id存在与否 
	 * @param bankId
	 * @return boolean值表示存在银行与否
	 */
	public boolean isExists(int bankId);
}
