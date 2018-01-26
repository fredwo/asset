package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import service.IAssetService;
import service.impl.AssetServiceImpl;

class test4Asset {

	IAssetService AssetService = new AssetServiceImpl();
	
	@Test
	void testGetAllAssetByUserId() {
	
		System.out.println("总资产为:" + AssetService.getAllAssetByUserId(1));
	}

	@Test
	void testAddNewAsset() {
		fail("Not yet implemented");
	}

	@Test
	void testDepositeAsset() {
		fail("Not yet implemented");
	}

	@Test
	void testWithdrawlAsset() {
		fail("Not yet implemented");
	}

	@Test
	void testViewAllAset() {
		fail("Not yet implemented");
	}

}
