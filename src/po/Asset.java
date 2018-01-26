package po;
/**
 * 资产记录实体类
 * @author wupeng
 * bankId cardNum  cardMoney createDate userId
 */

import java.sql.Date;

public class Asset {
	private int bankId;
	private String cardNum;
	private double cardMoney;
	private Date createDate;
	private int userId;
	
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Asset() {
		// TODO Auto-generated constructor stub
	}
	
	public Asset(int bankId,String cardNum,double cardMoney,Date createDate,int userId){
		
		this.bankId = bankId;
		this.cardNum = cardNum;
		this.cardMoney = cardMoney;
		this.createDate = createDate;
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Asset [bankId=" + bankId + ", cardNum=" + cardNum + ", cardMoney=" + cardMoney + ", createDate="
				+ createDate + ", userId=" + userId + "]";
	}
	
	
}
