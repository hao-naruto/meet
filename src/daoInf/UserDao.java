package daoInf;

import java.util.ArrayList;

import entity.User;

public interface UserDao {
	/**
	 * 通过 部分user数据 获取 全部数据
	 * @param u
	 * @return
	 */
	public User queryUser(User u);
	
	/**
	 * 查看 用户 是否 存在
	 * @param user
	 * @return
	 */
	public boolean queryUserExist(User user);
	
	/**
	 * 密码 是否 正确
	 * @param user
	 * @return
	 */
	public boolean queryUserIsTrue(User user);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);
	
	/**
	 * 能否 登录  不需要
	 * @param user
	 * @return
	 */
	public boolean userLoginIsOk(User user);
	
	/**
	 * 通过 事件号  获取 所有 用户
	 * @param user
	 * @return
	 */
	public ArrayList<User> getUsersOfEvent(User user);
}
