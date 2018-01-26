package po;
/**
 * admin管理员账户实体类
 * @author wupeng
 * adminId adminName adminPassword
 */
public class Admin {
	private int adminId;
	private String adminName;
	private String adminPassword;
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	public Admin(int adminId,String adminName,String adminPassword){
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword =adminPassword;
	}
	
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPassword=" + adminPassword + "]";
	}
	
	
}
