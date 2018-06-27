package entity;


public class Slots {

	private int index = 0;
	private String userID = null;
	
	public Slots() {
		// TODO Auto-generated constructor stub
	}
	public Slots(int i, String u) {
		// TODO Auto-generated constructor stub
		this.index = i;
		this.userID = u;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
