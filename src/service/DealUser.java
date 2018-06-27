package service;

import java.util.ArrayList;

import entity.User;
import dao.DaoFactory;
import dao.UserOperator;
public class DealUser {
	
	/**
	 * 用户 登录
	 * @param userId
	 * @param password
	 * @param eventID
	 * @return
	 */
	public boolean userLogin(String userId, String userName, String password, String eventID){
		UserOperator uo = DaoFactory.getUserOperator();
		User user = new User();
		user.setUserID(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setEventID(eventID);
		
		// 登录 成功
		if(uo.userLoginIsOk(user)){
			DaoFactory.closeUserOperator(uo);
			return true;
		}
		DaoFactory.closeUserOperator(uo);
		return false;
	}
	/**
	 * 获取 事件的用户
	 * @param eventID
	 */
	public ArrayList<User> getUsers(String eventID){
		
		UserOperator uo = DaoFactory.getUserOperator();
		User user = new User();
		user.setEventID(eventID);
		ArrayList<User> alu = uo.getUsersOfEvent(user);
		
		DaoFactory.closeUserOperator(uo);
		return alu;
		
	}
	/**
	 * 查询用户
	 * @param eventID
	 */
	public User getUsers(String eventID, String userID){
		
		UserOperator uo = DaoFactory.getUserOperator();
		User user = new User();
		user.setEventID(eventID);
		user.setUserID(userID);
		User user1 = uo.queryUser(user);
		
		DaoFactory.closeUserOperator(uo);
		return user1;
		
	}
}
