package service;

import java.util.List;

import po.Asset;
import vo.allAsset;

public interface IAssetService {
	
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
	 * @param 无参数
	 * @return 所有资产列表
	 */
	public List<allAsset> viewAllAset(int userId);
	
	/**
	 * 标记资产 BankId 与 cardNum是否存在函数
	 * @param asset
	 * @return 表示资产存在与否的标记
	 */
	public boolean isExists(Asset asset);
	
	/**
	 * 通过主键查看资产
	 * @param bankId
	 * @param cardNum
	 * @return
	 */
	public allAsset getallAssetByKey(int bankId,String cardNum);
}
