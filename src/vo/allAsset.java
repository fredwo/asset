package vo;
/**
 * 查询所有资产列表的视图类
 * @author wupeng
 *
 */

import java.sql.Date;

public class allAsset {

	private int userId;
	private String userName;
	private String idCard;
	private int bankId;
	private String bankName;
	private String cardNum;
	private double cardMoney;
	private Date createDate;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public double getCardMoney() {
		return cardMoney;
	}
	public void setCardMoney(double cardMoney) {
		this.cardMoney = cardMoney;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return "allAsset [userId=" + userId + ", userName=" + userName + ", idCard=" + idCard + ", bankId=" + bankId
				+ ", bankName=" + bankName + ", cardNum=" + cardNum + ", cardMoney=" + cardMoney + ", createDate="
				+ createDate + "]";
	}
}
