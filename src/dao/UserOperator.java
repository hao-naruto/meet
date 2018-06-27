package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import entity.User;
import db.sqlHelper;
public class UserOperator {
	
	
	public User queryUser(User u){
		User us = new User();
		try{
			
			String[] str = { u.getEventID(), u.getUserID() };
 			ResultSet rs = sqlHelper.query("select * from user where eventID=? and userID=?", str);
 			while(rs.next()){
 				us.setUserID(rs.getString(1));
 				us.setUserName(rs.getString(2));
 				us.setPassword(rs.getString(3));
 				us.setEventID(rs.getString(4));
 			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return us;
	}
	/**
	 * 返回 获取 的 user
	 * @param user
	 * @return
	 */
	public ArrayList<User> getUsersOfEvent(User user){
		ArrayList<User> alu = new ArrayList<User>();
		try{
			
			String[] str = { user.getEventID() };
 			ResultSet rs = sqlHelper.query("select * from user where eventID=?", str);
			
 			while(rs.next()){
 				User us = new User();
 				us.setUserID(rs.getString(1));
 				us.setUserName(rs.getString(2));
 				us.setPassword(rs.getString(3));
 				us.setEventID(rs.getString(4));
 				alu.add(us);
 			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return alu;
	}
	
	
	/**
	 *  查询 用户 手否 存在
	 * @param user
	 * @return
	 */
	public boolean queryUserExist(User user){
		
		try{
			
			String[] str = { user.getUserName(), user.getEventID() };
			ResultSet rs = sqlHelper.query("select * from user where userName=? and eventID=?", str);
			while(rs.next()) return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 *  查询 密码 是否正确
	 * @param user
	 * @return
	 */
	public boolean queryUserIsTrue(User user){
		
		try{
			
			String[] str = { user.getUserName(), user.getPassword(), user.getEventID()};
			ResultSet rs = sqlHelper.query("select * from user where userName=? and password=? and eventID=?", str);
			while(rs.next()) return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 *   添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user){
		
		try{
			
			String[] str = { user.getUserID(), user.getUserName(), user.getPassword(), user.getEventID()};
			sqlHelper.update("insert into user values(?,?,?,?)", str);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 *  用户登录 操作
	 * @param user
	 * @return
	 */
	public boolean userLoginIsOk(User user){
		UserOperator uo = null;
		try{
			
			uo = DaoFactory.getUserOperator();
			// 用户已存在
			if(uo.queryUserExist(user)){
				if(uo.queryUserIsTrue(user)){
					DaoFactory.closeUserOperator(uo);
					return true;
				}else{
					DaoFactory.closeUserOperator(uo);
					return false;
				}
			}else{  //用户 不存在
				if(uo.addUser(user)){
					DaoFactory.closeUserOperator(uo);
					return true;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		DaoFactory.closeUserOperator(uo);
		return false;
	}
	
}
