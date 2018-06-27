package dao;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.SmallTimeSlots;
import db.sqlHelper;
public class SmallTimeDeal {
	
	/**
	 * 返回 获取的 小 槽
	 * @param eventID
	 * @param userID
	 * @return
	 */
	public ArrayList<SmallTimeSlots> getSlotsOfEvent(String eventID, String userName){
		ArrayList<SmallTimeSlots> alsts = new ArrayList<SmallTimeSlots>();
		try{
			
			String[] str = { eventID, userName };
			ResultSet rs = sqlHelper.query("select * from selectres where eventID=? and userName=?", str);
			while(rs.next()){
				SmallTimeSlots slt = new SmallTimeSlots();
				slt.setEventID(rs.getString(1));
				slt.setUserName(rs.getString(2));
				slt.setDate(rs.getString(3));
				slt.setSlot(rs.getString(4));
				alsts.add(slt);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alsts;
	}
	
	/**
	 * 插入
	 * @param slt
	 * @return
	 */
	public boolean addSmallTime(SmallTimeSlots slt){
		try{
			
			String[] str = { slt.getEventID(), slt.getUserName(), slt.getDate(), slt.getSlot() };
			sqlHelper.update("insert into selectres values(?,?,?,?)", str);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 *  是否 已经存在
	 * @param slt
	 * @return
	 */
	public boolean SmallTimeIsExist(SmallTimeSlots slt){
		try{
			String[] str = { slt.getEventID(), slt.getUserName(), slt.getDate() };
			ResultSet rs = sqlHelper.query("select * from selectres where eventID=? and userName=? and date=?", str);
			while(rs.next()) return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 更新
	 * @param slt
	 */
	public void updateSmallTime(SmallTimeSlots slt){
		try{
			
			String[] str = { slt.getSlot(), slt.getEventID(), slt.getUserName(), slt.getDate() };
			sqlHelper.update("update selectres set slot=? where eventID=? and userName=? and date=?", str);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 *  入口
	 * @param slt
	 */
	public void modifySmallTime(SmallTimeSlots[] slt){
		try{
			SmallTimeDeal sltd = new SmallTimeDeal();
			for(int i = 0; i < slt.length; i++){
				if(!sltd.SmallTimeIsExist(slt[i])){
					sltd.addSmallTime(slt[i]);
				}else{  
					sltd.updateSmallTime(slt[i]);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
