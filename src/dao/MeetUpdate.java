package dao;

import db.sqlHelper;
import entity.Event;
import entity.Time;
public class MeetUpdate {
	
	public boolean addEvent(Event event){
		
		try{
			
			String sql = "insert into event values(?,?,?,?,?)";
			String[] str = { event.getEventID(),  event.getEventName(), event.getCreateTime(), 
					event.getDelTime(), event.getZone() };
			sqlHelper.update(sql, str);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean addEventTime(Time time){
		
		try{
			
			String sql = "insert into eventTime values(?,?,?,?)";
			String[] str = { time.getDate(), time.getStartT(), time.getEndT(), time.getEventID() };
			sqlHelper.update(sql, str);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}

}
