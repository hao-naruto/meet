package entity;
/**
 * 以天为单位的 日期
 * @author wanghao
 * 
 */
public class Time {
	String eventID = null;
	String date = null;
	String startT = null;
	String endT = null;
	public Time() {
		// TODO Auto-generated constructor stub
	}
	public Time(String e, String d, String s, String en) {
		// TODO Auto-generated constructor stub
		this.eventID = e;
		this.date = d;
		this.startT = s;
		this.endT = en;
	}
	public String getStartT() {
		return startT;
	}
	public void setStartT(String startT) {
		this.startT = startT;
	}
	public String getEndT() {
		return endT;
	}
	public void setEndT(String endT) {
		this.endT = endT;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	
}