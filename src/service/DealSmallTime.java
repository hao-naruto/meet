package service;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.SmallTimeDeal;
import entity.SmallTimeSlots;

public class DealSmallTime {
	/**
	 * 更新
	 * @param slts
	 * @return
	 */
	public boolean updateSmall(SmallTimeSlots[] slts){
		// 工厂模式 产生 对象
		SmallTimeDeal std = DaoFactory.getSmallTimeDeal();
		std.modifySmallTime(slts);
		// 回收对象
		DaoFactory.closeSmallTimeDeal(std);
		return false;
	}
	
	public ArrayList<SmallTimeSlots> getSmallSlots(String eventID, String userName){
		ArrayList<SmallTimeSlots> alsts = new ArrayList<SmallTimeSlots>();
		SmallTimeDeal std = DaoFactory.getSmallTimeDeal();
		alsts = std.getSlotsOfEvent(eventID, userName);
		DaoFactory.closeSmallTimeDeal(std);
		return alsts;
	}

}
