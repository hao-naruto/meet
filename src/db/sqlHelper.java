package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.rowset.*;
/**
 * 数据库 操作 封装
 * @author wanghao
 *
 */
public class sqlHelper {
	
	/**
	 * 数据库 查询
	 * @param sql
	 * @param str
	 * @return  CachedRowSetImpl
	 * @throws SQLException 
	 */
	@SuppressWarnings("restriction")
	public static CachedRowSetImpl query(String sql, String[] str) throws SQLException{
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CachedRowSetImpl rowset = new CachedRowSetImpl();
		try{
			// 获取连接
			ct = JDBCUtil.getConnection();
			ps = ct.prepareStatement(sql);
			for(int i = 0; i < str.length; i++){
				ps.setString(i+1, str[i]);
			}
			rs = ps.executeQuery();
			rowset.populate(rs);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				JDBCUtil.closeConn(ct);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return rowset;
	}
	/**
	 * 数据库更新
	 * @param sql
	 * @param str
	 */
	public static void update(String sql, String[] str){
		PreparedStatement ps = null;
		Connection ct = null;
		try{
			ct = JDBCUtil.getConnection();
			ps = ct.prepareStatement(sql);
			for(int i = 0; i < str.length; i++){
				ps.setString(i+1 , str[i]);
			}
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//关闭连接
			try{
				if(ps != null) ps.close();
				JDBCUtil.closeConn(ct);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
