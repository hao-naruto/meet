package service;

import java.util.ArrayList;
import java.util.HashMap;

import entity.Slots;
import entity.SmallTimeSlots;
import entity.User;

/**
 * 工具类
 * @author wangwei
 *
 */
public class DealSlots {

	public static ArrayList<Slots> getAllSlots(ArrayList<SmallTimeSlots> alsts, ArrayList<User> alu){
		
		ArrayList<Slots> als = new ArrayList<Slots>();
		HashMap<String, String> hm = new HashMap<String, String>();
		for(int i = 0; i < alu.size(); i++){
			hm.put(alu.get(i).getUserName(), alu.get(i).getUserID());
		}
		
		int size = alsts.size();
		int lengthofone = alsts.get(0).getSlot().length();
		int col = 0;
		
		
		String oneName = alsts.get(0).getUserName();
		for(int i = 0; i < size; i++){
			if(alsts.get(i).getUserName().equals(oneName)){
				col++;
			}
		}
		
		for(int i = 0; i < lengthofone; i++){
			for(int j = 0; j < size; j++){
				if(alsts.get(j).getSlot().charAt(i) == '1'){
					Slots s = new Slots();
					//设置 ID
					s.setUserID(hm.get(alsts.get(j).getUserName()));
					int index = i + (j % col) * lengthofone;
					s.setIndex(index);
					als.add(s);
				}
			}
		}
		
		return als;
	}
}
