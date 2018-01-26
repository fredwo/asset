package dao;

import java.util.List;

import po.Bank;

/**
 * 对银行操作的底层接口
 * @author wupeng
 *
 */
public interface IBankDao {
	
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
	 * 根据银行名称查找银行对象是否存在，存在返回银行对象，不存在则返回空对象
	 * @param 银行名
	 * @return bank对象
	 */
	public Bank isExists(String bankName);
	
	/**
	 * 根据银行名称查询银行的ID主键 返回在数据库中存在的主键的数值
	 * @param 银行Id
	 * @return 银行对象
	 */
	public Bank getBankByBankId(int bankId);
	
	/**
	 * 根据传入的银行的ID判断银行是否存在
	 * @param 银行的Id
	 * @return 银行实体对象
	 */
	public Bank isExists(int bankId);
}
