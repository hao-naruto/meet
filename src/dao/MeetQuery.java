package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import db.sqlHelper;
import entity.Event;
import entity.Time;
public class MeetQuery {
	
	/**
	 *  查询 事件
	 * @param eventID
	 * @return
	 */
	public Event queryEvent(String eventID){
		Event event = null;
		try{
			String[] str = { eventID };
			ResultSet rs = sqlHelper.query("select * from event where eventID = ?", str);
			while(rs.next()){
				event = new Event();
				event.setEventID(rs.getString(1));
				event.setEventName(rs.getString(2));
				event.setCreateTime(rs.getString(3));
				event.setDelTime(rs.getString(4));
				event.setZone(rs.getString(5));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return event;
	}
	/**
	 * 查询 天
	 * @return
	 */
	public ArrayList<Time> queryTime(String eventID){
		ArrayList<Time> at = new ArrayList<Time>();
		try{
			String[] str = { eventID };
			ResultSet rs = sqlHelper.query("select * from eventTime where eventID = ?", str);
			
			while(rs.next()){
				Time tm = new Time();
				tm.setDate(rs.getString(1));
				tm.setStartT(rs.getString(2));
				tm.setEndT(rs.getString(3));
				tm.setEventID(rs.getString(4));
				at.add(tm);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return at;
	}
}
