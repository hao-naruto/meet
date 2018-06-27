package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dao.DaoFactory;
import dao.MeetUpdate;
import entity.Event;
import entity.Time;

public class AddEvent {
	
	/**
	 * 增加 新事件
	 * @param eventName
	 * @param zone
	 * @return
	 */
	public boolean addNewEvent(String eventID, String eventName,String zone){
		Event event = new Event();
		event.setEventName(eventName);
		event.setZone(zone);
		event.setEventID(eventID);
		event.setCreateTime(getNowDate());
		event.setDelTime(getNextDate());
		
		MeetUpdate mu = DaoFactory.getMeetUpdate();
		mu.addEvent(event);
		DaoFactory.closeMeetUpdate(mu);
		return true;
	}
	/**
	 *  存储 以天 为单位的  时间段
	 * @param eventName  
	 * @param Dates
	 * @param earlier
	 * @param later
	 * @return
	 */
	public boolean addEventTime(String eventID, String Dates, String earlier, String later){
		Time[] tm = DealTimeUtil.DealTime(eventID, Dates, earlier, later);
		
		MeetUpdate mu = DaoFactory.getMeetUpdate();
		for(int i = 0; i < tm.length; i++){
			mu.addEventTime(tm[i]);
		}
		DaoFactory.closeMeetUpdate(mu);
		return true;
	}
	/**
	 * 获取  当前时间 工具类
	 * @return
	 */
	public static String getNowDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String dstr = sdf.format(d);
		return dstr;
	}
	/**
	 * 获取 后一个月  工具类
	 * @return
	 */
	public static String getNextDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		// 操作 时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, 1);
		d = calendar.getTime();
		String dstr = sdf.format(d);
		return dstr;

	}
}
