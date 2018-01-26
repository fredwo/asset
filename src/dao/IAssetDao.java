package dao;

import java.util.List;

import com.mysql.jdbc.StreamingNotifiable;

import po.Asset;
import vo.allAsset;

/**
 * 资产Dao层接口
 * @author wupeng
 *
 */

public interface IAssetDao {
	
	/**
	 * 获取用户的总资产<查看资产总数额>
	 * @param userId
	 * @return double类型的总资产数量
	 */
	public double getAllAssetByUserId(int userId);
	
	/**
	 * 添加新资产
	 * @param 资产类型对象
	 * @return 添加的行数
	 */
	public int addNewAsset(Asset asset);
	
	/**
	 * 存新资产
	 * @param 存资产的量化信息
	 * @return 存资产更新操作执行后受影响的行数
	 */
	public int depositeAsset(Asset asset);
	
	/**
	 * 取新资产
	 * @param  取资产的量化信息
	 * @return 取资产更新操作执行后受影响的行数
	 */
	public int withdrawlAsset(Asset asset);
	
	/**
	 * 查看总资产<查看资产列表>
	 * @param 用户Id
	 * @return 所有资产列表
	 */
	public List<allAsset> viewAllAset(int userId);
	
	/**
	 * 查看对应银行的银行卡是否存在
	 * @param 资产对象 主要用到的参数是bankId和cardNum
	 */
	public boolean isExists(Asset asset);
	
	/**
	 * 通过查询BankId和CardNum查询反馈的allAsset信息
	 */
	public allAsset getallAssetByKey(int bankId,String cardNum);
}
