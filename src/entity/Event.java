package entity;
/**
 * 事件
 * @author wanghao
 *
 */
public class Event {

	private String eventID;
	private String eventName;
	private String createTime;
	private String delTime;
	private String zone;
	
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Event() {
		// TODO Auto-generated constructor stub
	}
	public Event(String e, String c, String d){
		this.eventID = e;
		this.createTime = c;
		this.delTime = d;
	}
	
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDelTime() {
		return delTime;
	}
	public void setDelTime(String delTime) {
		this.delTime = delTime;
	}
	
}
