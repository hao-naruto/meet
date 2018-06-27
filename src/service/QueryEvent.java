package service;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.MeetQuery;
import entity.Event;
import entity.Time;
/**
 * 初始化 事件
 * @author wanghao
 *
 */
public class QueryEvent {
	MeetQuery mq = null;
	
	/**
	 * 事件
	 * @param eventID
	 * @return
	 */
	public String[] getEvent(String eventID){
		MeetQuery mq = DaoFactory.getMeetQuery();
		Event event = mq.queryEvent(eventID);
		if(event != null){
			String str[] = { event.getEventID(), event.getEventName(), event.getZone() };
			//在 返回 之前
			DaoFactory.closeMeetQuery(mq);
			return str;
		}
		DaoFactory.closeMeetQuery(mq);
		return null;
	}
	/**
	 * Time 以天 为单位
	 * @param eventID
	 * @return
	 */
	public Time[] getTime(String eventID){
		MeetQuery mq = DaoFactory.getMeetQuery();
		ArrayList<Time> at = mq.queryTime(eventID);
		int size = at.size();
		Time[] tm = new Time[size];
		for(int i = 0; i < size; i++){
			tm[i] = at.get(i);
		}
		DaoFactory.closeMeetQuery(mq);
		return tm;
	}
}
