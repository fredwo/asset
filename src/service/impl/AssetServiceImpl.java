package service.impl;

import java.util.List;

import dao.IAssetDao;
import dao.impl.AssetDaoImpl;
import po.Asset;
import service.IAssetService;
import vo.allAsset;
/**
 * 资产管理业务逻辑层
 * @author wupeng
 *
 */
public class AssetServiceImpl implements IAssetService {

	private IAssetDao assetDao = new AssetDaoImpl();
	
	@Override
	public double getAllAssetByUserId(int userId) {
		// TODO Auto-generated method stub
		//if(assetDao.getAllAssetByUserId(userId) == null)
		return assetDao.getAllAssetByUserId(userId);
	}

	@Override
	public int addNewAsset(Asset asset) {
		// TODO Auto-generated method stub
		return assetDao.addNewAsset(asset);
	}

	@Override
	public int depositeAsset(Asset asset) {
		// TODO Auto-generated method stub
		return assetDao.depositeAsset(asset);
	}

	@Override
	public int withdrawlAsset(Asset asset) {
		// TODO Auto-generated method stub
		return assetDao.withdrawlAsset(asset);
	}

	@Override
	public List<allAsset> viewAllAset(int userId) {
		// TODO Auto-generated method stub
		return assetDao.viewAllAset(userId);
	}

	@Override
	public boolean isExists(Asset asset) {
		// TODO Auto-generated method stub
		return assetDao.isExists(asset);
	}

	@Override
	public allAsset getallAssetByKey(int bankId, String cardNum) {
		// TODO Auto-generated method stub
		return assetDao.getallAssetByKey(bankId, cardNum);
	}

}
