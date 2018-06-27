package service;


import entity.SmallTime;
import entity.Time;

import util.Date_Stamp;
/**
 * 
 * 工具类
 * @author wanghao
 *
 */
public class DealTimeUtil {
	// 处理 时间 天为单位
	public static Time[] DealTime(String eventName, String PossibleDates, String NoEarlierThan, String NoLaterThan){
		String[] dates = PossibleDates.split("#");
		int size = dates.length;
		Time[] tm = new Time[size];
		for(int i = 0; i < size; i++){
			tm[i] = new Time(eventName, dates[i], NoEarlierThan, NoLaterThan);
		}
		return tm;
	}
	//输出 时间
	public static void PrintTime(Time[] tm){
		for(int i = 0; i <tm.length; i++){
			System.out.println(tm[i].getStartT()+"  "+tm[i].getEndT());
		}
	}
	/**
	 *  处理 小时间段  
	 * @param tm
	 * @return
	 */
	public static SmallTime[] SmallInterval(Time[] tm){
		int sizes = tm.length;
		String NoEarlierThan = tm[0].getStartT();
		String NoLaterThan = tm[0].getEndT();
		int intervals = Integer.parseInt(NoLaterThan) - Integer.parseInt(NoEarlierThan);
		SmallTime[] slt = new SmallTime[4*sizes*intervals];
		for(int i = 0; i < sizes; i++){
			int tmpStamp = Date_Stamp.StrToStamp(tm[i].getDate()+"-"+NoEarlierThan+"-00");
			for(int j = 0; j < 4*intervals; j++){
				slt[4*i*intervals+j] = new SmallTime(tmpStamp, 0);
				tmpStamp += 900;
			}
		}
		return slt;
	}
	public static void PrintSmallTime(SmallTime[] slt){
		for(int i = 0; i <slt.length; i++){
			System.out.println(slt[i].getStartT());
		}
	}
}
