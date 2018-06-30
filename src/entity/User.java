package entity;
import util.MD5;
/**
 *  用户
 * @author wanghao
 *
 */
public class User {
	
	private String userID = null;
	private String userName = null;
	private String password = null;
	private String eventID = null;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String u, String un, String p, String e) {
		// TODO Auto-generated constructor stub
		this.userID = u;
		this.userName = un;
		this.password = p;
		this.eventID = e;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = MD5.md5(password);
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
